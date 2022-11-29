package ru.job4j.io;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.3. Синхронизация ресурсов
 * 1. Visibility. Общий ресурс вне критической секции [#1102 #268979]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.11.2022
 */
public interface LoadContent<T> {
    byte[] load(T in);
}
