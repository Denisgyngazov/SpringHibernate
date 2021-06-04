package com.SchoolJournal.SpringHibernate;

import com.SchoolJournal.SpringHibernate.model.ClassRoom;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.ClassRoomRepository;
import com.SchoolJournal.SpringHibernate.repository.PupilRepository;
import com.SchoolJournal.SpringHibernate.repository.TeacherRepository;

import com.SchoolJournal.SpringHibernate.specifications.PupilSpecification;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

	@BeforeAll
	@Rollback(value = false)
	public void contextLoads() {
		Pupil pupil = new Pupil();
		pupil.setName("Anton");
		pupil.setSurname("Savridov");

		Pupil pupil1 = new Pupil();
		pupil1.setName("Elena");
		pupil1.setSurname("Antonova");

		Teacher teacher = new Teacher();
		teacher.setName("Alla");
		teacher.setSurname("Aronova");
		teacher.setDiscipline("Mathematics");

		ClassRoom classRoom = new ClassRoom();
		classRoom.setName("1A");

		pupil.setClassRoom(classRoom);
		pupil1.setClassRoom(classRoom);
		teacher.setClassRoom(classRoom);

		classRoomRepository.save(classRoom);
		teacherRepository.save(teacher);
		pupilRepository.save(pupil);
		pupilRepository.save(pupil1);

	}

	@Test
	public void createEntity() {
		System.out.println("Ученики");
		System.out.println("----------------------------");
		Iterable<Pupil> allPupil = pupilRepository.findAll();
		allPupil.forEach(p-> System.out.println(p.getId() + " "
				+ p.getName() + " "
				+ p.getSurname()));
		System.out.println("----------------------------");

		Iterable<Teacher> allTeacher = teacherRepository.findAll();
		System.out.println("Учителя");
		System.out.println("----------------------------");
		allTeacher.forEach(t -> System.out.println(t.getId() + " "
				+ t.getName() + " "
				+ t.getSurname() + " "
				+ t.getDiscipline()));
		System.out.println("----------------------------");

		System.out.println("Класс");
		System.out.println("----------------------------");
		Iterable<ClassRoom> allClassRoom = classRoomRepository.findAll();
		allClassRoom.forEach(c -> System.out.println(c.getId() + " " + c.getName()));
		allClassRoom.forEach(c -> c.getTeacher().forEach(t -> System.out.println(t.getId() + " "
				+ t.getName() + " " +
				t.getSurname() + " " +
				t.getDiscipline())));
		allClassRoom.forEach(c -> c.getPupil().forEach(p -> System.out.println(p.getId() + " " +
				p.getName() + " " +
				p.getSurname())));
		System.out.println("----------------------------");
	}


	@Test
	public void findPupilByTeacher() {
		System.out.println("Поиск ученика по учителю");
		System.out.println("----------------------------");
		Iterable<Pupil> findPupilByTeacher = pupilRepository.findByClassRoomTeacherName("Alla");
		findPupilByTeacher.forEach(p-> System.out.println(p.getId() + " "
				+ p.getName() + " "
				+ p.getSurname()));
	}

	@Test
	public void findPupilByTeacherSpecification() {
		System.out.println("Поиск ученика по учителю используя спецификации");
		System.out.println("----------------------------");
		Iterable<Pupil> finByTeacherOnPupil = pupilRepository.findAll(PupilSpecification.findByClassRoomTeacherName("Alla"));
		finByTeacherOnPupil.forEach(p-> System.out.println(p.getId() + " "
				+ p.getName()+ " "
				+ p.getSurname()));
	}

	@Test
	public void findAllPupilsPageable() {
		Pageable firstPageWithOneElements = PageRequest.of(0,1);

		System.out.println("Поиск всех учеников используя пагнацию");
		System.out.println("----------------------------");
		Iterable<Pupil> findByAllPupils = pupilRepository.findAll(PupilSpecification.findByClassRoomTeacherName("Alla"),firstPageWithOneElements);
		findByAllPupils.forEach(p -> System.out.println(p.getId() + " "
				+ p.getName() + " "
				+ p.getSurname()));
	}
}
