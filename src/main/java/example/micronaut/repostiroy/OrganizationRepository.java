package example.micronaut.repostiroy;

import example.micronaut.model.Organization;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    @Join(value = "departments", type = Join.Type.LEFT_FETCH)
    @Join(value = "employees", type = Join.Type.LEFT_FETCH)
    Optional<Organization> findByName(String name);
}
