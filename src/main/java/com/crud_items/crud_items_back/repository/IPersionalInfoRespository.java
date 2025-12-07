package com.crud_items.crud_items_back.repository;

import java.util.List;
import java.util.Optional;

import com.crud_items.crud_items_back.model.PersonalInfo;

public interface IPersionalInfoRespository {
    PersonalInfo save(PersonalInfo personalInfo);

    Optional<PersonalInfo> findById(Long id);

    List<PersonalInfo> findAll();

    void deleteById(Long id);
}
