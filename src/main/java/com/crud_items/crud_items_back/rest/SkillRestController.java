package com.crud_items.crud_items_back.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.crud_items.crud_items_back.model.Skill;
import com.crud_items.crud_items_back.service.ISkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillRestController {

    private final ISkillService skillService;

    public SkillRestController(ISkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/all")
    public List<Skill> getAll() {
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public Skill getById(@PathVariable Long id) {
        Optional<Skill> skill = skillService.findById(id);
        if (skill.isPresent()) {
            return skill.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill savedSkill = skillService.save(skill);
        return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(
            @PathVariable Long id,
            @RequestBody Skill skill) {
        Optional<Skill> existingSkill = skillService.findById(id);
        if (existingSkill.isPresent()) {
            skill.setId(id);
            Skill updatedSkill = skillService.save(skill);
            return new ResponseEntity<>(updatedSkill, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        Optional<Skill> existingSkill = skillService.findById(id);
        if (existingSkill.isPresent()) {
            skillService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found with id: " + id);
        }
    }
}
