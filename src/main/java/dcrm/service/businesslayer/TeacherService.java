package dcrm.service.businesslayer;

import dcrm.service.businessmodels.Teacher;
import dcrm.service.dal.abstractions.CrudRepository;
import dcrm.service.dal.impl.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherService {
    private CrudRepository _db;

    @Autowired
    public TeacherService(Database db) {
        _db = db;
    }

    public Teacher[] FindAll() {
        return _db.findAllTeachers();
    }
}


