package example.micronaut.service;

import example.micronaut.model.Department;
import example.micronaut.model.Employee;
import example.micronaut.model.Employment;
import example.micronaut.repostiroy.DepartmentRepository;
import example.micronaut.repostiroy.EmployeeRepository;
import example.micronaut.repostiroy.EmploymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Singleton
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Inject
    DepartmentRepository departmentRepository;
    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    EmploymentRepository employmentRepository;

    @Transactional
    public void changeJob(Long employeeId, Long targetDepartmentId) {
        Optional<Employee> findEmployee = employeeRepository.findById(employeeId);
        findEmployee.ifPresent(employee -> {
            Optional<Department> findDepartment = departmentRepository.findById(targetDepartmentId);
            findDepartment.ifPresent(department -> {
                employee.setDepartment(department);
                employee.setOrganization(department.getOrganization());
                Employment employment = new Employment(employee, department.getOrganization(), new Date());
                employmentRepository.save(employment);
                Employment previousEmployment = employmentRepository.findByEmployeeAndEndIsNull(employee);
                previousEmployment.setEnd(new Date());
                employmentRepository.save(previousEmployment);
            });
        });
    }

    @Transactional
    public void hireEmployee(Employee employee, Long targetDepartmentId) {
        Optional<Department> findDepartment = departmentRepository.findById(targetDepartmentId);
        findDepartment.ifPresent(department -> {
            employee.setDepartment(department);
            employee.setOrganization(department.getOrganization());
            Employee employeeSaved = employeeRepository.save(employee);
            LOGGER.info("{}", employeeSaved);
            employmentRepository.save(new Employment(employeeSaved, department.getOrganization(), new Date()));
        });
    }
}
