package dcrm.service.dal.abstractions;

import dcrm.service.businessmodels.Debt;
import dcrm.service.businessmodels.Group;
import dcrm.service.businessmodels.Student;

public interface CrudRepository {

    //Возвращает всех студентов из базы
    Student[] findAllStudents();

    Student[] findStudentsFromGroup(int groupId);

    Student[] findDebtersFromGroup(int groupId);

    void deleteStudent(Student student);

    //Возвращает все группы из базы
    Group[] findAllGroups();

    void addStudents(Student[] students);

    void addDebt(Debt debt);
}
