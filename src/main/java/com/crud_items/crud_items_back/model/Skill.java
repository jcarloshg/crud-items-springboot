package com.crud_items.crud_items_back.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    @NotBlank(message = "Skill name is mandatory")
    private String name;

    @NotNull(message = "Level percentage is mandatory")
    @Min(value = 0, message = "Level percentage must be at least 0")
    @Max(value = 100, message = "Level percentage must be at most 100")
    private Integer levelPercentage; // e.g., 1-10 scale

    @NotBlank(message = "Icon class is mandatory")
    private String iconClass;

    private Long personalInfoId;
}
