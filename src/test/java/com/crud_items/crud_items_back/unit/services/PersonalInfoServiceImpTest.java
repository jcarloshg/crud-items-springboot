package com.crud_items.crud_items_back.unit.services;

import com.crud_items.crud_items_back.exception.ValidationException;
import com.crud_items.crud_items_back.model.PersonalInfo;
import com.crud_items.crud_items_back.repository.IPersionalInfoRespository;
import com.crud_items.crud_items_back.service.PersonalInfoServiceImp;
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

class PersonalInfoServiceImpTest {
    @Mock
    private IPersionalInfoRespository personalInfoRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private PersonalInfoServiceImp personalInfoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personalInfoService = new PersonalInfoServiceImp(personalInfoRepository, validator);
    }

    @Test
    void save_ValidPersonalInfo_SavesAndReturnsPersonalInfo() {
        // Arrange: create a valid PersonalInfo and mock validator/repository behavior
        PersonalInfo personalInfo = new PersonalInfo();
        // Mock the validator to simulate successful validation (no errors added)
        doAnswer(invocation -> null).when(validator).validate(any(), any());
        // Mock the repository to return the same personalInfo when save is called
        when(personalInfoRepository.save(personalInfo)).thenReturn(personalInfo);
        // Act: call save on the service
        PersonalInfo saved = personalInfoService.save(personalInfo);
        // Assert: verify the returned personalInfo and repository interaction
        assertEquals(personalInfo, saved);
        verify(personalInfoRepository).save(personalInfo);
    }

    @Test
    void save_InvalidPersonalInfo_ThrowsValidationException() {
        // Arrange: create an invalid PersonalInfo and mock validator to add errors
        PersonalInfo personalInfo = new PersonalInfo();
        doAnswer(invocation -> {
            BeanPropertyBindingResult result = invocation.getArgument(1);
            result.reject("error.code", "Validation failed");
            return null;
        }).when(validator).validate(any(), any());
        // Act & Assert: call save and expect ValidationException
        assertThrows(ValidationException.class, () -> personalInfoService.save(personalInfo));
    }

    @Test
    void findById_ReturnsPersonalInfoOptional() {
        // Arrange: mock repository to return a PersonalInfo for given id
        PersonalInfo personalInfo = new PersonalInfo();
        // Mock the repository to return an Optional containing the personalInfo when findById is called with id 1L
        when(personalInfoRepository.findById(1L)).thenReturn(Optional.of(personalInfo));
        // Act: call findById on the service
        Optional<PersonalInfo> result = personalInfoService.findById(1L);
        // Assert: verify the result is present and matches expected PersonalInfo
        assertTrue(result.isPresent());
        assertEquals(personalInfo, result.get());
    }

    @Test
    void findAll_ReturnsPersonalInfoList() {
        // Arrange: mock repository to return a list of PersonalInfo
        PersonalInfo personalInfo1 = new PersonalInfo();
        PersonalInfo personalInfo2 = new PersonalInfo();
        List<PersonalInfo> personalInfos = Arrays.asList(personalInfo1, personalInfo2);
        // Mock the repository to return the predefined list of personalInfos when findAll is called
        when(personalInfoRepository.findAll()).thenReturn(personalInfos);
        // Act: call findAll on the service
        List<PersonalInfo> result = personalInfoService.findAll();
        // Assert: verify the returned list matches expected PersonalInfos
        assertEquals(personalInfos, result);
    }

    @Test
    void deleteById_DeletesPersonalInfo() {
        // Arrange: (no setup needed for delete)
        // Act: call deleteById on the service
        personalInfoService.deleteById(1L);
        // Assert: verify repository deleteById was called
        verify(personalInfoRepository).deleteById(1L);
    }
}
