package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "class_room")
public final class ClassRoom extends BaseModel {

    @OneToOne(mappedBy = "classRoom")
    // @JoinTable(name = "pupil_in_class_room_teacher")
    @Getter
    @Setter
    private Teacher teacher;

    @OneToOne(mappedBy = "classRoom")
    // @JoinTable(name = "pupil_in_class_room_teacher")
    @Getter
    @Setter
    private Pupil pupil;



    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    public ClassRoom() {
    }
}
