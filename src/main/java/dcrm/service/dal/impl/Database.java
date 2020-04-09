package dcrm.service.dal.impl;

import dcrm.service.bl.SessionUtil;
import dcrm.service.businessmodels.Group;
import dcrm.service.businessmodels.Student;
import dcrm.service.dal.abstractions.CrudRepository;
import org.springframework.stereotype.Component;
import models.AcademicGroupEntity;
import models.StudentEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

@Component
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

    @Override
    public void addStudents(Student[] students) {
        SessionUtil sessionUtil = new SessionUtil();
        //open session with a transaction
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();

        StudentEntity[] studentEntities = new StudentEntity[students.length];

        for (int i = 0; i < studentEntities.length; i++) {
            studentEntities[i] = createStudentEntityFromModel(students[i]);
        }

        for (StudentEntity studentEntity : studentEntities) {
            session.save(studentEntity);
        }

        //close session with a transaction
        sessionUtil.closeTransactionSession();

    }

    private Student createStudentModelFromEntity(StudentEntity studentEntity) {
        Student student = new Student();
        student.id = studentEntity.getId();
        student.firstName = studentEntity.getFirstName();
        student.middleName = studentEntity.getMiddleName();
        student.lastName = studentEntity.getLastName();

        AcademicGroupEntity groupEntity = studentEntity.getAcademicGroup();
        student.group = createGroupModelFromEntity(groupEntity);

        return student;
    }

    private Group createGroupModelFromEntity(AcademicGroupEntity groupEntity) {
        Group group = new Group();
        group.id = groupEntity.getGroupId();
        group.name = groupEntity.getGroupName();

        return group;
    }

    private StudentEntity createStudentEntityFromModel(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.id);
        studentEntity.setFirstName(student.firstName);
        studentEntity.setMiddleName(student.middleName);
        studentEntity.setLastName(student.lastName);

        AcademicGroupEntity groupEntity = createGroupEntityFromModel(student.group);
        studentEntity.setAcademicGroup(groupEntity);

        return studentEntity;
    }

    private AcademicGroupEntity createGroupEntityFromModel(Group group) {
        AcademicGroupEntity groupEntity = new AcademicGroupEntity();
        groupEntity.setGroupId(group.id);
        groupEntity.setGroupName(group.name);

        return groupEntity;
    }
}
