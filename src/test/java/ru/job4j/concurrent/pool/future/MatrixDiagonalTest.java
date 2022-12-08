package ru.job4j.concurrent.pool.future;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 4. CompletableFuture [#361626 #274184]
 * Пусть нам нужно посчитать суммы элементов по диагоналям матрицы.
 * Сколько диагоналей в матрице? 2 * N.
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.12.2022
 */
class MatrixDiagonalTest {
    @Test
    void whenSumDiagonalMatrix33() throws Exception {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] actual = MatrixDiagonal.asyncSum(matrix);
        int[] expect = {15, 1, 6, 15, 14, 9};
        assertThat(actual)
                .isEqualTo(expect);
    }
}