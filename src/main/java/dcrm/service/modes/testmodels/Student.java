package dcrm.service.modes.testmodels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
    public String DebtName;
    public String Name = "Dima";

    @Autowired
    public Student(Debt debt){
        DebtName = debt.Subject;
    }

}
