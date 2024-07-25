package com.learning.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UserForm {

    // only the fields that are in the signup form, and variable name should be same
    // as in User class
    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Minimum 3 Characters")
    private String name;
    @Email(message = "invalid Email Address")
    @NotBlank(message = "Email is required")
    private String email;
    @Size(min = 3, message = "Minimum 3 Characters")
    private String password;
    @Size(min = 8, max = 12, message = "Invalid Number")
    private String phoneNumber;
    @NotBlank(message = "About is required")
    private String about;

}
