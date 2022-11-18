package ru.job4j.ood.srp.report;

import java.util.function.Predicate;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Система отчетов. Report
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public interface Report<T> {
    String generator(Predicate<T> filter);
}
