package com.SchoolJournal.SpringHibernate;

import com.SchoolJournal.SpringHibernate.specifications.PupilSpecification;
import com.SchoolJournal.SpringHibernate.model.ClassRoom;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.ClassRoomRepository;
import com.SchoolJournal.SpringHibernate.repository.PupilInClassRoomRepository;
import com.SchoolJournal.SpringHibernate.repository.PupilRepository;
import com.SchoolJournal.SpringHibernate.repository.TeacherRepository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringHibernateApplicationTests {

	@Autowired
	private PupilRepository pupilRepository;

	@Autowired
	private ClassRoomRepository classRoomRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private PupilInClassRoomRepository pupilInClassRoomRepository;

//	@Autowired
//	private EntityManagerFactory emf;


	@BeforeAll
	@Rollback(value = false)
	public void contextLoads() {
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
		teacher.setPupilInClassRoom(pupilInClassRoom);
		pupil.setPupilInClassRoom(pupilInClassRoom);
		classRoom.setPupilInClassRoom(pupilInClassRoom);

		pupilInClassRoomRepository.save(pupilInClassRoom);
		teacherRepository.save(teacher);
		pupilRepository.save(pupil);
		classRoomRepository.save(classRoom);
	}

	@Test
	public void createEntity() {
		Iterable<Pupil> allPupil = pupilRepository.findAll();
		for (Pupil pupil1 : allPupil) {
			System.out.println("Ученики");
			System.out.println("----------------------------");
			System.out.println(pupil1.getId() + " " +
					pupil1.getName() + " " +
					pupil1.getSurname());
			System.out.println("----------------------------");
		}

		Iterable<Teacher> allTeacher = teacherRepository.findAll();
		for (Teacher teacher1 : allTeacher) {
			System.out.println("Учителя");
			System.out.println("----------------------------");
			System.out.println(teacher1.getId() + " " +
					teacher1.getName() + " " +
					teacher1.getSurname() + " " +
					teacher1.getDiscipline());
			System.out.println("----------------------------");
		}
		Iterable<ClassRoom> allClassRoom = classRoomRepository.findAll();
		for (ClassRoom classRoom1 : allClassRoom) {
			System.out.println("Класс");
			System.out.println("----------------------------");
			System.out.println(classRoom1.getId() + " "
					+ classRoom1.getName());
			System.out.println("----------------------------");
		}

		Iterable<PupilInClassRoom> allPupilInClassRoom = pupilInClassRoomRepository.findAll();
		for (PupilInClassRoom pupilInClassRoom1 : allPupilInClassRoom) {
			System.out.println("Ученики в классе");
			System.out.println("----------------------------");
			System.out.println(pupilInClassRoom1.getPupil().getId() + " " +
					pupilInClassRoom1.getPupil().getName() + " " +
					pupilInClassRoom1.getPupil().getSurname());

			System.out.println(pupilInClassRoom1.getTeacher().getId() + " " +
					pupilInClassRoom1.getTeacher().getName() + " " +
					pupilInClassRoom1.getTeacher().getSurname() + " " +
					pupilInClassRoom1.getTeacher().getDiscipline());
			System.out.println(pupilInClassRoom1.getClassRoom().getId() + " " +
					pupilInClassRoom1.getClassRoom().getName());
			System.out.println("----------------------------");
 		}

	}

	@SneakyThrows
	@Test
	public void findPupilByTeacher() {
		System.out.println("Поиск ученика по учителю");
		System.out.println("----------------------------");
		Iterable<Pupil> finByTeacherOnPupil = pupilRepository.findByPupilInClassRoomTeacherName("Alla");
		for (Pupil pupil1 : finByTeacherOnPupil) {
			System.out.println(pupil1.getId() + " " +
					pupil1.getName() + " " +
					pupil1.getSurname());
		}

	}

	@Test
	public void findPupilByTeacherSpecification() {
		System.out.println("Поиск ученика по учителю используя спецификации");
		System.out.println("----------------------------");
		Iterable<Pupil> finByTeacherOnPupil = pupilRepository.findAll(PupilSpecification.findPupilByTeacherSpecification("Alla"));
		for(Pupil pupil: finByTeacherOnPupil) {
			System.out.println(pupil.getId() + " " +
					pupil.getName() + " " +
					pupil.getSurname());
		}
	}

	@Test
	public void findAllPupilsPageable() {
		Pageable firstPageWithOneElements = PageRequest.of(0,1);

		System.out.println("Поиск всех учеников используя пагнацию");
		System.out.println("----------------------------");
		Iterable<Pupil> findByAllPupils = pupilRepository.findAll(PupilSpecification.findPupilByTeacherSpecification("Alla"),firstPageWithOneElements);
		for(Pupil pupil: findByAllPupils) {
			System.out.println(pupil.getId() + " " +
					pupil.getName() + " " +
					pupil.getSurname());
		}
	}
}
