package com.crud_items.crud_items_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud_items.crud_items_back.model.Experience;
import com.crud_items.crud_items_back.repository.IExperienceRepo;

@Service
public class ExperienciaServiceImpl implements IExperienciaService {

    private final IExperienceRepo experienceRepo;

    public ExperienciaServiceImpl(IExperienceRepo experienceRepo) {
        this.experienceRepo = experienceRepo;
    }

    @Override
    public Experience save(Experience experience) {

        if (experience.getStartDate() == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }

        if (experience.getEndDate() != null && experience.getEndDate().isBefore(experience.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        return experienceRepo.save(experience);
    }

    @Override
    public Optional<Experience> findById(Long id) {
        return experienceRepo.findById(id);
    }

    @Override
    public List<Experience> findAll() {
        return experienceRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        experienceRepo.deleteById(id);
    }

    @Override
    public List<Experience> findByPersonId(Long personId) {
        return experienceRepo.findByPersonId(personId);
    }

}
