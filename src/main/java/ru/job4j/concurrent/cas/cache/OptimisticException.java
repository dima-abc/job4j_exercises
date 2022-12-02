package ru.job4j.concurrent.cas.cache;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.5. Non Blocking Algoritm
 * 1. Неблокирующий кеш [#4741 #271070]
 * OptimisticException исключения для некорректной версии модели кэша
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
