package com.crud_items.crud_items_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.crud_items.crud_items_back.exception.ValidationException;
import com.crud_items.crud_items_back.model.PersonalInfo;
import com.crud_items.crud_items_back.repository.IPersionalInfoRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImp implements IPersonalInfoService {

    private final IPersionalInfoRespository personalInfoRepository;
    private final Validator validator;

    @Override
    @Transactional // Ensures that the operation is executed within a transaction
    public PersonalInfo save(PersonalInfo personalInfo) {
        BindingResult result = new BeanPropertyBindingResult(personalInfo, "personalInfo");
        validator.validate(personalInfo, result);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }

            throw new ValidationException(result);
        }
        PersonalInfo savedPersonalInfo = personalInfoRepository.save(personalInfo);
        return savedPersonalInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalInfo> findById(Long id) {
        Optional<PersonalInfo> personalInfo = personalInfoRepository.findById(id);
        return personalInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    @Transactional // Ensures that the operation is executed within a transaction
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }

}
