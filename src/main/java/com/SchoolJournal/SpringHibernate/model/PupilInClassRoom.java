package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pupil_in_class_room")
public final class PupilInClassRoom  extends BaseModel {

    @OneToOne(mappedBy = "pupilInClassRoom")
   // @JoinTable(name = "pupil_in_class_room_pupil")
    @Getter
    @Setter
    private Pupil pupil;

    @OneToOne(mappedBy = "pupilInClassRoom")
   // @JoinTable(name = "pupil_in_class_room_class_room")
    @Getter
    @Setter
    private ClassRoom classRoom;

    @OneToOne(mappedBy = "pupilInClassRoom")
   // @JoinTable(name = "pupil_in_class_room_teacher")
    @Getter
    @Setter
    private Teacher teacher;

    public PupilInClassRoom() {

    }
}
