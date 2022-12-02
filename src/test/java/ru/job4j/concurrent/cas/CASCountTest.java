package ru.job4j.concurrent.cas;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.5. Non Blocking Algoritm
 * 0. CAS - операции [#6859 #270973]
 * Не блокирующий счетчик
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
class CASCountTest {
    @Test
    void whenIncrementTwoThreadThen50() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CASCount casCount = new CASCount();
        Thread first = new Thread(
                () -> {
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                    for (int i = 0; i < 50; i++) {
                        casCount.increment();
                    }
                });
        Thread second = new Thread(
                () -> {
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                    for (int i = 0; i < 50; i++) {
                        casCount.increment();
                    }
                });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(casCount.get())
                .isEqualTo(100);
    }
}