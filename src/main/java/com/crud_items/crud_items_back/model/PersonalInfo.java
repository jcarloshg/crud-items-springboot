package com.crud_items.crud_items_back.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data
// Lombok annotation to generate a constructor with all fields as parameters
@AllArgsConstructor
// Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Profile description is mandatory")
    private String profileDescription;

    @NotBlank(message = "Profile image URL is mandatory")
    private String profileImageUrl;

    @Min(value = 0, message = "Years of experience must be non-negative")
    private Integer yearsOfExperience;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    private String phone;

    @NotBlank(message = "LinkedIn URL is mandatory")
    private String linkedinUrl;

    @NotBlank(message = "GitHub URL is mandatory")
    private String githubUrl;
}
