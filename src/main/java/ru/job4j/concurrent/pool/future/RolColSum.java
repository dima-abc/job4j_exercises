package ru.job4j.concurrent.pool.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 4. CompletableFuture [#361626 #274184]
 * Задание:
 * 1. Дан каркас класса. Ваша задача подсчитать суммы по строкам и столбцам квадратной матрицы.
 * - sums[i].rowSum - сумма элементов по i строке
 * - sums[i].colSum  - сумма элементов по i столбцу
 * Реализовать последовательную версию программы
 * 3. Реализовать асинхронную версию программы. i - я задача считает сумму по i столбцу и i строке
 * 4. Написать тесты
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.12.2022
 */
public class RolColSum {
    private static final Logger LOG = LoggerFactory.getLogger(RolColSum.class.getSimpleName());

    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{rowSum=" + rowSum + ", colSum=" + colSum + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (sums[i] == null) {
                    sums[i] = new Sums(0, 0);
                }
                sums[i].rowSum += matrix[i][j];
                sums[i].colSum += matrix[j][i];
            }
        }
        return sums;
    }

    /**
     * Метод выполняет асинхронный подсчет
     * суммы элементов в строке и в столбце квадратной матрице.
     * Результат массив объектов Sum.
     *
     * @param matrix Квадратная матрица чисел.
     * @return Array Sum
     */
    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int key = i;
            getTask(matrix, i)
                    .thenAccept(sum -> sums[key] = sum);
        }
        return sums;
    }

    /**
     * Метод генерирует асинхронную задачу.
     * В 1 задаче проходит работа по подсчету суммы одной строки и одного столбца,
     * То есть возвращается один заполненный объект Sum
     *
     * @param data       Array int
     * @param numRowColl start sum row coll
     * @return new Sum object
     */
    private static CompletableFuture<Sums> getTask(int[][] data, int numRowColl) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums(0, 0);
            for (int i = 0; i < data.length; i++) {
                sums.rowSum += data[numRowColl][i];
                sums.colSum += data[i][numRowColl];
            }
            LOG.info("{} work result:{}", Thread.currentThread().getName(), sums);
            return sums;
        });
    }
}
