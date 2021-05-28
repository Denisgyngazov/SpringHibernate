package com.SchoolJournal.SpringHibernate.controller;

import com.SchoolJournal.SpringHibernate.exception.IdMismatchException;
import com.SchoolJournal.SpringHibernate.exception.NotFoundException;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pupil")
public class PupilController {

    @Autowired
    private PupilRepository pupilRepository;

    @GetMapping()
    public  Iterable findAll() {
        return pupilRepository.findAll();
    }

    @GetMapping("/{name}")
    public List findByName(@PathVariable String name) {
        return pupilRepository.findByName(name);
    }

//    @GetMapping("/{searchPupilByTeacher}")
//    public List<Pupil> findPupilByTeacher(@RequestBody Teacher teacher) {
//       return pupilRepository.findByTeacher_Pupil_NameIn(teacher.getName());
//    }

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
