package ru.job4j.concurrent.pool.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 4. CompletableFuture [#361626 #274184]
 * Пусть нам нужно посчитать суммы элементов по диагоналям матрицы.
 * Сколько диагоналей в матрице? 2 * N.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.12.2022
 */
public class MatrixDiagonal {
    private static final Logger LOG = LoggerFactory.getLogger(MatrixDiagonal.class.getSimpleName());

    public static int[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        int[] sums = new int[2 * n];
        Map<Integer, CompletableFuture<Integer>> futures = new HashMap<>();
        futures.put(0, getMainDiagonalTask(matrix, 0, n - 1, 0));
        for (int k = 1; k <= n; k++) {
            futures.put(k, getTask(matrix, 0, k - 1, k - 1));
            if (k < n) {
                futures.put(2 * n - k, getTask(matrix, n - k, n - 1, n - 1));
            }
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Integer> getTask(int[][] data, int startRow,
                                                     int endRow, int startColl) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            int col = startColl;
            for (int i = startRow; i <= endRow; i++) {
                sum += data[i][col];
                col--;
            }
            LOG.info("{} work result {}", Thread.currentThread().getName(), sum);
            return sum;
        });
    }

    public static CompletableFuture<Integer> getMainDiagonalTask(int[][] data, int startRow,
                                                                 int endRow, int startCol) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            int col = startCol;
            for (int i = startRow; i <= endRow; i++) {
                sum += data[i][col];
                col++;
            }
            LOG.info("{} work result {}", Thread.currentThread().getName(), sum);
            return sum;
        });
    }
}
