package com.pplflw.ssm.bootstrap;

import com.pplflw.ssm.domain.Employee;
import com.pplflw.ssm.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeLoader implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    public EmployeeLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEmployeeObjects();
    }

    private void loadEmployeeObjects() {
        if(employeeRepository.count() == 0){
            log.info("Create Employee");
            employeeRepository.save(Employee.builder()
                    .name("EmployeeName-1")
                    .build());

            employeeRepository.save(Employee.builder()
                    .name("EmployeeName-2")
                    .build());

            employeeRepository.save(Employee.builder()
                    .name("EmployeeName-3")
                    .build());


            Employee nuts = new Employee();
            nuts.setName("Nuts");
            nuts.setId(123L);

            employeeRepository.save(nuts);
            log.debug("Employee getId={}", nuts.getId());

            log.debug("Created Employee={}", employeeRepository.count());
        }
        log.info("employeeRepository.count()={}", employeeRepository.count());
    }
}
