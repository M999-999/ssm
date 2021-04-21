package com.pplflw.ssm.dto;

import com.pplflw.ssm.domain.EmployeeState;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    private EmployeeState state;
}