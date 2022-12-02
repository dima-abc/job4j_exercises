package ru.job4j.concurrent.cas;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
class CASStackTest {
    @Test
    void whenTest() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CASStack<Integer> casStack = new CASStack<>();
        Thread first = new Thread(
                () -> {
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                    casStack.push(0);
                    casStack.push(1);
                    casStack.push(2);
                    casStack.push(3);
                    casStack.push(4);
                });
        Thread second = new Thread(
                () -> {
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                    casStack.push(5);
                    casStack.push(6);
                    casStack.push(7);
                    casStack.push(8);
                    casStack.push(9);
                });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(casStack.size())
                .isEqualTo(10);
    }
}