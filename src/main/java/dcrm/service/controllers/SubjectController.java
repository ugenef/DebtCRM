package dcrm.service.controllers;

import dcrm.service.businesslayer.SubjectService;
import dcrm.service.controllers.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins = "*")
public class SubjectController {
    private SubjectService _service;

    @Autowired
    public SubjectController(SubjectService service) {
        _service = service;
    }

    @GetMapping()
    public SubjectDto[] findAll() {
        return SubjectDto.Map(_service.FindAll());
    }
}