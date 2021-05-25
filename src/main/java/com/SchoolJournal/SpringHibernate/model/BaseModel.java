package com.SchoolJournal.SpringHibernate.model;

import javax.persistence.*;

@Entity
abstract class BaseModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;

    public BaseModel(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
