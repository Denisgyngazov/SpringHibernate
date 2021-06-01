package com.SchoolJournal.SpringHibernate.repository;

import com.SchoolJournal.SpringHibernate.model.Pupil;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


import javax.persistence.criteria.*;
import java.util.List;

public interface PupilRepository extends CrudRepository<Pupil,Long>, JpaSpecificationExecutor<Pupil> {
    List<Pupil> findByName(String name);

    List<Pupil> findByPupilInClassRoomTeacherName(String name);

}

/*

   @Query(nativeQuery = true, value = "SELECT p.id, p.name, p.surname " +
            "FROM pupil p " +
           "INNER JOIN pupil_in_class_room a ON p.pupil_id = a.id " +
           "INNER JOIN teacher t ON t.teacher_id = a.id " +
           "WHERE t.name = :name ")

 */
