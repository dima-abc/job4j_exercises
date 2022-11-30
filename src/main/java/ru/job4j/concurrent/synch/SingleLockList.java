package ru.job4j.concurrent.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.3. Синхронизация ресурсов
 * 4. ThreadSafe динамический список [#1105 #269906]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 30.11.2022
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList(final List<T> list) {
        this.list = list;
    }

    public synchronized void add(final T value) {
        this.list.add(value);
    }

    public synchronized T get(int index) {
        return this.list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized List<T> copy(final List<T> origin) {
        return new ArrayList<>(origin);
    }
}
