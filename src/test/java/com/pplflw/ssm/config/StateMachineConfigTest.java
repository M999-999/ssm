package com.pplflw.ssm.config;

import com.pplflw.ssm.domain.EmployeeEvent;
import com.pplflw.ssm.domain.EmployeeState;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

@SpringBootTest
//@EnableAutoConfiguration
//@ComponentScan
@Slf4j
class StateMachineConfigTest {
    @Autowired
    StateMachineFactory<EmployeeState,EmployeeEvent> factory;

    @Test
    void testNewStateMachine() {
        StateMachine<EmployeeState, EmployeeEvent> theStateMachine = factory.getStateMachine(UUID.randomUUID());

        theStateMachine.start();

        log.info("theStateMachine.start() State={}", theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.ADD);

        log.info("theStateMachine.sendEvent(EmployeeEvent.ADD)() State={}",theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.CHECK);

        log.info("theStateMachine.sendEvent(EmployeeEvent.CHECK)() State={}",theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.ACTIVATE);

        log.info("theStateMachine.sendEvent(EmployeeEvent.ACTIVATE)() State={}",theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.DECLINE);

        log.info("theStateMachine.sendEvent(EmployeeEvent.DECLINE_ACTIVATE)() State={}",theStateMachine.getState().toString());


    }
}