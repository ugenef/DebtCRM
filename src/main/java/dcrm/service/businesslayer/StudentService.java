package dcrm.service.businesslayer;

import dcrm.service.businessmodels.Student;
import dcrm.service.dal.abstractions.CrudRepository;
import dcrm.service.dal.impl.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {
    private CrudRepository _db;

    @Autowired
    public StudentService(Database db) {
        _db = db;
    }

    public Student[] FindAll(){
        return _db.findAllStudents();
    }
}
