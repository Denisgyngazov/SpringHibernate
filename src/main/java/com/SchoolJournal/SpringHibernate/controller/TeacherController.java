package com.SchoolJournal.SpringHibernate.controller;

import com.SchoolJournal.SpringHibernate.exception.IdMismatchException;
import com.SchoolJournal.SpringHibernate.exception.NotFoundException;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping()
    public  Iterable findAll() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{name}")
    public List findByName(@PathVariable String name) {
        return teacherRepository.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher create(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherRepository.findById(id).orElseThrow(NotFoundException::new);
        teacherRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@RequestBody Teacher teacher, @PathVariable Long id) {
        if(teacher.getId() != id) {
            throw new IdMismatchException("Несоответсвие id");
        }
        teacherRepository.findById(id).orElseThrow(NotFoundException::new);
        return teacherRepository.save(teacher);
    }
}
