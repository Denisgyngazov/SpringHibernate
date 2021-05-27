package com.SchoolJournal.SpringHibernate.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
 abstract class BaseModel {

    @Id
    @Column(name = "id")
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    public BaseModel(long id) {
//        this.id = id;
//    }

    protected BaseModel() {
    }
}
