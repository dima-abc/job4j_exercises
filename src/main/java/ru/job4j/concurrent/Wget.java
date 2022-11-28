package ru.job4j.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3.Мидл
 * 3.1.Multithreading
 * 3.1.1. Threads
 * 4. Режим ожидания. [#231217 #267270]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.11.2022
 */
public class Wget {
    private static final Logger LOG = LoggerFactory.getLogger(Wget.class.getSimpleName());

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                System.out.print("\rLoading : " + i + "%");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    LOG.error("Thread error: {}", e.getMessage());
                }
            }
        });
        thread.start();
    }
}
