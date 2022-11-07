package ru.job4j.map;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 2.1.6. Map
 * 4. Outer full join для массивов чисел.
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
class Task4Test {
    @Test
    public void whenNoUnique() {
        var right = List.of(1, 2, 3);
        var left = List.of(1, 2, 3);
        var expected = List.of();
        var result = Task4.extractUnique(left, right);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenFullUnique() {
        var right = List.of(1, 2, 3);
        var left = List.of(4, 5, 6);
        var expected = List.of(1, 2, 3, 4, 5, 6);
        var result = Task4.extractUnique(left, right);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenLeftDuplicate() {
        var right = List.of(1, 2, 3);
        var left = List.of(1, 2, 3, 4, 5, 6);
        var expected = List.of(4, 5, 6);
        var result = Task4.extractUnique(left, right);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenRightDuplicate() {
        var right = List.of(1, 2, 3, 4, 5, 6);
        var left = List.of(1, 2, 3);
        var expected = List.of(4, 5, 6);
        var result = Task4.extractUnique(left, right);
        assertThat(result).isEqualTo(expected);
    }
}