package com.SchoolJournal.SpringHibernate.controller;

import com.SchoolJournal.SpringHibernate.model.ClassRoom;
import com.SchoolJournal.SpringHibernate.service.ClassRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public final class ClassRoomController {
    private final ClassRoomService classRoomService;

    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<ClassRoom>> findAll() {
        return ResponseEntity.ok(classRoomService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ClassRoom>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(classRoomService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<ClassRoom> create(@RequestBody ClassRoom classRoom) {
        return ResponseEntity.ok(classRoomService.create(classRoom));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        classRoomService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoom> updateClassRoom(@RequestBody ClassRoom classRoom, @PathVariable Long id) {
        return ResponseEntity.ok(classRoomService.updateClassRoom(classRoom, id));
    }
}
