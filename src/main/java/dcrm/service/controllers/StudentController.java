package dcrm.service.controllers;

import dcrm.service.businesslayer.StudentService;
import dcrm.service.businessmodels.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService _service;

    @Autowired
    public StudentController(StudentService service) {
        _service = service;
    }

    @GetMapping()
    public Student[] findAll() {
        return _service.FindAll();
    }

    @GetMapping(value = "/{groupId}")
    public Student[] findFromGroup(@PathVariable int groupId) {
        return _service.FindFromGroup(groupId);
    }

    @GetMapping(value = "hasDebts/{groupId}")
    public Student[] findDebtersFromGroup(@PathVariable int groupId) {
        return _service.FindDebtersFromGroup(groupId);
    }

    @PostMapping()
    public void add(@RequestBody Student student){
        _service.add(student);
    }
}
