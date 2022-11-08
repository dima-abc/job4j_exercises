package ru.job4j.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 1.4.2. Stream API
 * 2.1. Понятие редукции. Произвольный Collector для подсчета
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.11.2022
 */
class CollectorArithmeticTest {
    @Test
    public void whenWithout6() {
        int out = CollectorArithmetic.collectArithmetic(List.of(1, 2, 3));
        assertThat(6)
                .isEqualTo(out);
    }

    @Test
    public void whenWith0() {
        int out = CollectorArithmetic.collectArithmetic(List.of(0, 2, 3));
        assertThat(0)
                .isEqualTo(out);
    }

    @Test
    public void whenWithout120() {
        int out = CollectorArithmetic.collectArithmetic(List.of(5, 2, 3, 4));
        assertThat(120)
                .isEqualTo(out);
    }
}