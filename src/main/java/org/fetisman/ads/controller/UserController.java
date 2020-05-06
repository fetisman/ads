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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String userList(Model model,
            @AuthenticationPrincipal User user) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("currentUser", user);

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
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, form);

        return "redirect:/user";
    }

    //---------------Pswd-----------
    @GetMapping("pswd-profile")
    public String getPswdProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);

        return "pswdProfile";
    }

    @PostMapping("pswd-profile")
    public String updatePswdProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmedPassword,
            Model model
    ){
        if (StringUtils.isEmpty(oldPassword)){
            pswdProfileAddAttribute(model, null, newPassword, confirmedPassword,
                    "oldPasswordError", "Old password can not be empty");
            return "pswdProfile";
        }
        if (StringUtils.isEmpty(newPassword)){
            pswdProfileAddAttribute(model, oldPassword, null, confirmedPassword,
                    "newPasswordError", "Password can not be empty");
            return "pswdProfile";
        }
        if (StringUtils.isEmpty(confirmedPassword)){
            pswdProfileAddAttribute(model, oldPassword, newPassword, null,
                    "confirmedPasswordError", "Password confirmation can not be empty");
            return "pswdProfile";
        }

        if (!newPassword.equals(confirmedPassword)){
            pswdProfileAddAttribute(model, oldPassword, newPassword, confirmedPassword,
                    "confirmedPasswordError", "Passwords are different");
            return "pswdProfile";
        }

        if (newPassword.equals(oldPassword)){
            pswdProfileAddAttribute(model, oldPassword, newPassword, confirmedPassword,
                    "newPasswordError", "New password already exists");
            return "pswdProfile";
        }

        if (!userService.updatePswd(user,newPassword, oldPassword)){
            pswdProfileAddAttribute(model, oldPassword, newPassword, confirmedPassword,
                    "oldPasswordError", "Invalid old password");
            return "pswdProfile";
        }

        return "redirect:/user/pswd-profile";
    }

    private void pswdProfileAddAttribute(Model model, String oldPassword,
            String newPassword, String confirmedPassword,  String errorName,  String errorDescr){
        model.addAttribute("oldPassword", oldPassword);
        model.addAttribute("newPassword", newPassword);
        model.addAttribute("confirmedPassword", confirmedPassword);
        model.addAttribute(errorName, errorDescr);
    }

    //-------------User-------------
    @GetMapping("user-profile")
    public String getUserProfile(Model model, @AuthenticationPrincipal User user){
        userProfileAddAttribute(model, user, "", null);
        return "userProfile";
    }

    @PostMapping("user-profile")
    public String updateUserProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String newFirstName,
            @RequestParam String newLastName,
            Model model
    ){
        if (StringUtils.isEmpty(newFirstName)){
            userProfileAddAttribute(model, user, "firstNameError", "First name can not be empty");
            return "userProfile";
        }
        if (StringUtils.isEmpty(newLastName)){
            userProfileAddAttribute(model, user, "lastNameError", "Last name can not be empty");
            return "userProfile";
        }

        boolean isUserFirstNameChanged = user.getUserFirstName() == null || !user.getUserFirstName().equals(newFirstName);

        boolean isUserLastNameChanged = user.getUserLastName() == null || !user.getUserLastName().equals(newLastName);

        if (isUserFirstNameChanged || isUserLastNameChanged) {
            userService.updateUserProfile(user, newFirstName, newLastName);
            return "redirect:/user/user-profile";
        }

        return "redirect:/user/user-profile";
    }

    private void userProfileAddAttribute(Model model, User user, String errorName, String errorDescr){
        model.addAttribute("user", user);
        model.addAttribute(errorName, errorDescr);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("delete/{user}")
    public String deleteUser(@PathVariable User user, Model model){
        userService.deleteUser(user);

        model.addAttribute("users", userService.findAll());
        return "userList";
    }
}
