package com.crud_items.crud_items_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud_items.crud_items_back.model.Education;
import com.crud_items.crud_items_back.repository.IEducationRepo;

@Service
public class EducationServiceImpl implements IEducationService {

    private final IEducationRepo educationRepo;

    public EducationServiceImpl(IEducationRepo educationRepo) {
        this.educationRepo = educationRepo;
    }

    @Override
    public Education save(Education education) {
        if (education.getStartDate() == null) {
            throw new IllegalArgumentException("Start date cannot be null");

        }

        if (education.getEndDate() != null && education.getStartDate().isAfter(education.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        return educationRepo.save(education);
    }

    @Override
    public Optional<Education> findById(Long id) {
        return educationRepo.findById(id);
    }

    @Override
    public List<Education> findAll() {
        return educationRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        educationRepo.deleteById(id);
    }

    @Override
    public List<Education> findByPersonId(Long personId) {
        return educationRepo.findByPersonId(personId);
    }

}
