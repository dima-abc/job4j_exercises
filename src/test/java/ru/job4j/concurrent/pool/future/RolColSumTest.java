package ru.job4j.concurrent.pool.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.concurrent.pool.future.RolColSum.*;

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
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.12.2022
 */
class RolColSumTest {
    @Test
    void whenConsistentSumRowColMatrix3x3() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] actual = RolColSum.sum(matrix);
        Sums[] expect = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18)
        };
        assertThat(actual)
                .isEqualTo(expect);
    }

    @Test
    void whenConsistentSumRowColMatrix5x5() {
        int[][] matrix = new int[5][5];
        int k = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = k++;
            }
        }
        Sums[] actual = RolColSum.sum(matrix);
        Sums[] expect = {
                new Sums(15, 55),
                new Sums(40, 60),
                new Sums(65, 65),
                new Sums(90, 70),
                new Sums(115, 75)
        };
        assertThat(actual)
                .isEqualTo(expect);
    }

    @Test
    void whenAsyncSumRowColMatrix3x3() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] actual = RolColSum.asyncSum(matrix);
        Sums[] expect = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18)
        };
        assertThat(actual)
                .isEqualTo(expect);
    }

    @Test
    void whenAsyncSumRowColMatrix5x5() {
        int[][] matrix = new int[5][5];
        int k = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = k++;
            }
        }
        Sums[] actual = RolColSum.asyncSum(matrix);
        Sums[] expect = {
                new Sums(15, 55),
                new Sums(40, 60),
                new Sums(65, 65),
                new Sums(90, 70),
                new Sums(115, 75)
        };
        assertThat(actual)
                .isEqualTo(expect);
    }

    @Test
    void whenAsyncSumRowColMatrix100x100() {
        int[][] matrix = new int[100][100];
        int k = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j] = k++;
            }
        }
        Sums[] actual = RolColSum.asyncSum(matrix);
        Sums[] expect = sum(matrix);
        assertThat(actual)
                .isEqualTo(expect);
    }
}