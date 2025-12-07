package com.crud_items.crud_items_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud_items.crud_items_back.model.PersonalInfo;
import com.crud_items.crud_items_back.repository.IPersionalInfoRespository;

@Service
public class PersonalInfoServiceImp implements IPersonalInfoService {

    private final IPersionalInfoRespository personalInfoRepository;

    public PersonalInfoServiceImp(IPersionalInfoRespository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        PersonalInfo savedPersonalInfo = personalInfoRepository.save(personalInfo);
        return savedPersonalInfo;
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        Optional<PersonalInfo> personalInfo = personalInfoRepository.findById(id);
        return personalInfo;
    }

    @Override
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }

}
