package com.crud_items.crud_items_back.repository;

import java.util.List;
import java.util.Optional;

import com.crud_items.crud_items_back.model.Experience;

public interface IExperienceRepo {

    Experience save(Experience experience);

    Optional<Experience> findById(Long id);

    List<Experience> findAll();

    void deleteById(Long id);

    List<Experience> findByPersonId(Long personId);
}
