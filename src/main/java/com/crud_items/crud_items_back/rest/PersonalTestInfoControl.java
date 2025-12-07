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

import com.crud_items.crud_items_back.model.PersonalInfo;
import com.crud_items.crud_items_back.service.IPersonalInfoService;

@RestController
@RequestMapping("/api/test-personal-info")
public class PersonalTestInfoControl {

    private final IPersonalInfoService personalInfoService;

    public PersonalTestInfoControl(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/all")
    public List<PersonalInfo> getAll() {
        return personalInfoService.findAll();
    }

    @GetMapping("{id}")
    public PersonalInfo getById(@PathVariable Long id) {
        Optional<PersonalInfo> personalInfo = personalInfoService.findById(id);
        if (personalInfo.isPresent()) {
            return personalInfo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonalInfo not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo) {
        PersonalInfo savedPersonalInfo = personalInfoService.save(personalInfo);
        return new ResponseEntity<>(savedPersonalInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalInfo> updatePersonalInfo(
            @PathVariable Long id,
            @RequestBody PersonalInfo personalInfo) {
        Optional<PersonalInfo> existingPersonalInfo = personalInfoService.findById(id);
        if (existingPersonalInfo.isPresent()) {
            personalInfo.setId(id);
            PersonalInfo updatedPersonalInfo = personalInfoService.save(personalInfo);
            return new ResponseEntity<>(updatedPersonalInfo, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonalInfo not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalInfo(@PathVariable Long id) {
        Optional<PersonalInfo> existingPersonalInfo = personalInfoService.findById(id);
        if (existingPersonalInfo.isPresent()) {
            personalInfoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonalInfo not found with id: " + id);
        }
    }
}
