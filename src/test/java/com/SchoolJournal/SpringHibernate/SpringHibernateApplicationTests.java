package com.SchoolJournal.SpringHibernate;

import com.SchoolJournal.SpringHibernate.model.ClassRoom;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.repository.ClassRoomRepository;
import com.SchoolJournal.SpringHibernate.repository.PupilRepository;
import com.SchoolJournal.SpringHibernate.repository.TeacherRepository;

import com.SchoolJournal.SpringHibernate.specifications.PupilSpecification;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;


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
		pupil.setName("Hello");
		pupil.setSurname("hello");

		Teacher teacher = new Teacher();
		teacher.setName("Alla");
		teacher.setSurname("Aronova");
		teacher.setDiscipline("Mat");

		ClassRoom classRoom = new ClassRoom();
		classRoom.setName("1A");
		teacher.setClassRoom(classRoom);
		pupil.setClassRoom(classRoom);

		classRoomRepository.save(classRoom);
		teacherRepository.save(teacher);
		pupilRepository.save(pupil);
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
			System.out.println(classRoom1.getPupil().getId() + " " +
					classRoom1.getPupil().getName() + " " +
					classRoom1.getPupil().getSurname());
			System.out.println(classRoom1.getTeacher().getId() + " " +
					classRoom1.getTeacher().getName() + " " +
					classRoom1.getTeacher().getSurname() + " " +
					classRoom1.getTeacher().getDiscipline());
			System.out.println("----------------------------");
		}
	}

	@Test
	public void findPupilByTeacher() {
		System.out.println("Поиск ученика по учителю");
		System.out.println("----------------------------");
		Iterable<Pupil> finByTeacherOnPupil = pupilRepository.findByClassRoomTeacherName("Alla");
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
		Iterable<Pupil> finByTeacherOnPupil = pupilRepository.findAll(PupilSpecification.findByClassRoomTeacherName("Alla"));
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
		Iterable<Pupil> findByAllPupils = pupilRepository.findAll(PupilSpecification.findByClassRoomTeacherName("Alla"),firstPageWithOneElements);
		for(Pupil pupil: findByAllPupils) {
			System.out.println(pupil.getId() + " " +
					pupil.getName() + " " +
					pupil.getSurname());
		}
	}
}
