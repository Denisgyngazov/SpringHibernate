package com.SchoolJournal.SpringHibernate.repository;

import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PupilInClassRoomRepository extends CrudRepository<PupilInClassRoom,Long> {

    //List<PupilInClassRoom> findByNameAndSurname(Teacher teacher);

    @Query(nativeQuery = true, value = "SELECT p.id, p.name, p.surname " +
            "FROM pupil p " +
            "INNER JOIN pupil_in_class_room a ON p.pupil_id = a.id " +
            "INNER JOIN teacher t ON t.teacher_id = a.id " +
            "WHERE t.name = :name ")
    List<PupilInClassRoom> findPupilByTeacher(@Param("name") String name);
}
