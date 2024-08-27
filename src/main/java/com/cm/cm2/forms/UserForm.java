package com.cm.cm2.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {

    @NotBlank(message = "usernam is required")
    @Size(min = 3, message = "Min 3 characters is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Min 6 Characters is required")
    private String password;
    @NotBlank(message = "About is required")
    private String about;
    @Size(min = 10, max = 10, message = "Invalid Number")
    private String phoneNo;

}
