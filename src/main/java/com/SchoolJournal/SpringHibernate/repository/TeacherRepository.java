package com.SchoolJournal.SpringHibernate.repository;

import com.SchoolJournal.SpringHibernate.model.Teacher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    List<Teacher> findByName(String name);

    @EntityGraph(value = "entity-graph")
    List<Teacher> findAll(Specification specification);
}
