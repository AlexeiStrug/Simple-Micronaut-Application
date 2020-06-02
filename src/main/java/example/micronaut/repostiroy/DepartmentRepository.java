package example.micronaut.repostiroy;

import example.micronaut.model.Department;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
