package com.pplflw.ssm.controller;


import com.pplflw.ssm.services.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {



    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/")
    public String index() {
        return "EmployeeController . !!!";
    }

//    @GetMapping("/{employeeId}")
//    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") UUID employeeId){
//
//        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
//    }

//    @PostMapping
//    public ResponseEntity handlePost(@RequestBody @Valid EmployeeDto employeeDto){
//        EmployeeDto savedDto = employeeService.saveNewEmployee(employeeDto);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Location", "/api/v1/employee/" + savedDto.getId().toString());
//
//        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{employeeId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void handleUpdate(@PathVariable("employeeId") UUID employeeId, @Valid @RequestBody EmployeeDto employeeDto){
//        employeeService.updateEmployee(employeeId, employeeDto);
//    }
//
//    @DeleteMapping("/{employeeId}")
//    public void deleteById(@PathVariable("employeeId")  UUID employeeId){
//        employeeService.deleteById(employeeId);
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
//        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
//
//        e.getConstraintViolations().forEach(constraintViolation -> {
//            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
//        });
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
}