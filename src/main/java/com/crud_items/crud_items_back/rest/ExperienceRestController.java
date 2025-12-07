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

import com.crud_items.crud_items_back.model.Experience;
import com.crud_items.crud_items_back.service.IExperienciaService;

@RestController
@RequestMapping("/api/experience")
public class ExperienceRestController {

    private final IExperienciaService experienciaService;

    public ExperienceRestController(IExperienciaService experienciaService) {
        this.experienciaService = experienciaService;
    }

    @GetMapping("/all")
    public List<Experience> getAll() {
        return experienciaService.findAll();
    }

    @GetMapping("/{id}")
    public Experience getById(@PathVariable Long id) {
        Optional<Experience> experience = experienciaService.findById(id);
        if (experience.isPresent()) {
            return experience.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        Experience savedExperience = experienciaService.save(experience);
        return new ResponseEntity<>(savedExperience, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(
            @PathVariable Long id,
            @RequestBody Experience experience) {
        Optional<Experience> existingExperience = experienciaService.findById(id);
        if (existingExperience.isPresent()) {
            experience.setId(id);
            Experience updatedExperience = experienciaService.save(experience);
            return new ResponseEntity<>(updatedExperience, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        Optional<Experience> existingExperience = experienciaService.findById(id);
        if (existingExperience.isPresent()) {
            experienciaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found with id: " + id);
        }
    }
}
