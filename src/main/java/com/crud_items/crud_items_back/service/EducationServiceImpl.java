package com.crud_items.crud_items_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.crud_items.crud_items_back.exception.ValidationException;
import com.crud_items.crud_items_back.model.Education;
import com.crud_items.crud_items_back.repository.IEducationRepo;

@Service
public class EducationServiceImpl implements IEducationService {

    private final IEducationRepo educationRepo;
    private final Validator validator;

    public EducationServiceImpl(IEducationRepo educationRepo, Validator validator) {
        this.educationRepo = educationRepo;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Education save(Education education) {

        BindingResult result = new BeanPropertyBindingResult(education, "Binding");
        validator.validate(education, result);

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return educationRepo.save(education);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Education> findById(Long id) {
        return educationRepo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return educationRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        educationRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findByPersonId(Long personId) {
        return educationRepo.findByPersonId(personId);
    }

}
