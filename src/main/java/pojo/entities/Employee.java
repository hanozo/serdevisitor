package pojo.entities;

import visitor.contract.AcceptVisitor;
import visitor.contract.Visitor;

public class Employee implements Comparable<Employee>, AcceptVisitor<Employee> {

    private java.lang.String name;
    private boolean active;
    private long salary;

    public Employee() {
    }

    private Employee(String name, boolean active, long salary) {
        this.name = name;
        this.active = active;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee employee) {
        return this.name.compareTo(employee.getName());
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void accept(Visitor<Employee> visitor) {
        visitor.visit(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private boolean active;
        private long salary;

        public Builder setName(String name) {
            this.name = name;

            return this;
        }

        public Builder setActive(boolean active) {
            this.active = active;
            return this;
        }

        public Builder setSalary(long salary) {
            this.salary = salary;
            return this;
        }

        public Employee build() {
            return new Employee(name, active, salary);
        }
    }
}
