package ru.job4j.ood.srp.formater;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Реализация парсера даты.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public class ReportDateTimeParser implements DateTimeParser<LocalDateTime> {
    private final DateTimeFormatter formatter;

    public ReportDateTimeParser(String dataFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dataFormat);
    }

    @Override
    public String parse(LocalDateTime localDateTime) {
        return formatter.format(localDateTime);
    }
}
