package example.micronaut;

import example.micronaut.model.Department;
import example.micronaut.model.Employee;
import example.micronaut.model.Organization;
import example.micronaut.repostiroy.DepartmentRepository;
import example.micronaut.repostiroy.EmployeeRepository;
import example.micronaut.repostiroy.OrganizationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class RepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryTest.class);

    @Inject
    DepartmentRepository departmentRepository;
    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    OrganizationRepository organizationRepository;

    @Test
    public void testAddOrganization() {
        Organization organization = new Organization("TestName", "TestAddress");
        organization = organizationRepository.save(organization);
        Assertions.assertNotNull(organization.getId());
        LOGGER.info("{}", organization);

        Department department = new Department("Test");
        department.setOrganization(organization);
        department = departmentRepository.save(department);
        Assertions.assertNotNull(department.getId());
        LOGGER.info("{}", department);
    }

    @Test
    public void addMultiple() {
        List<Employee> employees = Arrays.asList(
                new Employee("Test1", 20, "Developer", 5000),
                new Employee("Test2", 30, "Analyst", 15000),
                new Employee("Test3", 40, "Manager", 25000),
                new Employee("Test4", 25, "Developer", 9000),
                new Employee("Test5", 23, "Analyst", 8000),
                new Employee("Test6", 50, "Developer", 12000),
                new Employee("Test7", 55, "Architect", 25000),
                new Employee("Test8", 43, "Manager", 15000)
        );

        Organization organization = new Organization("TestWithEmployees", "TestAddress");
        Organization organizationSaved = organizationRepository.save(organization);
        Assertions.assertNotNull(organization.getId());
        Department department = new Department("TestWithEmployees");
        department.setOrganization(organization);
        Department departmentSaved = departmentRepository.save(department);
        Assertions.assertNotNull(department.getId());

        Employee employee = new Employee("Test1", 20, "Developer", 5000);
        employee.setOrganization(organizationSaved);
        employee.setDepartment(departmentSaved);
        employee = employeeRepository.save(employee);
        Assertions.assertNotNull(employee.getId());
//        employeeRepository.saveAll(employees.stream().map(employee -> {
//            employee.setOrganization(organizationSaved);
//            employee.setDepartment(departmentSaved);
//            return employee;
//        }).collect(Collectors.toList()));
    }
}
