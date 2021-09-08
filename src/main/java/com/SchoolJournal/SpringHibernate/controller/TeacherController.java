package com.SchoolJournal.SpringHibernate.controller;

import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public final class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Teacher>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Teacher>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(teacherService.findByName(name));
    }

    @GetMapping("/{graph}")
    public ResponseEntity<Iterable<Teacher>> findByClassroomAndTeacherNameGraph(@PathVariable String name) {
        return ResponseEntity.ok(teacherService.findByClassroomAndTeacherNameGraph(name));
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.create(teacher));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher, @PathVariable Long id) {
        return ResponseEntity.ok(teacherService.updateTeacher(teacher, id));
    }
}
