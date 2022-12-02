package ru.job4j.concurrent.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.5. Non Blocking Algoritm
 * 0. CAS - операции [#6859 #270973]
 * CASStack не блокирующая потока безопасная очередь.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
@ThreadSafe
public class CASStack<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();
    private final AtomicInteger size = new AtomicInteger(0);
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        Node<T> ref;
        do {
            ref = head.get();
            newNode.next = ref;
        } while (!head.compareAndSet(ref, newNode));
        size.incrementAndGet();
    }

    public T pool() {
        Node<T> ref;
        Node<T> temp;
        do {
            ref = head.get();
            if (ref == null) {
                throw new IllegalArgumentException("Stack i,s empty");
            }
            temp = ref.next;
        } while (!head.compareAndSet(ref, temp));
        ref.next = null;
        size.decrementAndGet();
        return ref.value;
    }

    public Integer size() {
        return size.get();
    }


    private static final class Node<T> {
        final T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{value=" + value + ", next=" + next + '}';
        }
    }
}
