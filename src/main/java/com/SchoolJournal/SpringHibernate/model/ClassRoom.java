package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "class_room")
public final class ClassRoom extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "class_Room_id")
    @Setter
    private PupilInClassRoom pupilInClassRoom;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

//    public ClassRoom(long id, String name) {
//        super(id);
//        this.name = name;
//
//    }

    public ClassRoom() {
    }
}
