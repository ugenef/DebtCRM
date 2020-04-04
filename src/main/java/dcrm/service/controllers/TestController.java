package dcrm.service.controllers;

import dcrm.service.modes.testmodels.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private Student _student;

    @Autowired
    public TestController(Student student) {
        _student = student;
    }

    @GetMapping("/test")
    public Student printHello() {
        return _student;
    }
}
