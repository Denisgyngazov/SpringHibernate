package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseModel {

    @Id
    @Column(name = "id")
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    protected BaseModel() {
    }
}
