package com.SchoolJournal.SpringHibernate.repository;

import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PupilRepository extends CrudRepository<Pupil,Long> {
    List<Pupil> findByName(String name);
//
//    @Query(nativeQuery = true, value = "SELECT p.id, p.name, p.surname " +
//            "FROM pupil p " +
//            "INNER JOIN pupil_in_class_room a ON p.pupil_id = a.id " +
//            "INNER JOIN teacher t ON t.teacher_id = a.id " +
//            "WHERE t.name = :name ")
//    List<Pupil> findPupilByTeacher(@Param("name") String name);

    List<Pupil> findBypupilInClassRoom_Teacher_Name(String name);
}




/*
"SELECT p.id, p.name, p.surname FROM pupil AS p " +
                "INNER JOIN pupilInClassRoom AS in ON p.id = in.pupilID " +
                "INNER JOIN teacher AS t ON t.id = in.teacherID" +
                "WHERE t.name = :myParam"
 */
