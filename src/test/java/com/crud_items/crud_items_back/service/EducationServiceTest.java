package com.crud_items.crud_items_back.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.crud_items.crud_items_back.exception.ValidationException;
import com.crud_items.crud_items_back.model.Education;
import com.crud_items.crud_items_back.repository.IEducationRepo;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EducationServiceTest {

    @Autowired
    private IEducationService educationService;
    @Autowired
    private IEducationRepo educationRepo;

    @Test
    void testEducationServiceNotNull() {
        assert educationService != null : "The education service should be autowired and not null";
        assert educationRepo != null : "The education repository should be autowired and not null";
    }

    @Test
    void saveEducation() {
        Education education = new Education(
                null,
                "Test Degree",
                "Test Institution",
                java.time.LocalDate.of(2020, 1, 1),
                java.time.LocalDate.of(2024, 1, 1),
                "Test Description",
                1L);
        Education savedEducation = educationService.save(education);

        assertNotNull(savedEducation, "The education was not saved");
        assertNotNull(
                educationRepo
                        .findById(savedEducation.getId())
                        .orElse(null),
                "The education should be found in the repository");

    }

    @Test
    void saveEducationInvalid() {
        Education education = new Education(
                null,
                "",
                "",
                java.time.LocalDate.of(2025, 1, 1),
                java.time.LocalDate.of(2024, 1, 1),
                "",
                1L);
        assertThrows(
                ValidationException.class,
                () -> {
                    educationService.save(education);
                },
                "Expected ValidationException to be thrown due to invalid education data");
    }
}
