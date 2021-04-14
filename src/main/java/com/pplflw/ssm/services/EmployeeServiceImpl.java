package com.pplflw.ssm.services;

import com.pplflw.ssm.domain.Employee;
import com.pplflw.ssm.domain.EmployeeEvent;
import com.pplflw.ssm.domain.EmployeeState;
import com.pplflw.ssm.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static final String EMPLOYEE_ID_HEADER = "employee_id";

    private final EmployeeRepository theEmployeeRepository;
    private final StateMachineFactory<EmployeeState, EmployeeEvent> theStateMachineFactory;
    private final EmployeeStateChangeInterceptor theEmployeeStateChangeInterceptor;

    @Transactional
    @Override
    public Employee addNewEmployee(Employee theEmployee) {
        theEmployee.setState(EmployeeState.ADDED);
        return theEmployeeRepository.save(theEmployee);
    }
    @Transactional
    @Override
    public StateMachine<EmployeeState, EmployeeEvent> checkEmployee(Long employeeId) {
        StateMachine<EmployeeState, EmployeeEvent> theStateMachine = build(employeeId);
        sendEvent(employeeId,theStateMachine,EmployeeEvent.CHECK);
        return null;
    }
    @Transactional
    @Override
    public StateMachine<EmployeeState, EmployeeEvent> approveEmployee(Long employeeId) {
        StateMachine<EmployeeState, EmployeeEvent> theStateMachine = build(employeeId);
        sendEvent(employeeId,theStateMachine,EmployeeEvent.ACTIVATE);
        return null;
    }

    private void sendEvent(Long theEmployeeId, StateMachine<EmployeeState, EmployeeEvent> theStataMachine, EmployeeEvent theEmployeeEvent){
        Message theMessage = MessageBuilder.withPayload(theEmployeeEvent)
                .setHeader(EMPLOYEE_ID_HEADER, theEmployeeId)
                .build();

        theStataMachine.sendEvent(theMessage);
    }

    private StateMachine<EmployeeState, EmployeeEvent> build(Long employeeId) {
        Employee theEmployee = theEmployeeRepository.getOne(employeeId);
        StateMachine<EmployeeState, EmployeeEvent> theStateMachine = theStateMachineFactory.getStateMachine(Long.toString(theEmployee.getId()));
        theStateMachine.stop();
        theStateMachine.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(theEmployeeStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(theEmployee.getState(), null, null, null));
                });
        theStateMachine.start();

        return theStateMachine;
    }

}
