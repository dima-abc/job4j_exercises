package ru.job4j.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * 1.4.2. Stream API
 * 1.7. Создание стрима примитивов. Методы range() и rangeClosed()
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.11.2022
 */
class RangeMethodTest {
    @Test
    public void test() {
        List<Integer> result = RangeMethod.createStream(1, 3).boxed().collect(Collectors.toList());
        List<Integer> expect = List.of(1, 2, 3);
        assertThat(expect)
                .isEqualTo(result);
    }
}