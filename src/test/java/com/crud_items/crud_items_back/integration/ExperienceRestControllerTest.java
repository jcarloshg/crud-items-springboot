package com.crud_items.crud_items_back.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.crud_items.crud_items_back.model.Experience;
import com.crud_items.crud_items_back.rest.ExperienceRestController;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExperienceRestControllerTest {

    @Autowired
    private ExperienceRestController experienceRestController;

    @Test
    void testGetAll() {
        List<Experience> experiences = experienceRestController.getAll();
        assertNotNull(experiences, "Should return a list, not null");
    }

    @Test
    void testCreateAndGetById() {
        Experience experience = new Experience(null, "Developer", "TestCompany", java.time.LocalDate.of(2020,1,1), java.time.LocalDate.of(2021,1,1), "Worked on backend", 1L);
        ResponseEntity<Experience> response = experienceRestController.createExperience(experience);
        assertEquals(org.springframework.http.HttpStatus.CREATED, response.getStatusCode());
        Experience saved = response.getBody();
        assertNotNull(saved);
        Experience found = experienceRestController.getById(saved.getId());
        assertEquals(saved.getId(), found.getId());
    }

    @Test
    void testUpdateExperience() {
        Experience experience = new Experience(null, "Developer", "TestCompany", java.time.LocalDate.of(2020,1,1), java.time.LocalDate.of(2021,1,1), "Worked on backend", 1L);
        Experience saved = experienceRestController.createExperience(experience).getBody();
        saved.setJobTitle("Senior Developer");
        ResponseEntity<Experience> response = experienceRestController.updateExperience(saved.getId(), saved);
        assertEquals(org.springframework.http.HttpStatus.OK, response.getStatusCode());
        assertEquals("Senior Developer", response.getBody().getJobTitle());
    }

    @Test
    void testDeleteExperience() {
        Experience experience = new Experience(null, "Developer", "TestCompany", java.time.LocalDate.of(2020,1,1), java.time.LocalDate.of(2021,1,1), "Worked on backend", 1L);
        Experience saved = experienceRestController.createExperience(experience).getBody();
        ResponseEntity<Void> response = experienceRestController.deleteExperience(saved.getId());
        assertEquals(org.springframework.http.HttpStatus.NO_CONTENT, response.getStatusCode());
        assertThrows(Exception.class, () -> experienceRestController.getById(saved.getId()));
    }

    @Test
    void testGetByIdNotFound() {
        assertThrows(Exception.class, () -> experienceRestController.getById(99999L));
    }
}
