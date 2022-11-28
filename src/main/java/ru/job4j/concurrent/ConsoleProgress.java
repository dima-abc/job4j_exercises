package ru.job4j.concurrent;

/**
 * 3.Мидл
 * 3.1.Multithreading
 * 3.1.1. Threads
 * 5. Прерывание нити [#1019 #267516]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.11.2022
 */
public class ConsoleProgress implements Runnable {
    private final String[] process = new String[]{"\\", "|", "/"};

    @Override
    public void run() {
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rLoad: " + process[i++]);
            if (i == 3) {
                i = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(3000);
        progress.interrupt();
    }
}
