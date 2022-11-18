package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formater.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Реализация системы отчетов Для Программистов.
 * Для отдела программистов потребовался отчет в формате CSV
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public class ReportEngine implements Report<Employee> {
    private final Store<Employee> store;
    private final DateTimeParser<LocalDateTime> dateTimeParser;
    private static final String DIVIDER = "; ";
    private static final String SEPARATOR = System.lineSeparator();

    public ReportEngine(Store<Employee> store, DateTimeParser<LocalDateTime> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generator(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder("Name").append(DIVIDER)
                .append("Hired").append(DIVIDER)
                .append("Fired").append(DIVIDER)
                .append("Salary")
                .append(SEPARATOR);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(DIVIDER)
                    .append(dateTimeParser.parse(employee.getHired())).append(DIVIDER)
                    .append(dateTimeParser.parse(employee.getFired())).append(DIVIDER)
                    .append(employee.getSalary())
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
