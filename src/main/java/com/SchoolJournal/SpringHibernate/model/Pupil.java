package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pupil")
public final class Pupil extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "pupil_id")
    @Setter
    private PupilInClassRoom pupilInClassRoom;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Column(nullable = false)
    private String surname;

    public Pupil() {

    }
}
