package com.SchoolJournal.SpringHibernate;

import com.SchoolJournal.SpringHibernate.model.ClassRoom;
import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.service.ClassRoomService;
import com.SchoolJournal.SpringHibernate.service.PupilService;
import com.SchoolJournal.SpringHibernate.service.TeacherService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringHibernateApplicationTests {

    @Container
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("schooljournal")
            .withUsername("root")
            .withPassword("my-secret-pw");

    @DynamicPropertySource
    static void mySQLProperties(DynamicPropertyRegistry registry) {
        mySQLContainer.start();
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
    }

    @Autowired
    private PupilService pupilService;

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private TeacherService teacherService;

    @BeforeAll
    @Rollback(value = false)
    public void contextLoads() {
        Pupil pupil = new Pupil();
        pupil.setName("Anton");
        pupil.setSurname("Savridov");

        Pupil pupil1 = new Pupil();
        pupil1.setName("Elena");
        pupil1.setSurname("Antonova");

        Pupil pupil2 = new Pupil();
        pupil2.setName("Svetlana");
        pupil2.setSurname("Antonova");

        Teacher teacher = new Teacher();
        teacher.setName("Alla");
        teacher.setSurname("Aronova");
        teacher.setDiscipline("Mathematics");

        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("1A");

        pupil.setClassRoom(classRoom);
        pupil1.setClassRoom(classRoom);
        pupil2.setClassRoom(classRoom);
        teacher.setClassRoom(classRoom);

        classRoomService.create(classRoom);
        teacherService.create(teacher);
        pupilService.create(pupil);
        pupilService.create(pupil1);
        pupilService.create(pupil2);
    }

    @Test
    @Transactional
    public void createEntity() {
        System.out.println("Ученики");
        System.out.println("----------------------------");
        Iterable<Pupil> allPupil = pupilService.findAll();
        allPupil.forEach(p -> System.out.println(p.getId() + " "
                + p.getName() + " "
                + p.getSurname()));
        System.out.println("----------------------------");

        Iterable<Teacher> allTeacher = teacherService.findAll();
        System.out.println("Учителя");
        System.out.println("----------------------------");
        allTeacher.forEach(t -> System.out.println(t.getId() + " "
                + t.getName() + " "
                + t.getSurname() + " "
                + t.getDiscipline()));
        System.out.println("----------------------------");

        System.out.println("Класс");
        System.out.println("----------------------------");
        Iterable<ClassRoom> allClassRoom = classRoomService.findAll();
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
        Iterable<Pupil> findPupilByTeacher = pupilService.findByClassRoomTeacherName("Alla");
        findPupilByTeacher.forEach(p -> System.out.println(p.getId() + " "
                + p.getName() + " "
                + p.getSurname()));
    }

    @Test
    public void findPupilByTeacherSpecification() {
        System.out.println("Поиск ученика по учителю используя спецификации");
        System.out.println("----------------------------");
        Iterable<Pupil> finByTeacherOnPupil = pupilService.findPupilByTeacherSpecification("Alla");
        finByTeacherOnPupil.forEach(p -> System.out.println(p.getId() + " "
                + p.getName() + " "
                + p.getSurname()));
    }

    @Test
    public void findAllPupilsPageable() {
        System.out.println("Поиск всех учеников используя пагнацию");
        System.out.println("----------------------------");
        Iterable<Pupil> findByAllPupils = pupilService.findPupilByTeacherSpecificationAndPageable("Alla",0,1);
        findByAllPupils.forEach(p -> System.out.println(p.getId() + " "
                + p.getName() + " "
                + p.getSurname()));
    }

    @Test
    public void findByClassroomAndTeacherGraph() {
        System.out.println("Поиск учителя и класса используя граф");
        System.out.println("----------------------------");
        Iterable<Teacher> findByFinal = teacherService.findByClassroomAndTeacherNameGraph("Alla");
        findByFinal.forEach(c -> System.out.println(c.getClassRoom().getId() + " " + c.getClassRoom().getName()));
        findByFinal.forEach(t -> System.out.println(t.getId() + " " + t.getSurname() + " " + t.getDiscipline()));
    }
}
