package com.SchoolJournal.SpringHibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "ClassRoom")
public final class ClassRoom extends BaseModel {

    @OneToOne
    @JoinColumn(name = "pupilInClassRoom_classRoomID")
    private PupilInClassRoom pupilInClassRoom;

    @Column(nullable = false, unique = true)
    private final String name;

    public ClassRoom(long id, String name) {
        super(id);
        this.name = name;

    }

    public String getName() {
        return name;
    }

}
