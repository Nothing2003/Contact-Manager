package com.cm.cm2.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ForgetPasswordForm {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address")
    private String email;
}
