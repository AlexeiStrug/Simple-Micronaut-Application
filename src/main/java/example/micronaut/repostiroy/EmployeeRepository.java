package example.micronaut.repostiroy;

import example.micronaut.model.Employee;
import example.micronaut.model.Organization;
import example.micronaut.model.dto.EmployeeDto;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Set<EmployeeDto> findBySalaryGreaterThan(int salary);

    Set<EmployeeDto> findByOrganization(Organization organization);

    int findAvgSalaryByAge(int age);

    int findAvgSalaryByOrganization(Organization organization);
}
