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
public class Skill {
    private Long id;
    private String name;
    private Integer levelPercentage; // e.g., 1-10 scale
    private String iconClass;
    private Long personalInfoId;
}
