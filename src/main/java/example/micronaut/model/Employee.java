package example.micronaut.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private int age;
    private String position;
    private int salary;
    @ManyToOne
    private Organization organization;
    @ManyToOne
    private Department department;
    @OneToMany
    private Set<Employment> employments;

    public Employee() {

    }

    public Employee(String name, int age, String position, int salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Employment> getEmployments() {
        return employments;
    }

    public void setEmployments(Set<Employment> employments) {
        this.employments = employments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                salary == employee.salary &&
                Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(organization, employee.organization) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(employments, employee.employments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, position, salary, organization, department, employments);
    }
}
