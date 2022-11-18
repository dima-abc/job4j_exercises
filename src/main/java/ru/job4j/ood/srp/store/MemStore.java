package ru.job4j.ood.srp.store;

import ru.job4j.ood.srp.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Реализация хранилища в памяти. MemStore.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public class MemStore implements Store<Employee> {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void add(Employee employee) {
        this.employees.add(employee);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return this.employees.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }
}
