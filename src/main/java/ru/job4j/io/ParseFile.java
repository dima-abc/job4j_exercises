package ru.job4j.io;

import java.io.File;
import java.util.function.Predicate;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.3. Синхронизация ресурсов
 * 1. Visibility. Общий ресурс вне критической секции [#1102 #268979]
 * Поправьте код с ошибками в коде.
 * - Избавиться от get set за счет передачи File в конструктор.
 * - Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
 * - Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
 * - Нарушен принцип единой ответственности. Тут нужно сделать два класса.
 * - Методы getContent написаны в стиле копипаста. Нужно применить шаблон стратегия. content(Predicate<Character> filter)
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.11.2022
 */
public final class ParseFile {

    public String getContent(Predicate<Character> predicate, byte[] content) {
        StringBuilder rsl = new StringBuilder();
        for (byte b : content) {
            if (predicate.test((char) b)) {
                rsl.append((char) b);
            }
        }
        return rsl.toString();
    }
}
