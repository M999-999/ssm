package com.pplflw.ssm.services;

import com.pplflw.ssm.domain.Employee;
import com.pplflw.ssm.domain.EmployeeEvent;
import com.pplflw.ssm.domain.EmployeeState;
import com.pplflw.ssm.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
@Slf4j
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
            Employee theAddedEmployee = theEmployeeService.addEmployee(theEmployee);
            log.info("===>>>>theAddedEmployee getState()={}",theAddedEmployee.getState());

            StateMachine<EmployeeState, EmployeeEvent> theStateMachine
                    = theEmployeeService.checkEmployee(theAddedEmployee.getId());
            Employee theCheckedEmployee = theEmployeeRepository.getOne(theAddedEmployee.getId());
            log.info("===>>>>theCheckedEmployee.getState()={}",theCheckedEmployee.getState());
            log.info("===>>>>theStateMachine.getState().getId()=={} ",theStateMachine.getState().getId());

        }else{
            log.info("===>>>>theAddedEmployee || theEmployeeService is\\are NULL");
        }

    }
}