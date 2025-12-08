package com.crud_items.crud_items_back.rest;

import java.util.ArrayList;
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

import com.crud_items.crud_items_back.model.Education;
import com.crud_items.crud_items_back.service.IEducationService;

@RestController
@RequestMapping("/api/education")
public class EducationRestController {

    private final IEducationService educationService;

    public EducationRestController(IEducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/all")
    public List<Education> getAll() {
        List<Education> educations = educationService.findAll();
        List<Education> educationsToResponse = new ArrayList<>();
        educationsToResponse.add(educations.getFirst());
        return educationsToResponse;
    }

    @GetMapping("/{id}")
    public Education getById(@PathVariable Long id) {
        Optional<Education> education = educationService.findById(id);
        if (education.isPresent()) {
            return education.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education) {
        Education savedEducation = educationService.save(education);
        return new ResponseEntity<>(savedEducation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(
            @PathVariable Long id,
            @RequestBody Education education) {
        Optional<Education> existingEducation = educationService.findById(id);
        if (existingEducation.isPresent()) {
            education.setId(id);
            Education updatedEducation = educationService.save(education);
            return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        Optional<Education> existingEducation = educationService.findById(id);
        if (existingEducation.isPresent()) {
            educationService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found with id: " + id);
        }
    }
}
