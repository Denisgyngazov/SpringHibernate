package com.SchoolJournal.SpringHibernate.service;

import com.SchoolJournal.SpringHibernate.exception.IdMismatchException;
import com.SchoolJournal.SpringHibernate.exception.NotFoundException;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.TeacherRepository;
import com.SchoolJournal.SpringHibernate.specifications.TeacherSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Iterable<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Iterable<Teacher> findByClassroomAndTeacherNameGraph(String name) {
        return teacherRepository.findAll(TeacherSpecification.findBy(name));
    }

    public List<Teacher> findByName(String name) {
        return teacherRepository.findByName(name);
    }

    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void delete(Long id) {
        teacherRepository.findById(id).orElseThrow(NotFoundException::new);
        teacherRepository.deleteById(id);
    }

    public Teacher updateTeacher(Teacher teacher, Long id) {
        if (teacher.getId() != id) {
            throw new IdMismatchException("Несоответсвие id");
        }
        teacherRepository.findById(id).orElseThrow(NotFoundException::new);
        return teacherRepository.save(teacher);
    }
}
