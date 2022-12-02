package ru.job4j.concurrent.singleton;

import ru.job4j.concurrent.cas.cache.CASCache;

import javax.management.ReflectionException;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 0. ThreadSafe Singleton [#102739 #271075]
 * 1.2. Реализация с применением поля final.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
public final class CASCacheSingle {
    private static final CASCache CAS_CACHE = new CASCache();

    private CASCacheSingle() {
        throw new IllegalArgumentException();
    }

    public static CASCache getInstance() {
        return CAS_CACHE;
    }
}
