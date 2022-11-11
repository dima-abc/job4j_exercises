package ru.job4j.map.certification;

import java.util.List;

/**
 * 1.3.3. Map. HashMap. LinkedHashMap
 * 4. Аттестация
 * В этом задании необходимо реализовать класс
 * для подсчета статистики по аттестатам учеников.
 * <p>
 * Класс Pupil описывает ученика.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.11.2022
 */
public record Pupil(String name, List<Subject> subjects) {
}
