package com.marketingsuite.models;

import com.marketingsuite.annotations.IsPasswordsMatching;
import com.marketingsuite.annotations.UniqueEmail;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@IsPasswordsMatching
public class RegistrationModel {

    @Size(min = 5, message = "Username too short")
    private String username;

    @Email(message = "Invalid email address!")
    @UniqueEmail
    private String email;

    @Size(min = 5, message = "Password too short")
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
