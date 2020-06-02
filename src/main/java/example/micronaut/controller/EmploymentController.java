package example.micronaut.controller;

import example.micronaut.model.Employee;
import example.micronaut.model.dto.EmploymentDto;
import example.micronaut.repostiroy.EmployeeRepository;
import example.micronaut.repostiroy.EmploymentRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@Controller("employments")
public class EmploymentController {

    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    EmploymentRepository repository;

    @Get("/employee/{employeeId}")
    public Set<EmploymentDto> findByEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return repository.findByEmployeeOrderByStartDesc(employee.get());
    }

}
