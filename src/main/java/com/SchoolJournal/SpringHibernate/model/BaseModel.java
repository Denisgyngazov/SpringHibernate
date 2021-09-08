package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseModel {
    @Id
    @Column(name = "id")
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
}
