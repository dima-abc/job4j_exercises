package ru.job4j.concurrent.singleton;

import ru.job4j.concurrent.cas.cache.CASCache;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 0. ThreadSafe Singleton [#102739 #271075]
 * HOLDER
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
public final class CASCacheHolder {
    private CASCacheHolder() {
        throw new IllegalArgumentException();
    }

    public static CASCache getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final CASCache INSTANCE = new CASCache();
    }
}
