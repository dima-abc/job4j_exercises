package ru.job4j.ood.srp.currency;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Конвертор валют.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public interface CurrencyConverter<T extends Enum<T>> {
    double convert(T source, double sourceValue, T target);
}
