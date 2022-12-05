package ru.job4j.concurrent.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.concurrent.waitnatify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 1. Реализовать ThreadPool [#1099 #271981]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.12.2022
 */
public class ThreadPool {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadPool.class.getSimpleName());
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        LOG.info("Количество процессоров равно размеру пула потоков:{}", size);
        for (int i = 0; i < size; i++) {
            Thread thread = new MyThead(tasks);
            threads.add(thread);
            thread.start();
        }
    }

    public void work(Runnable job) {
        try {
            this.tasks.offer(job);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        this.threads.forEach((t) -> {
            t.interrupt();
            LOG.info("{} нить остановлена", t.getName());
        });
    }

    private static class MyThead extends Thread {
        private final SimpleBlockingQueue<Runnable> tasks;

        public MyThead(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable runnable = tasks.poll();
                    if (runnable != null) {
                        runnable.run();
                        LOG.info("{} нить получила задачу", threadName);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOG.error("{} остановка:", threadName);
                }
            }
        }
    }
}
