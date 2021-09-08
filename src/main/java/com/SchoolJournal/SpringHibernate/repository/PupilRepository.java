package com.SchoolJournal.SpringHibernate.repository;

import com.SchoolJournal.SpringHibernate.model.Pupil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PupilRepository extends CrudRepository<Pupil, Long>, JpaSpecificationExecutor<Pupil> {
    List<Pupil> findByName(String name);

    Page<Pupil> findAll(Pageable pageable);

    Page<Pupil> findAll(Specification specification, Pageable pageable);

    List<Pupil> findByClassRoomTeacherName(String name);
}

