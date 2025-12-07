package com.crud_items.crud_items_back.repository;

import java.util.List;
import java.util.Optional;

import com.crud_items.crud_items_back.model.Skill;

public interface ISkillRepo {
    Skill save(Skill skill);

    Optional<Skill> findById(Long id);

    List<Skill> findAll();

    void deleteById(Long id);

    List<Skill> findByPersonId(Long personId);
}
