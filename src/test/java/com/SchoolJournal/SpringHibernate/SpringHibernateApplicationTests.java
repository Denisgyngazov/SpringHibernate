package com.SchoolJournal.SpringHibernate;

import com.SchoolJournal.SpringHibernate.model.ClassRoom;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.ClassRoomRepository;
import com.SchoolJournal.SpringHibernate.repository.PupilInClassRoomRepository;
import com.SchoolJournal.SpringHibernate.repository.PupilRepository;
import com.SchoolJournal.SpringHibernate.repository.TeacherRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;



@SpringBootTest
class SpringHibernateApplicationTests {

	@Autowired
	private PupilRepository pupilRepository;

	@Autowired
	private ClassRoomRepository classRoomRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private PupilInClassRoomRepository pupilInClassRoomRepository;


	@Rollback(value = false)
	public PupilInClassRoom contextLoads() {
		Pupil pupil = new Pupil();
		pupil.setName("Hello");
		pupil.setSurname("hello");

		Teacher teacher = new Teacher();
		teacher.setName("Alla");
		teacher.setSurname("Aronova");
		teacher.setDiscipline("Mat");

		ClassRoom classRoom = new ClassRoom();
		classRoom.setName("1A");

		PupilInClassRoom pupilInClassRoom = new PupilInClassRoom();
		pupilInClassRoom.setPupil(pupil);
		pupilInClassRoom.setTeacher(teacher);
		pupilInClassRoom.setClassRoom(classRoom);

		pupilRepository.save(pupil);
		teacherRepository.save(teacher);
		classRoomRepository.save(classRoom);


		return pupilInClassRoomRepository.save(pupilInClassRoom);
	}

	@Test
	public void testFindBy() {
		PupilInClassRoom pupilInClassRoom = contextLoads();
		System.out.println((pupilRepository.findPupilByTeacher(pupilInClassRoom.getTeacher().getName())));
	}
}
