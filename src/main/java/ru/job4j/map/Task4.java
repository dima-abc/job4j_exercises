package ru.job4j.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 2.1.6. Map
 * 4. Outer full join для массивов чисел.
 * В это задании необходимо вычислить уникальный элементы, которые есть в двух массивах чисел.
 * Заданы два массива чисел, нужно вернуть третий массив,
 * который будет состоять только из уникальных элементов,
 * которые есть в двух массивах.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
public class Task4 {
    private static final Logger LOG = LoggerFactory.getLogger(Task4.class.getSimpleName());

    public static List<Integer> extractUnique(List<Integer> left, List<Integer> right) {
        Map<Integer, Integer> map = toCountMap(left, right);
        return map.entrySet().stream()
                .filter(k -> k.getValue().equals(1))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static <T> Map<T, Integer> toCountMap(List<T> left, List<T> right) {
        Map<T, Integer> map = new HashMap<>();
        for (T type : left) {
            map.computeIfPresent(type, (k, v) -> v + 1);
            map.putIfAbsent(type, 1);
        }
        for (T type : right) {
            map.computeIfPresent(type, (k, v) -> v + 1);
            map.putIfAbsent(type, 1);
        }
        LOG.info(map.toString());
        return map;
    }
}
