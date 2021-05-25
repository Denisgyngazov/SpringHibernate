package com.SchoolJournal.SpringHibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "Pupil")
public final class Pupil extends BaseModel {

    @OneToOne
    @JoinColumn(name = "pupilInClassRoom_pupilID")
    private PupilInClassRoom pupilInClassRoom;

    @Column(nullable = false)
    private final String name;

    @Column(nullable = false)
    private final String surname;

    public Pupil(long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
