package com.pplflw.ssm.services;

import com.pplflw.ssm.domain.Employee;
import com.pplflw.ssm.domain.EmployeeEvent;
import com.pplflw.ssm.domain.EmployeeState;
import com.pplflw.ssm.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EmployeeStateChangeInterceptor extends StateMachineInterceptorAdapter<EmployeeState, EmployeeEvent> {
    private final EmployeeRepository theEmployeeRepository;

    @Override
    public void preStateChange(State<EmployeeState, EmployeeEvent> state, Message<EmployeeEvent> message, Transition<EmployeeState, EmployeeEvent> transition,
                               StateMachine<EmployeeState, EmployeeEvent> stateMachine, StateMachine<EmployeeState, EmployeeEvent> rootStateMachine) {
        Optional.ofNullable(message).ifPresent(msg -> {
            Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault(EmployeeServiceImpl.EMPLOYEE_ID_HEADER, -1L)))
                    .ifPresent(theEmployeeId -> {
                        Employee theEmployee = theEmployeeRepository.getOne(theEmployeeId);
                        theEmployee.setState(state.getId());
                        theEmployeeRepository.save(theEmployee);
                    });
        });
    }
}
