package example.micronaut.model.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class OrganizationDto {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
