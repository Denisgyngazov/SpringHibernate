package com.SchoolJournal.SpringHibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "PupilInClassRoom")
public final class PupilInClassRoom  extends BaseModel {
    @OneToOne(mappedBy = "pupilInClassRoom")
    Pupil pupil;

    @OneToOne(mappedBy = "pupilInClassRoom")
    ClassRoom classRoom;

    @OneToOne(mappedBy = "pupilInClassRoom")
    Teacher teacher;

    @Column(nullable = false)
    private final long pupilID;

    @Column(nullable = false)
    private final long classRoomID;

    @Column(nullable = false)
    private final long teacherID;

    public PupilInClassRoom(long id, long pupilID, long classRoomID, long teacherID) {
        super(id);
        this.pupilID = pupilID;
        this.classRoomID = classRoomID;
        this.teacherID = teacherID;
    }

    public long getPupilID() {
        return pupilID;
    }

    public long getClassRoomID() {
        return classRoomID;
    }

    public long getTeacherID() {
        return teacherID;
    }
}
