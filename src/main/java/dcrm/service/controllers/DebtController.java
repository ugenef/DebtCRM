package dcrm.service.controllers;

import dcrm.service.businesslayer.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebtController {
    private DebtService _service;

    @Autowired
    public DebtController(DebtService service){
        _service = service;
    }
}
