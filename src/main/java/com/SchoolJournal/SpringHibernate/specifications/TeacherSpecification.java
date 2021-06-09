package com.SchoolJournal.SpringHibernate.specifications;

import com.SchoolJournal.SpringHibernate.model.ClassRoom;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import com.SchoolJournal.SpringHibernate.model.Teacher_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TeacherSpecification {
    public static Specification<Teacher> findBy(String name) {
        return new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                root = criteriaQuery.from(Teacher.class);
                criteriaQuery.distinct(true);
                return criteriaBuilder.equal(root.get(Teacher_.NAME),name);

            }
        };
    }
}
