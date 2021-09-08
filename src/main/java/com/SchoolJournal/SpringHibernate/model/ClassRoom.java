package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_room")
public final class ClassRoom extends BaseModel {
    @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Teacher> teacher = new ArrayList<>();

    @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Pupil> pupil = new ArrayList<>();

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;
}
