package ru.job4j.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * 1.4.2. Stream API
 * 7.1. Optional и Stream. Метод flatMap()
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 10.11.2022
 */
class OptionalFlatMapTest {

    @Test
    public void whenExist() {
        Optional<Integer> actual = Optional.of("Hello.java".length());
        Optional<Integer> result = OptionalFlatMap.flatMap(List.of("Hello.java", "Hello.js", "Hello.py"));
        assertThat(result)
                .isEqualTo(actual);
    }

    @Test
    public void whenNotExist() {
        Optional<Integer> actual = Optional.empty();
        Optional<Integer> result = OptionalFlatMap.flatMap(List.of("Hello.cpp", "Hello.js", "Hello.py"));
        assertThat(result)
                .isEqualTo(actual);
    }
}