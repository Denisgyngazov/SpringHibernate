package com.SchoolJournal.SpringHibernate.service;

import com.SchoolJournal.SpringHibernate.exception.IdMismatchException;
import com.SchoolJournal.SpringHibernate.exception.NotFoundException;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.repository.PupilRepository;
import com.SchoolJournal.SpringHibernate.specifications.PupilSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class PupilService {
    private final PupilRepository pupilRepository;

    public PupilService(PupilRepository pupilRepository) {
        this.pupilRepository = pupilRepository;
    }

    public Iterable<Pupil> findAll() {
        return pupilRepository.findAll();
    }

    public List<Pupil> findByClassRoomTeacherName(String name) {
        return pupilRepository.findByClassRoomTeacherName(name);
    }

    public Iterable<Pupil> findAllOnPage(Pageable pageable) {
        Page<Pupil> page = pupilRepository.findAll(pageable);
        return page.getContent();
    }

    public List<Pupil> findByName(String name) {
        return pupilRepository.findByName(name);
    }

    public List<Pupil> findPupilByTeacherSpecification(String name) {
        return pupilRepository.findAll(PupilSpecification.findByClassRoomTeacherName(name));
    }

    public Page<Pupil> findPupilByTeacherSpecificationAndPageable(String name, int page, int size) {
        Pageable pageWitElements = PageRequest.of(page, size);
        return pupilRepository.findAll(PupilSpecification.findByClassRoomTeacherName(name), pageWitElements);
    }

    public Pupil create(Pupil pupil) {
        return pupilRepository.save(pupil);
    }

    public void delete(Long id) {
        pupilRepository.findById(id).orElseThrow(NotFoundException::new);
        pupilRepository.deleteById(id);
    }

    public Pupil updatePupil(Pupil pupil, Long id) {
        if (pupil.getId() != id) {
            throw new IdMismatchException("Несоответсвие id");
        }
        pupilRepository.findById(id).orElseThrow(NotFoundException::new);
        return pupilRepository.save(pupil);
    }
}

