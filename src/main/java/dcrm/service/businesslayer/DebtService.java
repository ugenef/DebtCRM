package dcrm.service.businesslayer;

import dcrm.service.businessmodels.Debt;
import dcrm.service.dal.abstractions.CrudRepository;
import dcrm.service.dal.impl.Database;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebtService {
    private CrudRepository _db;

    public DebtService(Database db) {
        _db = db;
    }

    public void AddDebt(int studentId, int subjectId){

    }

    public void CloseDebt(int studentId, int subjectId){

    }

    public Debt[] GetStudentDebts(int studentId){
        return null;
    }
}
