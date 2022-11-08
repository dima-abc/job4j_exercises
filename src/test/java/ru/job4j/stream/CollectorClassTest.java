package ru.job4j.stream;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 1.4.2. Stream API
 * 2.0. Понятие редукции. Произвольный Collector для сборки в коллекцию
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.11.2022
 */
class CollectorClassTest {

    @Test
    void collect() {
        assertThat(List.of(1, 2, 3))
                .isEqualTo(CollectorClass.collect(List.of(1, 2, 3)));
    }

    @Test
    void collectDeque() {
        Deque<Integer> expect = new LinkedList<>(List.of(3, 2, 1));
        Deque<Integer> actual = CollectorClass.collectDeque(List.of(1, 2, 3));
        assertThat(actual)
                .isEqualTo(expect);
    }
}