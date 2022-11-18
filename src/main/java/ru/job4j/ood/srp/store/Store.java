package ru.job4j.ood.srp.store;

import java.util.List;
import java.util.function.Predicate;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Интерфейс поведения хранилища. Store
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public interface Store<T> {
    void add(T employee);

    List<T> findBy(Predicate<T> filter);
}
