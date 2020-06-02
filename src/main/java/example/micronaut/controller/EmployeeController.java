package example.micronaut.controller;

import example.micronaut.model.dto.EmployeeDto;
import example.micronaut.repostiroy.EmployeeRepository;
import example.micronaut.repostiroy.OrganizationRepository;
import example.micronaut.service.EmployeeService;
import io.micronaut.http.annotation.*;
import example.micronaut.model.ChangeJobRequest;
import example.micronaut.model.Employee;
import example.micronaut.model.Organization;


import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@Controller("employees")
public class EmployeeController {

    @Inject
    EmployeeRepository repository;
    @Inject
    OrganizationRepository organizationRepository;
    @Inject
    EmployeeService service;

    @Get("/salary/{salary}")
    public Set<EmployeeDto> findEmployeesBySalary(int salary) {
        return repository.findBySalaryGreaterThan(salary);
    }

    @Get("/organization/{organizationId}")
    public Set<EmployeeDto> findEmployeesByOrganization(Long organizationId) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);
        return repository.findByOrganization(organization.get());
    }

    @Get("/salary-avg/age/{age}")
    public int findAvgSalaryByAge(int age) {
        return repository.findAvgSalaryByAge(age);
    }

    @Get("/salary-avg/organization/{organizationId}")
    public int findAvgSalaryByAge(Long organizationId) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);
        return repository.findAvgSalaryByOrganization(organization.get());
    }

    @Post("/{departmentId}")
    public void addNewEmployee(@Body Employee employee, Long departmentId) {
        service.hireEmployee(employee, departmentId);
    }

    @Put("/change-job")
    public void changeJob(@Body ChangeJobRequest request) {
        service.changeJob(request.getEmployeeId(), request.getTargetOrganizationId());
    }

}
