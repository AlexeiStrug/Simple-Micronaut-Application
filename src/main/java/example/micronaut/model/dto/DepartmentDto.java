package example.micronaut.model.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class DepartmentDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
