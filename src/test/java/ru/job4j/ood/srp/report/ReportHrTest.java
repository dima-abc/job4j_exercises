package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formater.DateTimeParser;
import ru.job4j.ood.srp.formater.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Реализация отчетов для HR
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
class ReportHrTest {
    @Test
    void whenGeneratedHr() {
        Store<Employee> store = new MemStore();
        LocalDateTime now = LocalDateTime.now();
        Employee employee1 = new Employee("Ivan", now, now, 10, Currency.RUB);
        Employee employee2 = new Employee("Ira", now, now, 100, Currency.RUB);
        Employee employee3 = new Employee("Dasha", now, now, 1000, Currency.RUB);
        store.add(employee1);
        store.add(employee2);
        store.add(employee3);
        Report<Employee> reportHr = new ReportHr(store);
        String expect = new StringBuilder()
                .append("Name; Salary")
                .append(System.lineSeparator())
                .append(employee3.getName()).append("; ").append(employee3.getSalary())
                .append(System.lineSeparator())
                .append(employee2.getName()).append("; ").append(employee2.getSalary())
                .append(System.lineSeparator())
                .append(employee1.getName()).append("; ").append(employee1.getSalary())
                .append(System.lineSeparator())
                .toString();
        assertThat(reportHr.generator(t -> true))
                .isEqualTo(expect);
    }
}