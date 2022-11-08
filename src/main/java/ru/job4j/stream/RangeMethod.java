package ru.job4j.stream;

import java.util.stream.IntStream;

/**
 * 1.4.2. Stream API
 * 1.7. Создание стрима примитивов. Методы range() и rangeClosed()
 * Стримы целых чисел (IntStream, LongSteam) имеют также еще один способ создания стрима. Это методы range(int start, int end) и rangeClosed(int start, int end)
 * Первый метод, создает стрим в диапозоне [start, end)
 * Второй метод, создает стрим в диапозоне [start, end]
 * Например,
 * IntStream.range(1, 5) => [1, 2, 3, 4]
 * IntStream.rangeClosed(1, 5) => [1, 2, 3, 4, 5]
 * Ваша задача создать стрим с использованием метода rangeClosed()
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.11.2022
 */
public class RangeMethod {
    public static IntStream createStream(int start, int end) {
        return IntStream.rangeClosed(start, end);
    }
}
