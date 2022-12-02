package ru.job4j.concurrent.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.5. Non Blocking Algoritm
 * 0. CAS - операции [#6859 #270973]
 * CASCount не блокирующий счетчик.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int newInt = 0;
        do {
            newInt = count.get();
        } while (!count.compareAndSet(newInt, newInt + 1));
    }

    public int get() {
        return count.get();
    }
}
