package com.SchoolJournal.SpringHibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "Teacher")
public final class Teacher extends BaseModel {

    @OneToOne
    @JoinColumn(name = "pupilInClassRoom_teacherID")
    private PupilInClassRoom pupilInClassRoom;

    @Column(nullable = false)
    private final String name;

    @Column(nullable = false)
    private final String surname;

    @Column(nullable = false)
    private final String discipline;

    public Teacher(long id, String name, String surname, String discipline) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.discipline = discipline;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDiscipline() {
        return discipline;
    }
}
