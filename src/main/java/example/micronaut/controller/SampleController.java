package example.micronaut.controller;

import example.micronaut.model.ChangeJobRequest;
import example.micronaut.model.Department;
import example.micronaut.model.Employee;
import example.micronaut.model.Organization;
import example.micronaut.repostiroy.DepartmentRepository;
import example.micronaut.repostiroy.EmployeeRepository;
import example.micronaut.repostiroy.OrganizationRepository;
import example.micronaut.service.EmployeeService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller("/api")
public class SampleController {

    @Inject
    DepartmentRepository departmentRepository;
    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    OrganizationRepository organizationRepository;
    @Inject
    EmployeeService service;

    @Get("/organization")
    public Iterable<Organization> findAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Get("/employee")
    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Get("/organization/name/{name}")
    public Optional<Organization> findOrganization(@NotNull String name) {
        return organizationRepository.findByName(name);
    }

    @Get("/organization/{id}")
    public Optional<Organization> findOrganizationById(@NotNull Long id) {
        return organizationRepository.findById(id);
    }

    @Post("/organization")
    public Organization addOrganization(@Body Organization organization) {
        return organizationRepository.save(organization);
    }

    @Post("/change-job")
    public void changeJob(@Body ChangeJobRequest request) {
        service.changeJob(request.getEmployeeId(), request.getTargetOrganizationId());
    }

    @Post("/employee/{departmentId}")
    public void addNewEmployee(@Body Employee employee, Long departmentId) {
        service.hireEmployee(employee, departmentId);
    }

    @Post("/departments/{organizationId}")
    public Department addNewDepartment(@Body Department department, Long organizationId) {
        organizationRepository.findById(organizationId).ifPresent(organization -> {
            department.setOrganization(organization);
            departmentRepository.save(department);
        });
        return department;
    }
}
