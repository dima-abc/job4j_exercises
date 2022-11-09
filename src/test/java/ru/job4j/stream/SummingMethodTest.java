package ru.job4j.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.stream.SummingMethod.*;

/**
 * 1.4.2. Stream API
 * 4.2. Группировка элементов и сумма. Метод summingInt()
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 09.11.2022
 */
class SummingMethodTest {
    @Test
    public void testSummingPair() {
        Bill b1 = new Bill(1);
        Bill b2 = new Bill(2);
        Bill b3 = new Bill(3);
        Bill b4 = new Bill(4);
        Bill b5 = new Bill(5);
        Bill b6 = new Bill(6);
        User u1 = new User("u1", List.of(b1));
        User u2 = new User("u2", List.of(b2, b3));
        User u3 = new User("u3", List.of(b4, b5, b6));
        Map<String, Integer> result = SummingMethod.summingPair(List.of(u1, u2, u3));
        Map<String, Integer> expect = Map.of(
                "u1", 1,
                "u2", 5,
                "u3", 15
        );
        assertThat(result)
                .isEqualTo(expect);
    }

    @Test
    public void testSumming() {
        Bill b1 = new Bill(1);
        Bill b2 = new Bill(2);
        Bill b3 = new Bill(3);
        Bill b4 = new Bill(4);
        Bill b5 = new Bill(5);
        Bill b6 = new Bill(6);
        User u1 = new User("u1", List.of(b1));
        User u2 = new User("u2", List.of(b2, b3));
        User u3 = new User("u3", List.of(b4, b5, b6));
        Map<String, Integer> result = SummingMethod.summing(List.of(u1, u2, u3));
        Map<String, Integer> expect = Map.of(
                "u1", 1,
                "u2", 5,
                "u3", 15
        );
        assertThat(result)
                .isEqualTo(expect);
    }
}