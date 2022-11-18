package ru.job4j.ood.srp.model;

import ru.job4j.ood.srp.currency.Currency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Модель данных сотрудников. Employee.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public class Employee {
    private String name;
    private LocalDateTime hired;
    private LocalDateTime fired;
    private double salary;
    private Currency currency;

    public Employee(String name, LocalDateTime hired, LocalDateTime fired,
                    double salary, Currency currency) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getHired() {
        return hired;
    }

    public void setHired(LocalDateTime hired) {
        this.hired = hired;
    }

    public LocalDateTime getFired() {
        return fired;
    }

    public void setFired(LocalDateTime fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + '\'' + ", hired=" + hired
                + ", fired=" + fired + ", salary=" + salary
                +  ", currency=" + currency + '}';
    }
}
