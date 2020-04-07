package dcrm.service.controllers;

import dcrm.service.bl.HibernateUtil;
import dcrm.service.businessmodels.Group;
import dcrm.service.businessmodels.Student;
import dcrm.service.dal.impl.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class MainController {
    public MainController() {

    }

    @GetMapping("/st")
    public Student[] getStudents() {

        Database db=new Database();

        // если закрыть сессию здесь, то нельзя будет сделать повторный запрос
        // где закрывать?
        //        HibernateUtil.shutdown();

        return db.findAllStudents();
    }

    @GetMapping("/gr")
    public Group[] getGroups() {

        Database db=new Database();

        // если закрыть сессию здесь, то нельзя будет сделать повторный запрос
        // где закрывать?
        //        HibernateUtil.shutdown();

        return db.findAllGroups();
    }
}
