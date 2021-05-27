package com.SchoolJournal.SpringHibernate.repository;

import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PupilInClassRoomRepository extends CrudRepository<PupilInClassRoom,Long> {

    //List<PupilInClassRoom> findByNameAndSurname(Teacher teacher);
}
