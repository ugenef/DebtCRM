package dcrm.service.controllers;

import dcrm.service.businesslayer.StudentService;
import dcrm.service.businessmodels.Student;
import dcrm.service.controllers.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {
    private StudentService _service;

    @Autowired
    public StudentController(StudentService service) {
        _service = service;
    }

    @GetMapping()
    public StudentDto[] findAll() {
        return StudentDto.Map(_service.FindAll());
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
