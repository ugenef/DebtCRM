package dcrm.service.dal.impl;

import dcrm.service.bl.SessionUtil;
import dcrm.service.businessmodels.Group;
import dcrm.service.businessmodels.Student;
import dcrm.service.dal.abstractions.CrudRepository;
import models.AcademicGroupEntity;
import models.StudentEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Database implements CrudRepository {
    @Override
    public Student[] findAllStudents() {
        SessionUtil sessionUtil = new SessionUtil();
        //open session with a transaction
        sessionUtil.openTransactionSession();

        String sql = "SELECT * FROM student";

        Session session = sessionUtil.getSession();
        Query query = session.createNativeQuery(sql).addEntity(StudentEntity.class);
        List<StudentEntity> studentsFromDb = query.list();

        Student[] students = new Student[studentsFromDb.size()];

        for (int i = 0; i < studentsFromDb.size(); i++) {
            students[i] = createStudentModelFromEntity(studentsFromDb.get(i));
        }

        //close session with a transaction
        sessionUtil.closeTransactionSession();

        return students;
    }

    @Override
    public Group[] findAllGroups() {
        SessionUtil sessionUtil = new SessionUtil();
        //open session with a transaction
        sessionUtil.openTransactionSession();

        String sql = "SELECT * FROM academic_group";

        Session session = sessionUtil.getSession();
        Query query = session.createNativeQuery(sql).addEntity(AcademicGroupEntity.class);
        List<AcademicGroupEntity> groupsFromDb = query.list();

        Group[] groups = new Group[groupsFromDb.size()];

        for (int i = 0; i < groupsFromDb.size(); i++) {
            groups[i] = createGroupModelFromEntity(groupsFromDb.get(i));
        }

        //close session with a transaction
        sessionUtil.closeTransactionSession();

        return groups;
    }

    private Student createStudentModelFromEntity(StudentEntity studentEntity) {
        Student student = new Student();
        student.id = studentEntity.getId();
        student.firstName = studentEntity.getFirstName();
        student.middleName = studentEntity.getMiddleName();
        student.lastName = studentEntity.getLastName();
        student.group = createGroupModelFromEntity(studentEntity.getAcademicGroup());

        return student;
    }

    private Group createGroupModelFromEntity(AcademicGroupEntity groupEntity) {
        Group group = new Group();
        group.id = groupEntity.getGroupId();
        group.name = groupEntity.getGroupName();

        return group;
    }
}
