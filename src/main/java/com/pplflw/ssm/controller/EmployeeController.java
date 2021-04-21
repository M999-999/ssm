package com.pplflw.ssm.controller;


import com.pplflw.ssm.domain.Employee;
import com.pplflw.ssm.domain.EmployeeEvent;
import com.pplflw.ssm.domain.EmployeeState;
import com.pplflw.ssm.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/ssm/api/v1")
public class EmployeeController {

    private final EmployeeService service;
    @GetMapping("/")
    public String index() {
        return "Default EmployeeController started.";
    }
    @PostMapping("/create")
    public Employee createEmployee(@Valid @RequestBody final Employee employee) {
        return service.addEmployee(employee);
    }

    @PostMapping("/activate/{empId}")
    public StateMachine<EmployeeState, EmployeeEvent> activateEmployee(@PathVariable final Long empId) {
        log.info("Requested activation for Employee with ID {}", empId);
        return service.activateEmployee(empId);
    }

    @PostMapping("/check/{empId}")
    public StateMachine<EmployeeState, EmployeeEvent> inCheckEmployee(@PathVariable final Long empId) {
        log.info("Requested in-check for Employee with ID {}", empId);
        return service.checkEmployee(empId);
    }

    @PostMapping("/approve/{empId}")
    public StateMachine<EmployeeState, EmployeeEvent> approveEmployee(@PathVariable final Long empId) {
        log.info("Requested approval for Employee with ID {}", empId);
        return service.approveEmployee(empId);
    }

    @PostMapping("/deactivate/{empId}")
    public StateMachine<EmployeeState, EmployeeEvent> deactivaEmployee(@PathVariable final Long empId) {
        log.info("Requested approval for Employee with ID {}", empId);
        return service.deactivateEmployee(empId);
    }
}