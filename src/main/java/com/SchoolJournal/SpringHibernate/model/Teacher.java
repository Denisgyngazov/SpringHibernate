package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public final class Teacher extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    @Setter
    @Getter
    private ClassRoom classRoom;

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

    public Teacher() {

    }
}
