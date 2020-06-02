package example.micronaut.model.dto;

import example.micronaut.model.Organization;
import io.micronaut.core.annotation.Introspected;

import java.util.Date;

@Introspected
public class EmploymentDto {

    private Organization organization;
    private Date start;
    private Date end;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
