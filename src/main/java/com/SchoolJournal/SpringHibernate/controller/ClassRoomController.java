package com.SchoolJournal.SpringHibernate.controller;

import com.SchoolJournal.SpringHibernate.exception.IdMismatchException;
import com.SchoolJournal.SpringHibernate.exception.NotFoundException;
import com.SchoolJournal.SpringHibernate.model.ClassRoom;
import com.SchoolJournal.SpringHibernate.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassRoomController  {
    @Autowired
    private ClassRoomRepository classRoomRepository;

    @GetMapping()
    public  Iterable findAll() {
        return classRoomRepository.findAll();
    }

    @GetMapping("/{name}")
    public List findByName(@PathVariable String name) {
        return classRoomRepository.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassRoom create(@RequestBody ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        classRoomRepository.findById(id).orElseThrow(NotFoundException::new);
        classRoomRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ClassRoom updateClassRoom(@RequestBody ClassRoom classRoom, @PathVariable Long id) {
        if(classRoom.getId() != id) {
            throw new IdMismatchException("Несоответсвие id");
        }
        classRoomRepository.findById(id).orElseThrow(NotFoundException::new);
        return classRoomRepository.save(classRoom);
    }
}
