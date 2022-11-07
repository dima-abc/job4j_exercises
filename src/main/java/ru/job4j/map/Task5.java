package ru.job4j.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 2.1.6. Map
 * 5. Супер работник
 * Менеджер компании просить написать утилиту,
 * которая будет показывать список сотрудников на которых назначено больше одной задачи.
 * Задан список задач, где поле assign - это ID сотрудник.
 * Необходимо написать метод,
 * который вернет список сотрудников на которых назначено больше одной задачи.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
public class Task5 {
    public static List<Integer> multiAssign(List<Task> tasks) {
        Map<Integer, Integer> map = taskToMap(tasks);
        return map.entrySet().stream()
                .filter(k -> k.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public record Task(Integer taskId, Integer assignId) {
    }

    public static Map<Integer, Integer> taskToMap(List<Task> tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Task task : tasks) {
            map.computeIfPresent(task.assignId, (k, v) -> v + 1);
            map.putIfAbsent(task.assignId, 1);
        }
        return map;
    }
}
