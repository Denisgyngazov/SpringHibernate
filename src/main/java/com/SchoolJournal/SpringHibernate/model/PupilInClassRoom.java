package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pupil_in_class_room")
public final class PupilInClassRoom  extends BaseModel {

    @OneToOne(mappedBy = "pupilInClassRoom")
    @Getter
    @Setter
    private Pupil pupil;

    @OneToOne(mappedBy = "pupilInClassRoom")
    @Getter
    @Setter
    private ClassRoom classRoom;

    @OneToOne(mappedBy = "pupilInClassRoom")
    @Getter
    @Setter
    private Teacher teacher;

    public PupilInClassRoom() {

    }

    public PupilInClassRoom(long id, long pupilID, long classRoomID, long teacherID) {
        super(id);
    }

    public PupilInClassRoom(ClassRoom classRoom, Teacher teacher, Pupil pupil) {
    }
}
