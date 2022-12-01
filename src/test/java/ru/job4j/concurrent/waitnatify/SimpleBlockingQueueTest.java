package ru.job4j.concurrent.waitnatify;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.12.2022
 */
class SimpleBlockingQueueTest {

    @Test
    void whenPoolTwoOfferThree() throws InterruptedException {
        CountDownLatch start = new CountDownLatch(2);
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread consumer = new Thread(() -> {
            start.countDown();
            try {
                queue.poll();
                queue.poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread producer = new Thread(() -> {
            start.countDown();
            try {
                queue.offer(1);
                queue.offer(2);
                queue.offer(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }

}