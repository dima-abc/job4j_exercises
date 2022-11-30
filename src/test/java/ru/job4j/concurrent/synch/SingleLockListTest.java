package ru.job4j.concurrent.synch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.3. Синхронизация ресурсов
 * 4. ThreadSafe динамический список [#1105 #269906]
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 30.11.2022
 */
class SingleLockListTest {
    @Test
    public void whenIt() {
        var init = new ArrayList<Integer>();
        SingleLockList<Integer> list = new SingleLockList<>(init);
        list.add(1);
        var it = list.iterator();
        list.add(2);
        assertThat(1)
                .isEqualTo(it.next());
    }

    @Test
    public void whenAdd() throws InterruptedException {
        var init = new ArrayList<Integer>();
        SingleLockList<Integer> list = new SingleLockList<>(init);
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl)
                .isEqualTo(Set.of(1, 2));
    }
}