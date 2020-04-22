package dcrm.service.controllers;

import dcrm.service.businesslayer.DebtService;
import dcrm.service.controllers.dto.DebtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debts")
@CrossOrigin(origins = "*")
public class DebtController {
    private DebtService _service;

    @Autowired
    public DebtController(DebtService service) {
        _service = service;
    }

    @GetMapping()
    public DebtDto[] findAll() {
        return DebtDto.Map(_service.FindAll());
    }
}
