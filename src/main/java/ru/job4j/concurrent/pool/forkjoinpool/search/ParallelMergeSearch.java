package ru.job4j.concurrent.pool.forkjoinpool.search;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 3. ForkJoinPool [#315067 #273731]
 * ParallelMergeSearch поиск элемента в массиве через ForkJoinPool.
 * 1. Реализовать параллельный поиск индекса в массиве объектов.
 * В целях оптимизации, если размер массива не больше 10,
 * использовать обычный линейный поиск. Метод поиска должен быть обобщенным.
 * 2. Написать тесты на случаи:
 * - разные типы данных
 * - линейный и рекурсивный поиск (малый и большой размеры массива)
 * - элемент не найден
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.12.2022
 */
public class ParallelMergeSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T findEl;
    private final int leftI;
    private final int rightI;

    public ParallelMergeSearch(T[] array, T findEl, int leftI, int rightI) {
        this.array = array;
        this.findEl = findEl;
        this.leftI = leftI;
        this.rightI = rightI;
    }

    @Override
    protected Integer compute() {
        if (rightI - leftI < 10) {
            return recursiveFindIndex(leftI, rightI);
        }
        int mid = (leftI + rightI) / 2;
        ParallelMergeSearch<T> leftSearch = new ParallelMergeSearch<>(array, findEl, leftI, mid);
        ParallelMergeSearch<T> rightSearch = new ParallelMergeSearch<>(array, findEl, mid + 1, rightI);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return Math.max(left, right);
    }

    public int findElement() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        return forkJoinPool.invoke(new ParallelMergeSearch<>(array, findEl, 0, array.length - 1));
    }

    /**
     * Рекурсивный поиск элемента в массиве.
     *
     * @param leftI  left index
     * @param rightI right index
     * @return Index element
     */
    private int recursiveFindIndex(int leftI, int rightI) {
        if (rightI < leftI) {
            return -1;
        }
        if (findEl.equals(array[leftI])) {
            return leftI;
        }
        if (findEl.equals(array[rightI])) {
            return rightI;
        }
        return recursiveFindIndex(leftI + 1, rightI - 1);
    }
}
