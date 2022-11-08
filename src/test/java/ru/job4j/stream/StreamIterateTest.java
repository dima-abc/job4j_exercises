package ru.job4j.stream;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

/**
 * 1.4.2. Stream API
 * 1.4. Создание стрима. Метод Stream.iterate()
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.11.2022
 */
class StreamIterateTest {
    @Test
    public void test() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Integer[] data = {1, 2, 3, 4};
        StreamIterate.showArray(data);
        String ln = System.lineSeparator();
        assertThat(out.toString())
                .isEqualTo(
                        "1" + ln
                                + "3" + ln
                );
    }
}