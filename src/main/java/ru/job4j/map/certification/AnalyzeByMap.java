package ru.job4j.map.certification;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1.3.3. Map. HashMap. LinkedHashMap
 * 4. Аттестация
 * В этом задании необходимо реализовать класс
 * для подсчета статистики по аттестатам учеников.
 * <p>
 * Класс AnalyzeByMap получает статистику по аттестатам.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.11.2022
 */
public class AnalyzeByMap {
    /**
     * Метод averageScore() - вычисляет общий средний балл.
     *
     * @param pupils Pupil list
     * @return double avg score.
     */
    public static double averageScore(List<Pupil> pupils) {
        int countSubject = 0;
        double scoreSum = 0L;
        for (Pupil pupil : pupils) {
            countSubject += pupil.subjects().size();
            scoreSum += sumSubjectScope(pupil.subjects());
        }
        return average(countSubject, scoreSum);
    }

    /**
     * Метод averageScoreByPupil() - вычисляет средний балл по каждому ученику.
     * То есть берем одного ученика и
     * считаем все его баллы за все предметы и
     * делим на количество предметов.
     * Возвращает список из объекта Label (имя ученика и средний балл).
     *
     * @param pupils List Pupil
     * @return List label.
     */
    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        int countSubject;
        double avgScorePupil;
        for (Pupil pupil : pupils) {
            countSubject = pupil.subjects().size();
            avgScorePupil = sumSubjectScope(pupil.subjects());
            result.add(new Label(pupil.name(), average(countSubject, avgScorePupil)));
        }
        return result;
    }

    /**
     * Метод averageScoreBySubject() - вычисляет средний балл по каждому предмету.
     * Например, собираем все баллы учеников по предмету география и
     * делим на количество учеников.
     * Возвращает список из объектов Label (название предмета и средний балл).
     *
     * @param pupils List Pupil
     * @return List Label
     */
    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Double> subjectGroup = subjectGroupToMap(pupils);
        return subjectGroup.keySet().stream()
                .map(k -> new Label(k, subjectGroup.get(k) / pupils.size()))
                .collect(Collectors.toList());
    }

    /**
     * Метод bestPupil() - возвращает лучшего ученика.
     * Лучшим считается ученик с наибольшим суммарным баллом по всем предметам.
     * Возвращает объект Label (имя ученика и суммарный балл).
     *
     * @param pupils List Pupil
     * @return Label
     */
    public static Label bestPupil(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        for (Pupil pupil : pupils) {
            list.add(new Label(pupil.name(), sumSubjectScope(pupil.subjects())));
        }
        list.sort(Comparator.reverseOrder());
        return list.get(0);
    }

    /**
     * Метод bestSubject() - возвращает предмет с наибольшим баллом для всех студентов.
     * Возвращает объект Label (имя предмета, сумма баллов каждого ученика по этому предмету).
     *
     * @param pupils List pupil
     * @return Label
     */
    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Double> subjectGroup = subjectGroupToMap(pupils);
        List<Label> result = subjectGroup.keySet().stream()
                .map(k -> new Label(k, subjectGroup.get(k)))
                .sorted(Comparator.reverseOrder()).toList();
        return result.get(0);
    }

    /**
     * Суммирует оценки по всем студентам по каждому предмету.
     *
     * @param pupils List Public.
     * @return Map
     */
    private static Map<String, Double> subjectGroupToMap(List<Pupil> pupils) {
        return pupils.stream()
                .flatMap(p -> p.subjects().stream())
                .collect(Collectors.groupingBy(
                        Subject::name,
                        Collectors.summingDouble(Subject::score)
                ));
    }

    /**
     * Подсчет общего количества баллов всех предметов.
     *
     * @param subjects Subject
     * @return double.
     */
    private static double sumSubjectScope(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::score)
                .reduce(Integer::sum)
                .orElse(0);
    }

    /**
     * Подсчет среднего количества.
     *
     * @param count Count element
     * @param sum   Sum element
     * @return double average
     */
    private static double average(int count, double sum) {
        if (count > 0) {
            return sum / count;
        }
        return count;
    }
}
