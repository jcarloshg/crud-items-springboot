package com.crud_items.crud_items_back.unit.services;

import com.crud_items.crud_items_back.exception.ValidationException;
import com.crud_items.crud_items_back.model.Skill;
import com.crud_items.crud_items_back.repository.ISkillRepo;
import com.crud_items.crud_items_back.service.SkillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SkillServiceImplTest {
    /**
     * Mock instance of the ISkillRepo interface used for unit testing.
     * This allows for simulating repository behavior without interacting with the
     * actual database.
     */
    @Mock
    private ISkillRepo skillRepository;

    @Mock
    private Validator validator;

    /**
     * Injects the mocked dependencies into the SkillServiceImpl instance.
     * This allows for testing SkillServiceImpl methods with controlled behavior of
     * its dependencies.
     */
    @InjectMocks
    private SkillServiceImpl skillService;

    /**
     * Initializes mocks and sets up the SkillServiceImpl instance before each test.
     * This ensures that each test runs with fresh mock objects and a properly
     * constructed service.
     */
    @BeforeEach
    void setUp() {
        // Initializes the mock objects annotated with @Mock and injects them into
        // @InjectMocks fields.
        MockitoAnnotations.openMocks(this);
        skillService = new SkillServiceImpl(skillRepository, validator);
    }

    @Test
    void save_ValidSkill_SavesAndReturnsSkill() {
        // Arrange: create a valid Skill and mock validator/repository behavior
        Skill skill = new Skill();
        // Mock the validator to simulate successful validation (no errors added)
        doAnswer(invocation -> {
            // No errors added to result, so validation passes
            return null;
        })
                .when(validator)
                .validate(any(), any());

        // Mock the repository to return the same skill when save is called
        when(skillRepository.save(skill)).thenReturn(skill);
        // Act: call save on the service
        Skill saved = skillService.save(skill);
        // Assert: verify the returned skill and repository interaction
        assertEquals(skill, saved);
        verify(skillRepository).save(skill);
    }

    @Test
    void save_InvalidSkill_ThrowsValidationException() {
        // Arrange: create an invalid Skill and mock validator to add errors
        Skill skill = new Skill();
        doAnswer(invocation -> {
            BeanPropertyBindingResult result = invocation.getArgument(1);
            result.reject("error.code", "Validation failed");
            return null;
        }).when(validator).validate(any(), any());
        // Act & Assert: call save and expect ValidationException
        assertThrows(ValidationException.class, () -> skillService.save(skill));
    }

    @Test
    void findById_ReturnsSkillOptional() {
        // Arrange: mock repository to return a Skill for given id
        Skill skill = new Skill();
        // Mock the repository to return an Optional containing the skill when findById
        // is called with id 1L
        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        // Act: call findById on the service
        Optional<Skill> result = skillService.findById(1L);
        // Assert: verify the result is present and matches expected Skill
        assertTrue(result.isPresent());
        assertEquals(skill, result.get());
    }

    @Test
    void findAll_ReturnsSkillList() {
        // Arrange: mock repository to return a list of Skills
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        List<Skill> skills = Arrays.asList(skill1, skill2);
        // Mock the repository to return the predefined list of skills when findAll is
        // called
        when(skillRepository.findAll()).thenReturn(skills);
        // Act: call findAll on the service
        List<Skill> result = skillService.findAll();
        // Assert: verify the returned list matches expected Skills
        assertEquals(skills, result);
    }

    @Test
    void deleteById_DeletesSkill() {
        // Arrange: (no setup needed for delete)
        // Act: call deleteById on the service
        skillService.deleteById(1L);
        // Assert: verify repository deleteById was called
        verify(skillRepository).deleteById(1L);
    }

    @Test
    void findByPersonId_ReturnsSkillList() {
        // Arrange: mock repository to return Skills for a personId
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        List<Skill> skills = Arrays.asList(skill1, skill2);
        // Mock the repository to return the predefined list of skills when
        // findByPersonId is called with 10L
        when(skillRepository.findByPersonId(10L)).thenReturn(skills);
        // Act: call findByPersonId on the service
        List<Skill> result = skillService.findByPersonId(10L);
        // Assert: verify the returned list matches expected Skills
        assertEquals(skills, result);
    }
}
