package com.crud_items.crud_items_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud_items.crud_items_back.model.Skill;
import com.crud_items.crud_items_back.repository.ISkillRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements ISkillService {

    private final ISkillRepo skillRepository;

    @Override
    public Skill save(Skill skill) {
        if (skill.getLevelPercentage() < 0 || skill.getLevelPercentage() > 100) {
            throw new IllegalArgumentException("Level percentage must be between 0 and 100");
        }
        return skillRepository.save(skill);
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> findByPersonId(Long personId) {
        return skillRepository.findByPersonId(personId);
    }

}
