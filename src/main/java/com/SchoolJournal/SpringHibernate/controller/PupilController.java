package com.SchoolJournal.SpringHibernate.controller;

import com.SchoolJournal.SpringHibernate.exception.IdMismatchException;
import com.SchoolJournal.SpringHibernate.exception.NotFoundException;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.PupilRepository;
import com.SchoolJournal.SpringHibernate.specifications.PupilSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;

@RestController
@RequestMapping("/pupil")
public class PupilController {

    @Autowired
    private PupilRepository pupilRepository;

    @GetMapping()
    public Iterable<Pupil> findAll(@PageableDefault (page = 1) Pageable pageable) {
        Page<Pupil> page = pupilRepository.findAll(pageable);
        return page.getContent();
    }

    @GetMapping("/{name}")
    public List<Pupil> findByName(@PathVariable String name) {
        return pupilRepository.findByName(name);
    }

    @GetMapping("/{searchPupilByTeacher}")
    public List<Pupil> findPupilByTeacher(@RequestBody Teacher teacher) {
        return pupilRepository.findAll(PupilSpecification.findPupilByTeacherSpecification("Alla"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pupil create(@RequestBody Pupil pupil) {
        return pupilRepository.save(pupil);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pupilRepository.findById(id).orElseThrow(NotFoundException::new);
        pupilRepository.deleteById(id);

    }

    @PutMapping("/{id}")
    public Pupil updatePupil(@RequestBody Pupil pupil, @PathVariable Long id) {
        if(pupil.getId() != id) {
            throw new IdMismatchException("Несоответсвие id");
        }
        pupilRepository.findById(id).orElseThrow(NotFoundException::new);
        return pupilRepository.save(pupil);
    }
}
