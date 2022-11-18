package ru.job4j.ood.srp.formater;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Парсер даты.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public interface DateTimeParser<T> {
    String parse(T t);
}
