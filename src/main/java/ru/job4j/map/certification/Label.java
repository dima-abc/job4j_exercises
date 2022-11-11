package ru.job4j.map.certification;

/**
 * 1.3.3. Map. HashMap. LinkedHashMap
 * 4. Аттестация
 * В этом задании необходимо реализовать класс
 * для подсчета статистики по аттестатам учеников.
 * <p>
 * Класс Label содержит результаты:
 * имя и баллы.
 * Этот класс используется как для учеников, так и для предметов.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.11.2022
 */
public record Label(String name, double score) implements Comparable<Label> {
    @Override
    public int compareTo(Label o) {
        return Double.compare(this.score, o.score);
    }
}
