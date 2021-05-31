package com.SchoolJournal.SpringHibernate.specifications;

import com.SchoolJournal.SpringHibernate.model.Pupil;
import com.SchoolJournal.SpringHibernate.model.PupilInClassRoom;
import com.SchoolJournal.SpringHibernate.model.Teacher;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public final class PupilSpecification {
    public static Specification<Pupil> findPupilByTeacherSpecification(String name) {
        return new Specification<Pupil>() {
            @Override
            public Predicate toPredicate(Root<Pupil> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                root = criteriaQuery.from(Pupil.class);
                Join<Pupil, PupilInClassRoom> pupil_PupilInClassRoomJoin = root.join("pupil.pupil_id");
                Join<Teacher, PupilInClassRoom> teacher_PupilInClassRoomJoin = root.join("teacher.teacher_id");
                return criteriaBuilder.equal(root.get(Teacher.class.getName()),name);
            }
        };
    }
}
