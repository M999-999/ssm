package com.pplflw.ssm.config;

import com.pplflw.ssm.domain.EmployeeEvent;
import com.pplflw.ssm.domain.EmployeeState;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

@SpringBootTest
@Configuration
@EnableAutoConfiguration
@ComponentScan
class StateMachineConfigTest {
    /* @Autowired */
    StateMachineFactory<EmployeeState, EmployeeEvent> factory;

    @Test
    void testNewStateMachine() {
        StateMachine<EmployeeState, EmployeeEvent> theStateMachine = factory.getStateMachine(UUID.randomUUID());

        theStateMachine.start();

        System.out.println(theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.ADD);

        System.out.println(theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.CHECK);

        System.out.println(theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.ACTIVATE);

        System.out.println(theStateMachine.getState().toString());

        theStateMachine.sendEvent(EmployeeEvent.DECLINE_ACTIVATE);

        System.out.println(theStateMachine.getState().toString());


    }
}