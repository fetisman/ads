package org.example.advs.controllers;

import com.sun.mail.util.MailConnectException;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import javax.validation.Valid;
import org.example.advs.domain.CaptchaResponseDto;
import org.example.advs.domain.User;
import org.example.advs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class.getName());

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2")String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model){
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);

        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()){
            model.addAttribute("captchaError", "Fill captcha");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty){
            model.addAttribute("password2Error", "Password confirmation can not be empty");
        }

        if (user.getPassword()!=null && !user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordError", "Passwords are different");
        }

        if (isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        try {
            if (!userService.addUser(user)) {
                model.addAttribute("usernameError", "User exists");
                return "registration";
            }
        }
        catch (MailException e){
            model.addAttribute("mailsendError", "Server Error. We have some troubles with a registration email sending.");
            if (e.getCause() instanceof MailConnectException) { // parent is MailSendException
                LOGGER.log(Level.WARNING, "MailConnectException. Check out spring.mail.host data in .properties");
            }
            else if (e.getCause() instanceof AuthenticationFailedException){ // parent is MailAuthenticationException
                LOGGER.log(Level.WARNING, "AuthenticationFailedException. Check out spring.mail.password / .username data in .properties");
            }
            else {// works with spring.mail.port wrong data as example
                LOGGER.log(Level.WARNING, "MailException. We have some troubles with a mail sending in .properties");
            }
            return "errorPage";

        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        }else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found");
        }
        return "login";
    }
}
