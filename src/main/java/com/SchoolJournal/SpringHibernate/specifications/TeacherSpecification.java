package com.SchoolJournal.SpringHibernate.specifications;

import com.SchoolJournal.SpringHibernate.metamodel.Teacher_;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TeacherSpecification {
    public static Specification<Teacher> findBy(String name) {
        return new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                root = criteriaQuery.from(Teacher.class);
                criteriaQuery.distinct(true);
                return criteriaBuilder.equal(root.get(Teacher_.Name), name);
            }
        };
    }
}
