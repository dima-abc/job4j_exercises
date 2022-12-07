package ru.job4j.concurrent.pool.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 4. CompletableFuture [#361626 #274184]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.12.2022
 */
public class CompletableFutureExample {
    private static final Logger LOG = LoggerFactory.getLogger(CompletableFutureExample.class.getSimpleName());

    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            LOG.info("Вы: Я работаю");
            TimeUnit.SECONDS.sleep(1);
            count++;
        }
    }

    /**
     * 1. Пример runAsync()
     *
     * @return CompletableFuture<Void>
     */
    public static CompletableFuture<Void> goToTrash() {
        return CompletableFuture.runAsync(
                () -> {
                    LOG.info("Сын: Мам/Пап, я пошел выносить мусор");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        LOG.error(e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                    LOG.info("Сын: Мам/Пап, я вернулся!");
                }
        );
    }

    /**
     * 1. Пример runAsync()
     *
     * @throws Exception InterruptedException
     */
    public static void runAsyncExample() throws Exception {
        CompletableFuture<Void> gtt = goToTrash();
        iWork();
    }

    /**
     * 2. Пример supplyAsync()
     *
     * @param product String
     * @return CompletableFuture<String>
     */
    public static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(
                () -> {
                    LOG.info("Сын: Мам/Пап, я пошел в магазин");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        LOG.error(e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                    LOG.info("Сын: Мам/Пап, я купил {}", product);
                    return product;
                }
        );
    }

    /**
     * 2. Пример supplyAsync()
     *
     * @throws Exception InterruptedException
     */
    public static void supplyAsyncExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко");
        iWork();
        LOG.info("Куплено: {}", bm.get());
    }

    /**
     * 3. Пример thenRun()
     *
     * @throws InterruptedException Exception
     */
    public static void thenRunExample() throws InterruptedException {
        CompletableFuture<Void> gtt = goToTrash();
        gtt.thenRun(() -> {
            int count = 0;
            while (count < 3) {
                LOG.info("Сын: я мою руки");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    LOG.error(e.getMessage());
                    Thread.currentThread().interrupt();
                }
                count++;
            }
            LOG.info("Сын: Я помыл руки");
        });
        iWork();
    }

    /**
     * 4. Пример thenAccept()
     *
     * @throws Exception Exception
     */
    public static void thenAcceptExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко");
        bm.thenAccept((product) -> LOG.info("Сын: Я убрал {}", product));
        iWork();
        LOG.info("Куплено: {}" + bm.get());
    }

    /**
     * 5. Пример thenApply()
     * Этот метод принимает Function.
     * Также имеет доступ к результату.
     * Как раз благодаря этому,
     * мы можем произвести преобразование полученного результата.
     * Допишем второй пример.
     * Например, вы хотите,
     * чтобы после того, как сын принес молоко,
     * налил вам его в кружку.
     * Однако результат преобразования станет
     * доступным только при вызове get().
     *
     * @throws Exception Exception
     */
    public static void thenApplyExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко")
                .thenApply((prod) -> "Сын: я налил тебе в кружку " + prod + ". Держи.");
        iWork();
        LOG.info(bm.get());
    }

    /**
     * 6. Пример thenCompose().
     * Данный метод используется, если действия зависимы.
     * Т.е. сначала должно выполниться одно,
     * а только потом другое. Например, вам принципиально,
     * чтобы сын сначала выбросил мусор,
     * а только потом сходил за молоком.
     * В ситуации можно записать так
     *
     * @throws Exception Exception
     */
    public static void thenComposeExample() throws Exception {
        CompletableFuture<String> result = goToTrash()
                .thenCompose(a -> buyProduct("Молоко"));
        iWork();
        result.get();
    }

    /**
     * 7. Пример thenCombine()
     * Данный метод используется,
     * если действия могут быть выполнены независимо друг от друга.
     * Причем в качестве второго аргумента,
     * нужно передавать BiFunction – функцию,
     * которая преобразует результаты двух задач во что-то одно.
     * Например, первого сына вы посылаете выбросить мусор,
     * а второго сходить за молоком. В этой ситуации можно поступить так
     *
     * @throws Exception Exception
     */
    public static void thenCombineExample() throws Exception {
        CompletableFuture<String> result = buyProduct("Молоко")
                .thenCombine(buyProduct("Хлеб"),
                        (r1, r2) -> "Куплены " + r1 + " и " + r2);
        iWork();
        LOG.info(result.get());
    }

    /**
     * 8. Пример allOf()
     * Это метод возвращает CompletableFuture<Void>,
     * при этом обеспечивает выполнение всех задач.
     * Например, вы зовете всех членов семью к столу.
     * Надо дождаться пока все помоют руки
     *
     * @param name String
     * @return CompletableFuture
     */
    public static CompletableFuture<Void> washHands(String name) {
        return CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
            LOG.info("{}, моет руки", name);
        });
    }

    /**
     * 8. Пример allOf()
     *
     * @throws Exception Exception
     */
    public static void allOfExample() throws Exception {
        CompletableFuture<Void> all = CompletableFuture.allOf(
                washHands("Папа"), washHands("Мама"),
                washHands("Ваня"), washHands("Борис")
        );
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * 9. Пример anyOf()
     * Этот метод возвращает CompletableFuture<Object>.
     * Результатом будет первая выполненная задача.
     * На том же примере мы можем, например, узнать, кто сейчас моет руки.
     * Результаты запуск от запуска будут различаться.
     *
     * @param name String
     * @return CompletableFuture
     */
    public static CompletableFuture<String> whoWasHands(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
            return name + ", моет руки";
        });
    }

    /**
     * 9. Пример anyOf()
     *
     * @throws Exception Exception
     */
    public static void anyOfExample() throws Exception {
        CompletableFuture<Object> first = CompletableFuture.anyOf(
                whoWasHands("Папа"), whoWasHands("Мама"),
                whoWasHands("Ваня"), whoWasHands("Боря")
        );
        LOG.info("Кто сейчас моет руки?");
        TimeUnit.SECONDS.sleep(1);
        LOG.info(first.get().toString());
    }

    public static void main(String[] args) throws Exception {
        runAsyncExample();
        supplyAsyncExample();
        thenRunExample();
        thenAcceptExample();
        thenApplyExample();
        thenComposeExample();
        thenCombineExample();
        allOfExample();
        anyOfExample();
    }
}
