package example.micronaut.repostiroy;

import example.micronaut.model.Employee;
import example.micronaut.model.Employment;
import example.micronaut.model.dto.EmploymentDto;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Set;

@Repository
public interface EmploymentRepository extends CrudRepository<Employment, Long> {

    Set<EmploymentDto> findByEmployeeOrderByStartDesc(Employee employee);

    Employment findByEmployeeAndEndIsNull(Employee employee);
}
