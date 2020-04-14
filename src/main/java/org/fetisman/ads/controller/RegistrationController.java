package org.fetisman.ads.controller;

import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;
import org.fetisman.ads.domain.CaptchaResponseDto;
import org.fetisman.ads.domain.User;
import org.fetisman.ads.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2")String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @RequestParam("email")String email,
            @Valid User user,
            BindingResult bindingResult,
            Model model){
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);

        CaptchaResponseDto captchaResponseDto = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!captchaResponseDto.isSuccess()){
            model.addAttribute("captchaError", "Fill captcha");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty){
            model.addAttribute("password2Error", "Password confirmation can not be empty");
        }

        boolean isPswdEquals = (user.getPassword()!= null && !user.getPassword().equals(passwordConfirm));
        if (isPswdEquals){
            model.addAttribute("passwordError", "Passwords are different !");
        }

        if (isConfirmEmpty || isPswdEquals || bindingResult.hasErrors() || !captchaResponseDto.isSuccess()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists");
            return "registration";
        }

        model.addAttribute("mailSendWarning", "We just sent you e-letter on " + email + " address. Please , visit your mail-box and confirm your mail address");
        return "mailWarnPage";
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
