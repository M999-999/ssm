package com.pplflw.ssm.config;

import com.pplflw.ssm.domain.EmployeeEvent;
import com.pplflw.ssm.domain.EmployeeState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;
@SpringBootTest
class StateMachineConfigTest {
    @Autowired
    StateMachineFactory<EmployeeState, EmployeeEvent> factory;

    @Test
    void testNewStateMachine() {
        StateMachine<EmployeeState, EmployeeEvent> theStateMachine = factory.getStateMachine(UUID.randomUUID());

        theStateMachine.start();

        System.out.println(theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.ADD);

        System.out.println(theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.ADDED);

        System.out.println(theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.ACTIVATE);

        System.out.println(theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.DECLINE_ACTIVATE);

        System.out.println(theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.ACTIVATED);

        System.out.println(theStateMachine.getState().toString());

    }
}