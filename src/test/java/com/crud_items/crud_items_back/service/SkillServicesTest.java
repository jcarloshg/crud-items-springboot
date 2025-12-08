package com.crud_items.crud_items_back.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.crud_items.crud_items_back.exception.ValidationException;
import com.crud_items.crud_items_back.model.Skill;
import com.crud_items.crud_items_back.repository.ISkillRepo;

@SpringBootTest
// is used in Spring Boot tests to ensure that the application context is reset
// (reloaded) before each test method runs.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SkillServicesTest {

    @Autowired
    private ISkillService skillService;
    @Autowired
    private ISkillRepo skillRepo;

    @Test
    void testSaveSkill() {
        Skill skill = new Skill(
                null,
                "Java Programming",
                90,
                "fab fa-java",
                1L);
        Skill savedSkill = skillService.save(skill);
        assertNotNull(savedSkill, "The saved skill should not be null");
        assertNotNull(
                skillRepo
                        .findById(savedSkill.getId())
                        .orElse(null),
                "The skill should be found in the repository");
    }

    @Test
    void testSaveSkillInvalid() {
        Skill skill = new Skill(
                null,
                "",
                150,
                "",
                1L);
        assertThrows(
                ValidationException.class,
                () -> {
                    skillService.save(skill);
                },
                "Expected ValidationException to be thrown due to invalid skill data");
    }

}
