package ru.job4j.concurrent.pool;

import org.junit.jupiter.api.Test;
import ru.job4j.concurrent.waitnatify.SimpleBlockingQueue;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 1. Реализовать ThreadPool [#1099 #271981]
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.12.2022
 */
class ThreadPoolTest {
    @Test
    void whensExperiment() {
        ThreadPool pool = new ThreadPool();
        Runnable task = () -> {
            for (int i = 1; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.printf("\r%s Task work %d", Thread.currentThread().getName(), i);
            }
            System.out.println(System.lineSeparator());
        };
        IntStream.range(0, 10).forEach((i) -> pool.work(task));
        pool.shutdown();
    }
}