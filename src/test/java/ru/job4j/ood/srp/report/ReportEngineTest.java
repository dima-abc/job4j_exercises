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
 * Реализация системы отчетов Для Программистов.
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
class ReportEngineTest {
    @Test
    void whenOldGenerated() {
        Store<Employee> store = new MemStore();
        LocalDateTime now = LocalDateTime.now().withNano(0);
        Employee worker = new Employee("Ivan", now, now, 1000D, Currency.RUB);
        DateTimeParser<LocalDateTime> parser = new ReportDateTimeParser("dd:MM:yyyy HH:mm");
        store.add(worker);
        Report<Employee> engine = new ReportEngine(store, parser);
        String expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append("; ")
                .append(parser.parse(worker.getHired())).append("; ")
                .append(parser.parse(worker.getFired())).append("; ")
                .append(worker.getSalary())
                .append(System.lineSeparator())
                .toString();
        assertThat(engine.generator(em -> true))
                .isEqualTo(expect);
    }
}