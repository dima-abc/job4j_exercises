package ru.job4j.stream;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 1.4.2. Stream API
 * 1.0. flatMap для итератора
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.11.2022
 */
class FlatItTest {
    @Test
    public void whenIt() {
        Iterator<Iterator<Integer>> it = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        assertThat(
                FlatIt.flatten(it))
                .isEqualTo(List.of(1, 2, 3));
    }
}