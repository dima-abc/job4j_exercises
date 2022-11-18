package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
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
 * Реализация отчетов для Бухгалтерии
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
class ReportAccountTest {
    @Test
    void whenAccountGenerate() {
        Employee employee = new Employee("Ivan", LocalDateTime.now(), LocalDateTime.now(),
                1000, Currency.USD);
        Employee employee2 = new Employee("Dima", LocalDateTime.now(), LocalDateTime.now(),
                3000, Currency.EUR);
        Store<Employee> store = new MemStore();
        store.add(employee);
        store.add(employee2);
        DateTimeParser<LocalDateTime> formatter = new ReportDateTimeParser("dd:MM:yyyy HH:mm");
        CurrencyConverter<Currency> converter = new InMemoryCurrencyConverter();
        Currency targetRub = Currency.RUB;
        Report<Employee> reportAccount = new ReportAccount(store, formatter, converter, targetRub);
        String actual = reportAccount.generator(t -> t.getSalary() < 2000D);
        String expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary; ").append(targetRub)
                .append(System.lineSeparator())
                .append(employee.getName()).append("; ")
                .append(formatter.parse(employee.getHired())).append("; ")
                .append(formatter.parse(employee.getHired())).append("; ")
                .append(converter.convert(employee.getCurrency(), employee.getSalary(), targetRub)).append("; ")
                .append(targetRub)
                .append(System.lineSeparator())
                .toString();
        assertThat(actual)
                .isEqualTo(expect);
    }

}