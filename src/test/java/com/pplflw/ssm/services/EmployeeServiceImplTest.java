package com.pplflw.ssm.services;

import com.pplflw.ssm.domain.Employee;
import com.pplflw.ssm.domain.EmployeeEvent;
import com.pplflw.ssm.domain.EmployeeState;
import com.pplflw.ssm.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
class EmployeeServiceImplTest {
@Autowired
EmployeeService theEmployeeService;
@Autowired
EmployeeRepository theEmployeeRepository;
Employee theEmployee;

    @BeforeEach
    void setUp() {
        theEmployee = Employee.builder().email("nospam@nospam.com").build();
        //theEmployee = Employee.builder().id(123L).build();
        //theEmployee.setEmail("nospam@nospam.com");
    }
    @Transactional
    @Test
    void checkEmployee() {
        if(theEmployee!=null && theEmployeeService!=null) {
            Employee theAddedEmployee = theEmployeeService.addNewEmployee(theEmployee);
            System.out.println("===>>>>theAddedEmployee getState()");
            System.out.println(theAddedEmployee.getState());

            StateMachine<EmployeeState, EmployeeEvent> theStateMachine
                    = theEmployeeService.checkEmployee(theAddedEmployee.getId());
            Employee theCheckedEmployee = theEmployeeRepository.getOne(theAddedEmployee.getId());
            System.out.println("===>>>>theCheckedEmployee=");
            //System.out.println(theCheckedEmployee.getId());
            System.out.println(theCheckedEmployee.getState());

            System.out.println("===>>>>theStateMachine.getState().getId() ");
            System.out.println(theStateMachine.getState().getId());
        }else{
            System.out.println("===>>>>theAddedEmployee || theEmployeeService is\\are NULL");
        }

    }
}