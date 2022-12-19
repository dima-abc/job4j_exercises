package ru.job4j.concurrent;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.1. Threads
 * 11. Отладка в многопоточном приложении [#504954]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.12.2022
 */
public class DebugConcurrentDemo {
    public static void main(String[] args) {
        String name = "Поток №1";
        String name1 = "Поток №2";
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " прерван ");
            }
            System.out.println(Thread.currentThread().getName() + " завершен.");
        }, name);
        Thread t2 = new Thread(() -> {
            try {
                for (int i = 10; i < 13; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " прерван ");
            }
            System.out.println(Thread.currentThread().getName() + " завершен.");
        }, name1);
        t1.start();
        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван ");
        }
        System.out.println("Главный поток завершен.");
    }
}
