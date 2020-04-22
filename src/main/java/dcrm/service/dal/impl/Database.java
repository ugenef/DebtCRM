package dcrm.service.dal.impl;

import dcrm.service.bl.SessionUtil;
import dcrm.service.businessmodels.*;
import dcrm.service.dal.abstractions.CrudRepository;
import models.SubjectEntity;
import models.TeacherEntity;
import org.springframework.stereotype.Component;
import models.AcademicGroupEntity;
import models.StudentEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Vector;

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
    public Student[] findDebtersFromGroup(int groupId) {
        return findStudents(String.format("select s.*\n" +
                "from student s\n" +
                "         inner join student_subject on (student_subject.student_id = s.student_id)\n" +
                "where group_id = %s", groupId));
    }

    @Override
    public Teacher[] findAllTeachers() {
        return findTeachers("SELECT * FROM teacher");
    }

    @Override
    public Subject[] findAllSubjects() {
        return findSubjects("SELECT * FROM subject");
    }

    @Override
    public Debt[] findAllDebts() {
        DbContext context = new DbContext();
        context.OpenConnection();
        List<StudentEntity> studentsFromDb = context.getQuery("SELECT * FROM student", StudentEntity.class);

        Vector<Debt> debts = new Vector<>();
        for (StudentEntity student : studentsFromDb) {
            for (SubjectEntity subject : student.getSubjects()) {
                Debt debt = new Debt();
                debt.student = createStudentModelFromEntity(student);
                debt.subject = createSubjectModelFromEntity(subject);
                debts.add(debt);
            }
        }

        Debt[] debtArray = new Debt[debts.size()];
        for (int i = 0; i < debts.size(); i++) {
            debtArray[i] = debts.get(i);
        }

        context.CloseConnection();
        return debtArray;
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
    public void addDebt(Debt debt) {
        executeNoQuery(String.format("insert into student_subject(student_id, subject_id) values (%s,%s)",
                debt.student.id,
                debt.subject.id));
    }

    private void executeNoQuery(String sql) {
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

    private Teacher[] findTeachers(String sql) {
        DbContext context = new DbContext();
        context.OpenConnection();
        List<TeacherEntity> teachersFromDb = context.getQuery(sql, TeacherEntity.class);

        Teacher[] teachers = new Teacher[teachersFromDb.size()];
        for (int i = 0; i < teachersFromDb.size(); i++) {
            teachers[i] = createTeacherModelFromEntity(teachersFromDb.get(i));
        }
        context.CloseConnection();
        return teachers;
    }

    private Subject[] findSubjects(String sql) {
        DbContext context = new DbContext();
        context.OpenConnection();
        List<SubjectEntity> subjectsFromDb = context.getQuery(sql, SubjectEntity.class);

        Subject[] subjects = new Subject[subjectsFromDb.size()];
        for (int i = 0; i < subjectsFromDb.size(); i++) {
            subjects[i] = createSubjectModelFromEntity(subjectsFromDb.get(i));
        }
        context.CloseConnection();
        return subjects;
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

    private Teacher createTeacherModelFromEntity(TeacherEntity teacherEntity) {
        Teacher teacher = new Teacher();
        teacher.id = teacherEntity.getId();
        teacher.firstName = teacherEntity.getFirstName();
        teacher.middleName = teacherEntity.getMiddleName();
        teacher.lastName = teacherEntity.getLastName();

        return teacher;
    }

    private Subject createSubjectModelFromEntity(SubjectEntity subjectEntity) {
        Subject subject = new Subject();
        subject.id = subjectEntity.getId();
        subject.name = subjectEntity.getName();

        return subject;
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
