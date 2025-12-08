package com.crud_items.crud_items_back.unit.services;

import com.crud_items.crud_items_back.exception.ValidationException;
import com.crud_items.crud_items_back.model.Education;
import com.crud_items.crud_items_back.repository.IEducationRepo;
import com.crud_items.crud_items_back.service.EducationServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EducationServiceImplTest {
    @Mock
    private IEducationRepo educationRepo;

    @Mock
    private Validator validator;

    @InjectMocks
    private EducationServiceImpl educationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        educationService = new EducationServiceImpl(educationRepo, validator);
    }

    @Test
    void save_ValidEducation_SavesAndReturnsEducation() {
        // Arrange: create a valid Education and mock validator/repository behavior
        Education education = new Education();
        // Mock the validator to simulate successful validation (no errors added)
        doAnswer(invocation -> null).when(validator).validate(any(), any());
        // Mock the repository to return the same education when save is called
        when(educationRepo.save(education)).thenReturn(education);
        // Act: call save on the service
        Education saved = educationService.save(education);
        // Assert: verify the returned education and repository interaction
        assertEquals(education, saved);
        verify(educationRepo).save(education);
    }

    @Test
    void save_InvalidEducation_ThrowsValidationException() {
        // Arrange: create an invalid Education and mock validator to add errors
        Education education = new Education();
        doAnswer(invocation -> {
            BeanPropertyBindingResult result = invocation.getArgument(1);
            result.reject("error.code", "Validation failed");
            return null;
        }).when(validator).validate(any(), any());
        // Act & Assert: call save and expect ValidationException
        assertThrows(ValidationException.class, () -> educationService.save(education));
    }

    @Test
    void findById_ReturnsEducationOptional() {
        // Arrange: mock repository to return an Education for given id
        Education education = new Education();
        // Mock the repository to return an Optional containing the education when
        // findById is called with id 1L
        when(educationRepo.findById(1L)).thenReturn(Optional.of(education));
        // Act: call findById on the service
        Optional<Education> result = educationService.findById(1L);
        // Assert: verify the result is present and matches expected Education
        assertTrue(result.isPresent());
        assertEquals(education, result.get());
    }

    @Test
    void findAll_ReturnsEducationList() {
        // Arrange: mock repository to return a list of Educations
        Education education1 = new Education();
        Education education2 = new Education();
        List<Education> educations = Arrays.asList(education1, education2);
        // Mock the repository to return the predefined list of educations when findAll
        // is called
        when(educationRepo.findAll()).thenReturn(educations);
        // Act: call findAll on the service
        List<Education> result = educationService.findAll();
        // Assert: verify the returned list matches expected Educations
        assertEquals(educations, result);
    }

    @Test
    void deleteById_DeletesEducation() {
        // Arrange: (no setup needed for delete)
        // Act: call deleteById on the service
        educationService.deleteById(1L);
        // Assert: verify repository deleteById was called
        verify(educationRepo).deleteById(1L);
    }

    @Test
    void findByPersonId_ReturnsEducationList() {
        // Arrange: mock repository to return Educations for a personId
        Education education1 = new Education();
        Education education2 = new Education();
        List<Education> educations = Arrays.asList(education1, education2);
        // Mock the repository to return the predefined list of educations when
        // findByPersonId is called with 10L
        when(educationRepo.findByPersonId(10L)).thenReturn(educations);
        // Act: call findByPersonId on the service
        List<Education> result = educationService.findByPersonId(10L);
        // Assert: verify the returned list matches expected Educations
        assertEquals(educations, result);
    }
}
