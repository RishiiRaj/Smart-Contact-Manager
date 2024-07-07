package com.learning.scm.forms;

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
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String about;

}
