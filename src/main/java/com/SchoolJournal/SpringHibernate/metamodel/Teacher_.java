package com.SchoolJournal.SpringHibernate.metamodel;

import com.SchoolJournal.SpringHibernate.model.BaseModel;
import com.SchoolJournal.SpringHibernate.model.Teacher;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Teacher.class)
public abstract class Teacher_ {
    public static volatile SingularAttribute<Teacher,String> name;
    public static volatile SingularAttribute<Teacher,String> surmane;
    public static volatile SingularAttribute<Teacher,String> discipline;
    public static volatile SingularAttribute<BaseModel,Integer> id;

    public static final String Name = "name";
    public static final String Surname = "surname";
    public static final String Discipline = "discipline";
    public static final String Id = "id";
}
