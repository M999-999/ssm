package com.pplflw.ssm.repository;

import com.pplflw.ssm.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
