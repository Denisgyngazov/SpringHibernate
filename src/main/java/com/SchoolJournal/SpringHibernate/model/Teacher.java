package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public final class Teacher extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @Setter
    private PupilInClassRoom pupilInClassRoom;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @Getter
    @Setter
    private String surname;

    @Column(nullable = false)
    @Getter
    @Setter
    private String discipline;

//    public Teacher(long id, String name, String surname, String discipline) {
//        super(id);
//        this.name = name;
//        this.surname = surname;
//        this.discipline = discipline;
//    }

    public Teacher() {
    }
}
