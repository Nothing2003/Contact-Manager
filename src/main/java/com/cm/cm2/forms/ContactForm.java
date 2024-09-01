package com.cm.cm2.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ContactForm {

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Minimum 3 characters are required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email Address")
    private String email;
    @NotBlank(message = "Number is required")
    @Size(min = 10, max = 10, message = "10 Number required")
    private String phoneNumber;
    @NotBlank(message = "Address is required")
    private String address;
    private String description;
    private boolean favorite;
    private String websiteLink;
    private String LinkedInLink;
    private MultipartFile ContactImage;

}
