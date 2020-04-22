package dcrm.service.businesslayer;

import dcrm.service.businessmodels.Subject;
import dcrm.service.dal.abstractions.CrudRepository;
import dcrm.service.dal.impl.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectService {

    private CrudRepository _db;

    @Autowired
    public SubjectService(Database db) {
        _db = db;
    }

    public Subject[] FindAll() {
        return _db.findAllSubjects();
    }
}

