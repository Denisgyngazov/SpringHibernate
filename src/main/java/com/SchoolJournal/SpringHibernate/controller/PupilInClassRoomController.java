package com.SchoolJournal.SpringHibernate.controller;

import com.SchoolJournal.SpringHibernate.exception.IdMismatchException;
import com.SchoolJournal.SpringHibernate.exception.NotFoundException;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.PupilInClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pupilInClassRoom")
public class PupilInClassRoomController {

    @Autowired
    private PupilInClassRoomRepository pupilInClassRoomRepository;

    @GetMapping()
    public  Iterable findAll() {
        return pupilInClassRoomRepository.findAll();
    }

    @GetMapping("/{searchId}")
    public Optional<PupilInClassRoom> findById(@PathVariable Long id) {
        return pupilInClassRoomRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PupilInClassRoom create(@RequestBody PupilInClassRoom pupilInClassRoom) {
        return pupilInClassRoomRepository.save(pupilInClassRoom);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pupilInClassRoomRepository.findById(id).orElseThrow(NotFoundException::new);
        pupilInClassRoomRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public PupilInClassRoom updatePupilInClassRoom(@RequestBody PupilInClassRoom pupilInClassRoom, @PathVariable Long id) {
        if(pupilInClassRoom.getId() != id) {
            throw new IdMismatchException("Несоответсвие id");
        }
        pupilInClassRoomRepository.findById(id).orElseThrow(NotFoundException::new);
        return pupilInClassRoomRepository.save(pupilInClassRoom);
    }
}
