package ru.job4j.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.1. Threads
 * 3. Состояние нити. [#229175 #267118]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.11.2022
 */
public class ThreadState {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadState.class.getSimpleName());

    public static void main(String[] args) {
        Thread first = new Thread(() -> {
        });
        Thread second = new Thread(() -> {
        });
        LOG.info("{} + {}", first.getName(), first.getState());
        LOG.info("{} + {}", second.getName(), second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            LOG.info("{} + {}", first.getName(), first.getState());
            LOG.info("{} + {}", second.getName(), second.getState());
        }
        LOG.info("{} + {}", first.getName(), first.getState());
        LOG.info("{} + {}", second.getName(), second.getState());
    }

}
