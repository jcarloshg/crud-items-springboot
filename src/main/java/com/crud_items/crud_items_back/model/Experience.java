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
public class Experience {
    private Long id;
    private String jobTitle;
    private String companyName;
    private java.time.LocalDate startDate;
    private java.time.LocalDate endDate;
    private String description;
    private Long personalInfoId;
}
