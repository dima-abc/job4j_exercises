package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formater.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Реализация отчетов для HR
 * Отдел HR попросил выводить сотрудников в порядке убывания зарплаты
 * и убрать поля даты найма и увольнения.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public class ReportHr implements Report<Employee> {
    private final Store<Employee> store;
    private static final String DIVIDER = "; ";
    private static final String SEPARATOR = System.lineSeparator();

    public ReportHr(Store<Employee> store) {
        this.store = store;
    }

    @Override
    public String generator(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder("Name").append(DIVIDER)
                .append("Salary")
                .append(SEPARATOR);
        List<Employee> employees = store.findBy(filter);
        employees.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        for (Employee employee : employees) {
            text.append(employee.getName()).append(DIVIDER)
                    .append(employee.getSalary())
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
