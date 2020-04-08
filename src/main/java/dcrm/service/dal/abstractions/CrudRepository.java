package dcrm.service.dal.abstractions;

import dcrm.service.businessmodels.Group;
import dcrm.service.businessmodels.Student;

public interface CrudRepository {

    //Возвращает всех студентов из базы
    Student[] findAllStudents();

    //Возвращает все группы из базы
    Group[] findAllGroups();

    void addStudents(Student[] students);
}
