package com.SchoolJournal.SpringHibernate.repository;

import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PupilRepository extends CrudRepository<Pupil,Long> {
    List<Pupil> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT p.id, p.pupil_id, p.name, p.surname " +
            "FROM pupil p " +
            "INNER JOIN pupil_in_class_room in ON p.pupil_id = in.id " +
            "INNER JOIN teacher t ON t.teacher_id = in.id " +
            "WHERE t.name = :name ")
    List<PupilInClassRoom> findPupilByTeacher(@Param("name") String name);
}



/*
"SELECT p.id, p.name, p.surname FROM pupil AS p " +
                "INNER JOIN pupilInClassRoom AS in ON p.id = in.pupilID " +
                "INNER JOIN teacher AS t ON t.id = in.teacherID" +
                "WHERE t.name = :myParam"
 */
