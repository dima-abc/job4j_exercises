package ru.job4j.concurrent.waitnatify;

/**
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.12.2022
 */
public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        final Thread consumer = new Thread(
                () -> {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            System.out.println(queue.poll());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int i = 0; i != 3; i++) {
                        try {
                            queue.offer(i);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }).start();
    }
}
