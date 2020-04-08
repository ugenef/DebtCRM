package dcrm.service.controllers;

import dcrm.service.businesslayer.StudentService;
import dcrm.service.businessmodels.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService _service;

    @Autowired
    public StudentController(StudentService service) {
        _service = service;
    }

    @GetMapping()
    public Student[] FindAll(){
        return _service.FindAll();
    }
}
