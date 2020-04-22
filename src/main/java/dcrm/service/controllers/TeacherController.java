package dcrm.service.controllers;

import dcrm.service.businesslayer.TeacherService;
import dcrm.service.controllers.dto.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {
    private TeacherService _service;

    @Autowired
    public TeacherController(TeacherService service) {
        _service = service;
    }

    @GetMapping()
    public TeacherDto[] findAll() {
        return TeacherDto.Map(_service.FindAll());
    }
}