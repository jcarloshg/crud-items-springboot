package com.crud_items.crud_items_back.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data
// Lombok annotation to generate a constructor with all fields as parameters
@AllArgsConstructor
// Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Experience {
    private Long id;

    @NotBlank(message = "Job title is mandatory")
    private String jobTitle;

    @NotBlank(message = "Institution is mandatory")
    private String companyName;

    @NotNull(message = "Start date is mandatory")
    @PastOrPresent(message = "Start date must be in the past or present")
    private java.time.LocalDate startDate;

    @PastOrPresent(message = "End date must be in the past or present")
    private java.time.LocalDate endDate;

    @NotBlank(message = "Description is mandatory")
    private String description;

    private Long personalInfoId;
}
