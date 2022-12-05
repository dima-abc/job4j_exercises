package ru.job4j.concurrent.pool.forkjoinpool.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 3. ForkJoinPool [#315067 #273731] Пример.
 * ParallelMergeSort при помощи ForkJoinPool реализует алгоритм сортировки слиянием.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.12.2022
 */
public class ParallelMergeSort extends RecursiveTask<int[]> {
    private static final Logger LOG = LoggerFactory.getLogger(ParallelMergeSort.class.getSimpleName());
    private final int[] array;
    private final int from;
    private final int to;

    public ParallelMergeSort(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected int[] compute() {
        if (from == to) {
            return new int[]{array[from]};
        }
        int mid = (from + to) / 2;
        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, mid);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to);
        leftSort.fork();
        rightSort.fork();
        int[] left = leftSort.join();
        int[] right = rightSort.join();
        LOG.info("{} LEFT:{}", Thread.currentThread().getName(), left);
        LOG.info("{} RIGHT:{}", Thread.currentThread().getName(), right);
        return MergeSort.merge(left, right);
    }

    public static int[] sort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
    }
}
