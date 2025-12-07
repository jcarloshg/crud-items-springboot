package com.crud_items.crud_items_back.service;

import java.util.List;
import java.util.Optional;

import com.crud_items.crud_items_back.model.Education;

public interface IEducationService {

    Education save(Education education);

    Optional<Education> findById(Long id);

    List<Education> findAll();

    void deleteById(Long id);

    List<Education> findByPersonId(Long personId);
}
