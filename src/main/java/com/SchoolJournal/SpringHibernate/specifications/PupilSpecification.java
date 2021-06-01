package com.SchoolJournal.SpringHibernate.specifications;

import com.SchoolJournal.SpringHibernate.metamodel.Teacher_;
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
                Join<Pupil, PupilInClassRoom> pupil_PupilInClassRoomJoin = root.join("pupilInClassRoom");
                Join<PupilInClassRoom, Teacher> teacher_PupilInClassRoomJoin = pupil_PupilInClassRoomJoin.join("teacher");
                return criteriaBuilder.equal(teacher_PupilInClassRoomJoin.get(Teacher_.Name),name);
            }
        };
    }
}
