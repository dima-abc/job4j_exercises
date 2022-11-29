package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.3. Синхронизация ресурсов
 * 2. JCIP. Настройка библиотеки [#268575 #269021]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.11.2022
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    public int get() {
        synchronized (this) {
            return this.value;
        }
    }
}
