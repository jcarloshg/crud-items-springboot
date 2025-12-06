package com.crud_items.crud_items_back.model;

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
    private String firstName;
    private String lastName;
    private String title;
    private String profileDescription;
    private String profileImageUrl;
    private Integer yearsOfExperience;
    private String email;
    private String phone;
    private String linkedinUrl;
    private String githubUrl;

    // Getters and setters omitted for brevity
}
