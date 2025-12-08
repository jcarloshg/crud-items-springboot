package com.crud_items.crud_items_back.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.crud_items.crud_items_back.model.PersonalInfo;
import com.crud_items.crud_items_back.rest.PersonalTestInfoControl;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonalTestInfoControlTest {

    @Autowired
    private PersonalTestInfoControl personalTestInfoControl;

    @Test
    void testGetAll() {
        List<PersonalInfo> infos = personalTestInfoControl.getAll();
        assertNotNull(infos, "Should return a list, not null");
    }

    @Test
    void testCreateAndGetById() {
        PersonalInfo info = new PersonalInfo(
                null, // id
                "John", // firstName
                "Doe", // lastName
                "Developer", // title
                "Profile desc", // profileDescription
                "http://img.url", // profileImageUrl
                5, // yearsOfExperience
                "john@doe.com", // email
                "123456789", // phone
                "https://linkedin.com/in/johndoe", // linkedinUrl
                "https://github.com/johndoe" // githubUrl
        );
        ResponseEntity<PersonalInfo> response = personalTestInfoControl.createPersonalInfo(info);
        assertEquals(org.springframework.http.HttpStatus.CREATED, response.getStatusCode());
        PersonalInfo saved = response.getBody();
        assertNotNull(saved);
        PersonalInfo found = personalTestInfoControl.getById(saved.getId());
        assertEquals(saved.getId(), found.getId());
    }

    @Test
    void testUpdatePersonalInfo() {
        PersonalInfo info = new PersonalInfo(
                null, "John", "Doe", "Developer", "Profile desc", "http://img.url", 5, "john@doe.com", "123456789",
                "https://linkedin.com/in/johndoe", "https://github.com/johndoe");
        PersonalInfo saved = personalTestInfoControl.createPersonalInfo(info).getBody();
        saved.setFirstName("Jane");
        ResponseEntity<PersonalInfo> response = personalTestInfoControl.updatePersonalInfo(saved.getId(), saved);
        assertEquals(org.springframework.http.HttpStatus.OK, response.getStatusCode());
        assertEquals("Jane", response.getBody().getFirstName());
    }

    @Test
    void testDeletePersonalInfo() {
        PersonalInfo info = new PersonalInfo(
                null, "John", "Doe", "Developer", "Profile desc", "http://img.url", 5, "john@doe.com", "123456789",
                "https://linkedin.com/in/johndoe", "https://github.com/johndoe");
        PersonalInfo saved = personalTestInfoControl.createPersonalInfo(info).getBody();
        ResponseEntity<Void> response = personalTestInfoControl.deletePersonalInfo(saved.getId());
        assertEquals(org.springframework.http.HttpStatus.NO_CONTENT, response.getStatusCode());
        assertThrows(Exception.class, () -> personalTestInfoControl.getById(saved.getId()));
    }

    @Test
    void testGetByIdNotFound() {
        assertThrows(Exception.class, () -> personalTestInfoControl.getById(99999L));
    }
}
