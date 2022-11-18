package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
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
 * Реализация отчетов для Бухгалтерии
 * В бухгалтерском отчете необходимо сделать конвертацию зарплаты в одну из валют.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public class ReportAccount implements Report<Employee> {
    private final Store<Employee> store;
    private final DateTimeParser<LocalDateTime> dateTimeParser;
    private final CurrencyConverter<Currency> converter;
    private final Currency targetCurrency;
    private static final String DIVIDER = "; ";
    private static final String SEPARATOR = System.lineSeparator();


    public ReportAccount(Store<Employee> store, DateTimeParser<LocalDateTime> dateTimeParser,
                         CurrencyConverter<Currency> converter, Currency targetCurrency) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String generator(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder("Name").append(DIVIDER)
                .append("Hired").append(DIVIDER)
                .append("Fired").append(DIVIDER)
                .append("Salary").append(DIVIDER)
                .append(targetCurrency)
                .append(SEPARATOR);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(DIVIDER)
                    .append(dateTimeParser.parse(employee.getHired())).append(DIVIDER)
                    .append(dateTimeParser.parse(employee.getFired())).append(DIVIDER)
                    .append(converter
                            .convert(employee.getCurrency(), employee.getSalary(), targetCurrency))
                    .append(DIVIDER)
                    .append(targetCurrency)
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
