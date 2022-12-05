package ru.job4j.concurrent.pool.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Format;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 2. ExecutorService рассылка почты. [#63097 #273206]
 * EmailNotification сервис генерации и отправки писем на основе модели User.
 * 1. Реализовать сервис для рассылки почты. Создайте класс EmailNotification.
 * 2. В классе будет иметь метод emailTo(User user) - он должен через ExecutorService отправлять почту.
 * Так же добавьте метод close() - он должен закрыть pool.
 * То есть в классе EmailNotification должно быть поле pool,
 * которые используется в emailTo и close().
 * 3. Модель User описывают поля username, email.
 * 4. Метод emailTo должен брать данные пользователя и подставлять в шаблон
 * subject = Notification {username} to email {email}.
 * body = Add a new event to {username}
 * 5. Создайте метод public void send(String subject, String body, String email) - он должен быть пустой.
 * 6. Через ExecutorService создайте задачу,
 * которая будет создавать данные для пользователя и передавать их в метод send.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.12.2022
 */
public class EmailNotification {
    private static final Logger LOG = LoggerFactory.getLogger(EmailNotification.class.getSimpleName());
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    /**
     * emailTo(User user) - через ExecutorService отправлять почту.
     * emailTo должен брать данные пользователя и подставлять в шаблон
     * subject = Notification {username} to email {email}.
     * body = Add a new event to {username}
     *
     * @param user User
     */
    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.username(), user.email());
        String body = String.format("Add a new event to %s", user.username());
        pool.submit(() -> send(subject, body, user.email()));
    }

    /**
     * @param subject Subject email
     * @param body    Body email
     * @param email   Email address
     */
    public void send(String subject, String body, String email) {
    }

    /**
     * Метод закрывает все потоки в pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
