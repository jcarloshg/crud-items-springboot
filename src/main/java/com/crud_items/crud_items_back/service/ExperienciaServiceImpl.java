package com.crud_items.crud_items_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.crud_items.crud_items_back.exception.ValidationException;
import com.crud_items.crud_items_back.model.Experience;
import com.crud_items.crud_items_back.repository.IExperienceRepo;

@Service
public class ExperienciaServiceImpl implements IExperienciaService {

    private final IExperienceRepo experienceRepo;
    private final Validator validator;

    public ExperienciaServiceImpl(IExperienceRepo experienceRepo, Validator validator) {
        this.experienceRepo = experienceRepo;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Experience save(Experience experience) {

        // if (experience.getStartDate() == null) {
        // throw new IllegalArgumentException("Start date cannot be null");
        // }

        // if (experience.getEndDate() != null &&
        // experience.getEndDate().isBefore(experience.getStartDate())) {
        // throw new IllegalArgumentException("End date cannot be before start date");
        // }

        BindingResult result = new BeanPropertyBindingResult(experience, "Binding");
        validator.validate(experience, result);

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return experienceRepo.save(experience);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Experience> findById(Long id) {
        return experienceRepo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return experienceRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        experienceRepo.deleteById(id);
    }

    @Override
    public List<Experience> findByPersonId(Long personId) {
        return experienceRepo.findByPersonId(personId);
    }

}
