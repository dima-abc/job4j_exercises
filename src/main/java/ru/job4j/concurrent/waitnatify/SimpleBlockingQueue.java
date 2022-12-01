package ru.job4j.concurrent.waitnatify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.4. Wait, Notify, NotifyAll
 * 1. Реализовать шаблон Producer Consumer. [#1098 #270249]
 * Задание.
 * 1. Реализовать методы poll() и offer().
 * 2. Написать тесты. В тестах должны быть две нити: одна производитель, другая потребитель.
 * Через методы join() добиться последовательного выполнения программы.
 * Очередь организована по принципу FIFO
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.12.2022
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleBlockingQueue.class.getSimpleName());
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int sizeQueue;

    public SimpleBlockingQueue(int size) {
        this.sizeQueue = size;
    }

    /**
     * Метод вставляет элемент в конец очереди
     *
     * @param value T
     */
    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() >= sizeQueue) {
                wait();
                LOG.info("{} ОЖИДАНИЕ ДОБОВЛЕНИЯ нет места значения:{}",
                        Thread.currentThread().getName(), value);
            }
            queue.offer(value);
            notifyAll();
            LOG.info("{} ДОБАВЛЕНИЕ В ОЧЕРЕДЬ значение:{}",
                    Thread.currentThread().getName(), value);
        }
    }

    /**
     * Метод извлекает и удаляет данные из начала очереди
     *
     * @return T type элемент очереди.
     */
    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.peek() == null) {
                wait();
                LOG.info("{} ОЖИДАНИЕ ПОЛУЧЕНИЯ данных.",
                        Thread.currentThread().getName());
            }
            T rsl = queue.poll();
            notifyAll();
            LOG.info("{} ДАННЫЕ ПОЛУЧЕНЫ:{}",
                    Thread.currentThread().getName(), rsl);
        }
        return queue.poll();
    }
}
