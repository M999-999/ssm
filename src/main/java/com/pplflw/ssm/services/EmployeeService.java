package com.pplflw.ssm.services;

import com.pplflw.ssm.domain.Employee;
import com.pplflw.ssm.domain.EmployeeEvent;
import com.pplflw.ssm.domain.EmployeeState;
import org.springframework.statemachine.StateMachine;

public interface EmployeeService {
    Employee addNewEmployee(Employee theEmployee);

    StateMachine<EmployeeState, EmployeeEvent> checkEmployee(Long employeeId);

    StateMachine<EmployeeState, EmployeeEvent> approveEmployee(Long employeeId);

}
