package example.micronaut.controller;

import example.micronaut.model.Organization;
import example.micronaut.repostiroy.OrganizationRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;


import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller("organizations")
public class OrganizationController {

    @Inject
    OrganizationRepository repository;

    @Post
    public Long addOrganization(@Body Organization organization) {
        Organization organization1 = repository.save(organization);
        return organization1.getId();
    }

    @Get("/name/{name}")
    public Optional<Organization> findOrganization(@NotNull String name) {
        return repository.findByName(name);
    }

}
