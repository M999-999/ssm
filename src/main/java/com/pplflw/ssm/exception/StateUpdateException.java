package com.pplflw.ssm.exception;

import com.pplflw.ssm.domain.EmployeeState;

public class StateUpdateException extends IllegalStateException{
    public StateUpdateException(final Long emplId, final EmployeeState state) {
        super("Unable to update state of employee " + emplId + ", required state is " + state);
    }
}
