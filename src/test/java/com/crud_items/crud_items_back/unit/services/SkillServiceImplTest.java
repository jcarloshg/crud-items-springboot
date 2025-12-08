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
import static org.mockito.Mockito.*;

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

    @InjectMocks
    private SkillServiceImpl skillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        skillService = new SkillServiceImpl(skillRepository, validator);
    }

    @Test
    void save_ValidSkill_SavesAndReturnsSkill() {
        Skill skill = new Skill();
        doAnswer(invocation -> {
            Object target = invocation.getArgument(0);
            BeanPropertyBindingResult result = invocation.getArgument(1);
            // No errors added
            return null;
        }).when(validator).validate(any(), any());
        when(skillRepository.save(skill)).thenReturn(skill);
        Skill saved = skillService.save(skill);
        assertEquals(skill, saved);
        verify(skillRepository).save(skill);
    }

    @Test
    void save_InvalidSkill_ThrowsValidationException() {
        Skill skill = new Skill();
        doAnswer(invocation -> {
            BeanPropertyBindingResult result = invocation.getArgument(1);
            result.reject("error.code", "Validation failed");
            return null;
        }).when(validator).validate(any(), any());
        assertThrows(ValidationException.class, () -> skillService.save(skill));
    }

    @Test
    void findById_ReturnsSkillOptional() {
        Skill skill = new Skill();
        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        Optional<Skill> result = skillService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(skill, result.get());
    }

    @Test
    void findAll_ReturnsSkillList() {
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        List<Skill> skills = Arrays.asList(skill1, skill2);
        when(skillRepository.findAll()).thenReturn(skills);
        List<Skill> result = skillService.findAll();
        assertEquals(skills, result);
    }

    @Test
    void deleteById_DeletesSkill() {
        skillService.deleteById(1L);
        verify(skillRepository).deleteById(1L);
    }

    @Test
    void findByPersonId_ReturnsSkillList() {
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        List<Skill> skills = Arrays.asList(skill1, skill2);
        when(skillRepository.findByPersonId(10L)).thenReturn(skills);
        List<Skill> result = skillService.findByPersonId(10L);
        assertEquals(skills, result);
    }
}
