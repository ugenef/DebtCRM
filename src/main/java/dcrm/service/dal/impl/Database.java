package dcrm.service.dal.impl;

import dcrm.service.bl.SessionUtil;
import dcrm.service.businessmodels.Debt;
import dcrm.service.businessmodels.Group;
import dcrm.service.businessmodels.Student;
import dcrm.service.dal.abstractions.CrudRepository;
import org.springframework.stereotype.Component;
import models.AcademicGroupEntity;
import models.StudentEntity;
import org.hibernate.Session;

import java.util.List;

@Component
public class Database implements CrudRepository {
    @Override
    public Student[] findAllStudents() {
        return findStudents("SELECT * FROM student");
    }

    @Override
    public Student[] findStudentsFromGroup(int groupId) {
        return findStudents(String.format("SELECT * FROM student WHERE group_id = %s", groupId));
    }

    @Override
    public Student[] findDebtersFromGroup(int groupId){
        return findStudents(String.format("select s.*\n" +
                "from student s\n" +
                "         inner join student_subject on (student_subject.student_id = s.student_id)\n" +
                "where group_id = %s", groupId));
    }

    @Override
    public void deleteStudent(Student student) {
        DbContext context = new DbContext();
        context.OpenConnection();

        StudentEntity studentEntity = createStudentEntityFromModel(student);
        context.removeEntity(studentEntity);

        context.CloseConnection();
    }

    @Override
    public void addDebt(Debt debt){
        executeNoQuery(String.format("insert into student_subject(student_id, subject_id) values (%s,%s)",
                debt.student.id,
                debt.subject.id));
    }

    private void executeNoQuery(String sql){
        SessionUtil sessionUtil = new SessionUtil();
        //open session with a transaction
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        //TODO sql
        //close session with a transaction
        sessionUtil.closeTransactionSession();
    }


    private Student[] findStudents(String sql) {
        DbContext context = new DbContext();
        context.OpenConnection();
        List<StudentEntity> studentsFromDb = context.getQuery(sql, StudentEntity.class);

        Student[] students = new Student[studentsFromDb.size()];
        for (int i = 0; i < studentsFromDb.size(); i++) {
            students[i] = createStudentModelFromEntity(studentsFromDb.get(i));
        }
        context.CloseConnection();
        return students;
    }

    @Override
    public Group[] findAllGroups() {
        DbContext context = new DbContext();
        context.OpenConnection();
        String sql = "SELECT * FROM academic_group";
        List<AcademicGroupEntity> groupsFromDb = context.getQuery(sql, AcademicGroupEntity.class);

        Group[] groups = new Group[groupsFromDb.size()];

        for (int i = 0; i < groupsFromDb.size(); i++) {
            groups[i] = createGroupModelFromEntity(groupsFromDb.get(i));
        }

        context.CloseConnection();
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
