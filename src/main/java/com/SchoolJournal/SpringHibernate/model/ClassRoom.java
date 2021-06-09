package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "class_room")
public final class ClassRoom extends BaseModel {

    @OneToMany(mappedBy = "classRoom", fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<Teacher> teacher = new HashSet<>();

    @OneToMany(mappedBy = "classRoom",fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<Pupil> pupil = new HashSet<>();

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    public ClassRoom() {
    }
}
