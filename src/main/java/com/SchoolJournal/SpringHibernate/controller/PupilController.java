package com.SchoolJournal.SpringHibernate.controller;

import com.SchoolJournal.SpringHibernate.exception.IdMismatchException;
import com.SchoolJournal.SpringHibernate.exception.NotFoundException;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.PupilRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;

@RestController
@RequestMapping("/pupil")
public class PupilController {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PupilRepository pupilRepository;

    @GetMapping()
    public Iterable<Pupil> findAll() {
        return pupilRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<Pupil> findByName(@PathVariable String name) {
        return pupilRepository.findByName(name);
    }

//    @GetMapping("/{searchPupilByTeacher}")
//    public List<Pupil> findPupilByTeacher(@RequestBody Teacher teacher) {
//        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
//        CriteriaQuery<Pupil> criteria = builder.createQuery(Pupil.class);
//        Root<Pupil> pupilInClassRoomRoot = criteria.from(Pupil.class);
//        criteria.select(pupilInClassRoomRoot);
//        criteria.where(builder.equal(pupilInClassRoomRoot.get(teacher.getName()),"Alla"));
//        return entityManagerFactory.createEntityManager().createQuery(criteria).getResultList();
//    }

    private Specification<Pupil> findPupilByTeacherSpecification(String name) {
        return new Specification<Pupil>() {
            @Override
            public Predicate toPredicate(Root<Pupil> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                root = criteriaQuery.from(Pupil.class);
                Join<Pupil, PupilInClassRoom> pupil_PupilInClassRoomJoin = root.join("pupil_id");
                Join<Teacher, PupilInClassRoom> teacher_PupilInClassRoomJoin = root.join("teacher_id");
                return criteriaBuilder.equal(root.get(Teacher.class.getName()), name);
            }
        };
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
