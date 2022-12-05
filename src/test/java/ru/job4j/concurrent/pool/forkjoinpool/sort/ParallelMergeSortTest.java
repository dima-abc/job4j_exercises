package ru.job4j.concurrent.pool.forkjoinpool.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 3. ForkJoinPool [#315067 #273731] Пример.
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.12.2022
 */
class ParallelMergeSortTest {
    @Test
    void whenArraySort() {
        int[] array = new int[]{3, 1, 0, 8, 2, 0, 7, 6, 8, 2, 9, 5, 4, 1, 8};
        int[] expect = new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 8, 8, 9};
        int[] actual = ParallelMergeSort.sort(array);
        assertThat(actual)
                .isEqualTo(expect);
    }
}