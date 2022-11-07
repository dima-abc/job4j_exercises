package ru.job4j.map;

import org.junit.jupiter.api.Test;

import java.util.List;


import static org.assertj.core.api.Assertions.*;

/**
 * 2.1.6. Map
 * 3. Inner Join для массивов
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
class Task3Test {

    @Test
    public void whenNoDuplicate() {
        var right = List.of(1, 2, 3);
        var left = List.of(4, 5, 6);
        var expected = List.of();
        var result = Task3.extractDuplicates(left, right);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenFullDuplicate() {
        var right = List.of(1, 2, 3);
        var left = List.of(1, 2, 3);
        var expected = List.of(1, 2, 3);
        var result = Task3.extractDuplicates(left, right);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenLeftDuplicate() {
        var right = List.of(1, 2, 3);
        var left = List.of(1, 2, 3, 4, 5, 6);
        var expected = List.of(1, 2, 3);
        var result = Task3.extractDuplicates(left, right);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenRightDuplicate() {
        var right = List.of(1, 2, 3, 4, 5, 6);
        var left = List.of(1, 2, 3);
        var expected = List.of(1, 2, 3);
        var result = Task3.extractDuplicates(left, right);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenLeftDoubleDuplicate() {
        var right = List.of(1, 2, 3);
        var left = List.of(1, 2, 3, 1, 2, 3);
        var expected = List.of(1, 2, 3);
        var result = Task3.extractDuplicates(left, right);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenRightDoubleDuplicate() {
        var right = List.of(1, 2, 3, 1, 2, 3);
        var left = List.of(1, 2, 3);
        var expected = List.of(1, 2, 3);
        var result = Task3.extractDuplicates(left, right);
        assertThat(result).isEqualTo(expected);
    }
}