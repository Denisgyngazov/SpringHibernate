package com.SchoolJournal.SpringHibernate.repository;

import com.SchoolJournal.SpringHibernate.model.Pupil;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PupilRepository extends CrudRepository<Pupil,Long> {
    List<Pupil> findByName(String name);
}
