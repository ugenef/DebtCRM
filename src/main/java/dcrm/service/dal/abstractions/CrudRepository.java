package dcrm.service.dal.abstractions;

import dcrm.service.businessmodels.*;

public interface CrudRepository {

    //Возвращает всех студентов из базы
    Student[] findAllStudents();

    Student[] findStudentsFromGroup(int groupId);

    Student[] findDebtersFromGroup(int groupId);

    Teacher[] findAllTeachers();

    Subject[] findAllSubjects();

    Debt[] findAllDebts();

    void deleteStudent(Student student);

    //Возвращает все группы из базы
    Group[] findAllGroups();

    void addStudents(Student[] students);

    void addDebt(Debt debt);
}
