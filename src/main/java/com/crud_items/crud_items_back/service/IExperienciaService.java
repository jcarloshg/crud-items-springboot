package com.crud_items.crud_items_back.service;

import java.util.List;
import java.util.Optional;

import com.crud_items.crud_items_back.model.Experience;

public interface IExperienciaService {

    Experience save(Experience experience);

    Optional<Experience> findById(Long id);

    List<Experience> findAll();

    void deleteById(Long id);

    List<Experience> findByPersonId(Long personId);
}
