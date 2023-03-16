package ru.job4j.map;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 2.1.6. Map
 * 3. Inner Join для массивов
 * В это задании необходимо вычислить элементы,
 * которые одновременно есть в двух массивах чисел.
 * Заданы два массива чисел, нужно вернуть третий массив, который будет состоять из таких чисел,
 * которые одновременно есть в двух массивах.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
public class Task3 {
    public static List<Integer> extractDuplicates(List<Integer> left, List<Integer> right) {
        Set<Integer> set = listToSet(left);
        return getInnerList(right, set);
    }

    public static <T> Set<T> listToSet(List<T> list) {
        return new HashSet<>(list);
    }

    public static <T> List<T> getInnerList(List<T> list, Set<T> set) {
        return list.stream()
                .distinct()
                .filter(set::contains)
                .collect(Collectors.toList());
    }
}
