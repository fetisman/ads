package org.fetisman.ads.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegistrationDto {

    @Size(min = 5, max = 20, message = "Check your name data. Require min length - 5 chars & max length - 20 chars")
    private String username;

    @Size(min = 8, max = 15, message = "Check your password data. Require min length - 8 chars & max length - 15 chars")
    private String password;

    @Email(message = "Email is not correct")
    @NotBlank(message = "Email can not be empty")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
