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
        model.addAttribute("lastname", user.getUserLastName());
        model.addAttribute("email", user.getEmail());

        return "pswdProfile";
    }

    @PostMapping("pswd-profile")
    public String updatePswdProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password0,
            @RequestParam String password,
            @RequestParam String password2,
            Model model
    ){
        if (StringUtils.isEmpty(password0)){
            pswdProfileAddAttribute(model, null, password, password2,
                    "password0Error", "Old password can not be empty");
            return "pswdProfile";
        }
        if (StringUtils.isEmpty(password)){
            pswdProfileAddAttribute(model, password0, null, password2,
                    "passwordError", "Password can not be empty");
            return "pswdProfile";
        }
        if (StringUtils.isEmpty(password2)){
            pswdProfileAddAttribute(model, password0, password, null,
                    "password2Error", "Password confirmation can not be empty");
            return "pswdProfile";
        }

        if (!password.equals(password2)){
            pswdProfileAddAttribute(model, password0, password, password2,
                    "password2Error", "Passwords are different");
            return "pswdProfile";
        }

        if (password.equals(password0)){
            pswdProfileAddAttribute(model, password0, password, password2,
                    "passwordError", "New password already exists");
            return "pswdProfile";
        }

        if (!userService.updatePswd(user,password, password0)){
            pswdProfileAddAttribute(model, password0, password, password2,
                    "password0Error", "Invalid old password");
            return "pswdProfile";
        }

        return "redirect:/user/pswd-profile";
    }

    private void pswdProfileAddAttribute(Model model, String password0,
            String password, String password2,  String errorName,  String errorDescr){
        model.addAttribute("password0", password0);
        model.addAttribute("password", password);
        model.addAttribute("password2", password2);
        model.addAttribute(errorName, errorDescr);
    }

    //-------------User-------------
    @GetMapping("user-profile")
    public String getUserProfile(Model model, @AuthenticationPrincipal User user){
        userProfileAddAttribute(model, user.getUsername(), user.getEmail(),
                user.getUserLastName(), null);
        return "userProfile";
    }

    @PostMapping("user-profile")
    public String updateUserProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String userLastName,
            Model model
    ){
        if (StringUtils.isEmpty(userLastName)){
            userProfileAddAttribute(model, user.getUsername(), user.getEmail(),
                    user.getUserLastName(), "Last name can not be empty");
            return "userProfile";
        }

        if (!userService.updateUserProfile(user,userLastName)){
            userProfileAddAttribute(model, user.getUsername(), user.getEmail(),
                    user.getUserLastName(), "New last name already exists");
            return "userProfile";
        }

        return "redirect:/user/user-profile";
    }

    private void userProfileAddAttribute(Model model, String userName,
            String userEmail, String userLastName,  String lastNameError){
        model.addAttribute("userName", userName);
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("userLastName", userLastName);
        model.addAttribute("lastNameError", lastNameError);
    }

}