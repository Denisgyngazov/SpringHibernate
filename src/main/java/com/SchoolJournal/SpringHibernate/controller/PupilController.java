package com.SchoolJournal.SpringHibernate.controller;

import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.service.PupilService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pupil")
public final class PupilController {
    private final PupilService pupilService;

    public PupilController(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Pupil>> findAll() {
        return ResponseEntity.ok(pupilService.findAll());
    }

    @GetMapping("/{page}")
    public ResponseEntity<Iterable<Pupil>> findAllOnPage(@PageableDefault(page = 1) Pageable pageable) {
        return ResponseEntity.ok(pupilService.findAllOnPage(pageable));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Pupil>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(pupilService.findByName(name));
    }

    @GetMapping("/{findPupilByTeacher}")
    public ResponseEntity<List<Pupil>> findPupilByTeacher(@RequestBody String name) {
        return ResponseEntity.ok(pupilService.findPupilByTeacherSpecification(name));
    }

    @PostMapping
    public ResponseEntity<Pupil> create(@RequestBody Pupil pupil) {
        return ResponseEntity.ok(pupilService.create(pupil));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pupilService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pupil> updatePupil(@RequestBody Pupil pupil, @PathVariable Long id) {
        return ResponseEntity.ok(pupilService.updatePupil(pupil, id));
    }
}
