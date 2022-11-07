package ru.job4j.map;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 2.1.6. Map
 * 5. Супер работник
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
class Task5Test {
    @Test
    public void whenMulti() {
        var input = List.of(
                new Task5.Task(1, 1),
                new Task5.Task(1, 2),
                new Task5.Task(1, 1)
        );
        var expected = List.of(1);
        var result = Task5.multiAssign(input);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenOnlyUnique() {
        var input = List.of(
                new Task5.Task(1, 1),
                new Task5.Task(1, 2),
                new Task5.Task(1, 3)
        );
        var expected = List.of();
        var result = Task5.multiAssign(input);
        assertThat(result).isEqualTo(expected);
    }
}