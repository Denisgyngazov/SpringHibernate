package com.SchoolJournal.SpringHibernate.service;

import com.SchoolJournal.SpringHibernate.exception.IdMismatchException;
import com.SchoolJournal.SpringHibernate.exception.NotFoundException;
import com.SchoolJournal.SpringHibernate.model.ClassRoom;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.ClassRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ClassRoomService {
    private final ClassRoomRepository classRoomRepository;

    public ClassRoomService(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }

    public Iterable<ClassRoom> findAll() {
        return classRoomRepository.findAll();
    }

    public List<ClassRoom> findByName(String name) {
        return classRoomRepository.findByName(name);
    }

    public ClassRoom create(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    public void delete(Long id) {
        ClassRoom classRoom = classRoomRepository.findById(id).orElseThrow(NotFoundException::new);
        if(classRoom.getPupil().isEmpty() && classRoom.getTeacher().isEmpty()) {
            classRoomRepository.deleteById(id);
        }
    }

    public ClassRoom updateClassRoom(ClassRoom classRoom, Long id) {
        if (classRoom.getId() != id) {
            throw new IdMismatchException("Несоответсвие id");
        }
        classRoomRepository.findById(id).orElseThrow(NotFoundException::new);
        return classRoomRepository.save(classRoom);
    }
}
