package org.fetisman.ads.controller;

import java.util.Map;
import org.fetisman.ads.domain.Role;
import org.fetisman.ads.domain.User;
import org.fetisman.ads.repo.UserRepo;
import org.fetisman.ads.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);

        return "redirect:/user";
    }

//---------------Pswd-----------
    @GetMapping("pswd-profile")
    public String getPswdProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "pswdProfile";
    }

    @PostMapping("pswd-profile")
    public String updatePswdProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String password2,
            Model model
    ){
        if (StringUtils.isEmpty(password)){
            model.addAttribute("passwordError", "Password can not be empty");
            return "pswdProfile";
        }
        if (StringUtils.isEmpty(password2)){
            model.addAttribute("password", password);
            model.addAttribute("password2Error", "Password confirm can not be empty");
            return "pswdProfile";
        }
        if (!password.equals(password2)){
            model.addAttribute("password", password);
            model.addAttribute("password2", password2);
            model.addAttribute("passwordError", "Passwords are different");
            model.addAttribute("password2Error", "Passwords are different");
            return "pswdProfile";
        }

        userService.updatePswd(user,password);

        return "redirect:/user/pswd-profile";
    }

//------------------Email--------------
    @GetMapping("email-profile")
    public String getEmailProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username", user.getUsername());
        model.addAttribute("user", user);

        return "emailProfile";
    }

    @PostMapping("email-profile")
    public String updateEmailProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String email,
            @RequestParam String newEmail,
            Model model
    ){

        if (StringUtils.isEmpty(email)){
            model.addAttribute("emailError", "Email can not be empty");
            return "emailProfile";
        }
        if (!user.getEmail().equals(email)){
            model.addAttribute("email", email);
            model.addAttribute("emailError", "Current email is wrong");
            return "emailProfile";
        }

        if (StringUtils.isEmpty(newEmail)){
            model.addAttribute("email", email);
            model.addAttribute("newEmailError", "New email can not be empty");
            return "emailProfile";
        }

        if (!userService.updateEmail(user,email, newEmail)){
            model.addAttribute("email", email);
            model.addAttribute("newEmailError", "New email already exists");
            return "emailProfile";
//            return "redirect:/user/email-profile";
        }

        model.addAttribute("mailSendWarning", "We just sent you e-letter on " + newEmail + " address."
                + "Please , visit your mail-box and confirm your mail address");
        return "mailWarnPage";
    }
    //-------------User-------------
    @GetMapping("user-profile")
    public String getUserProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username", user.getUsername());

        return "userProfile";
    }

    @PostMapping("user-profile")
    public String updateUserProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            Model model
    ){
        if (StringUtils.isEmpty(username)){
            model.addAttribute("username", username);
            model.addAttribute("nameError", "Name can not be empty");
            return "userProfile";
        }

        if (userRepo.findByUsername(username) != null){
            model.addAttribute("username", username);
            model.addAttribute("nameError", "New name already exists");
            return "userProfile";
        }

        userService.updateUser(user,username);

        return "redirect:/user/user-profile";
    }

}