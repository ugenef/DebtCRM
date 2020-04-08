package dcrm.service.dal.impl;

import dcrm.service.businessmodels.Group;
import dcrm.service.businessmodels.Student;
import dcrm.service.dal.abstractions.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class Database implements CrudRepository {
    @Override
    public Student[] findAllStudents() {
        return new Student[0];
    }

    @Override
    public Group[] findAllGroups() {
        return new Group[0];
    }
}
