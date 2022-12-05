package ru.job4j.concurrent.pool.forkjoinpool.search;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 3. ForkJoinPool [#315067 #273731]
 * ParallelMergeSearch поиск элемента в массиве через ForkJoinPool.
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.12.2022
 */
class ParallelMergeSearchTest {
    @Test
    void whenFindElementByTypeString() {
        String[] arrayString = new String[]{"aa", "gg", "ee", "zz", "ll", "yy", "pp",
                "xx", "oo", "tt", "dd", "rr", "bb", "mm"};
        String findElement = "tt";
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int expect = 9;
        int actual = forkJoinPool.invoke(
                new ParallelMergeSearch<>(arrayString, findElement, 0, arrayString.length - 1)
        );
        assertThat(actual)
                .isEqualTo(expect);
    }

    @Test
    void whenFindElementByTypeInteger() {
        Integer[] arrayInteger = new Integer[100];
        for (int i = 0; i < arrayInteger.length; i++) {
            arrayInteger[i] = i;
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer findElement = 55;
        int expect = 55;
        int actual = forkJoinPool.invoke(
                new ParallelMergeSearch<>(arrayInteger, findElement, 0, arrayInteger.length - 1)
        );
        assertThat(actual)
                .isEqualTo(expect);
    }

    @Test
    void whenFindElementBaArraySizeNine() {
        Integer[] arrayInteger = new Integer[9];
        for (int i = 0; i < arrayInteger.length; i++) {
            arrayInteger[i] = i;
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer findElement = 5;
        int expect = 5;
        int actual = forkJoinPool.invoke(
                new ParallelMergeSearch<>(arrayInteger, findElement, 0, arrayInteger.length - 1)
        );
        assertThat(actual)
                .isEqualTo(expect);
    }

    @Test
    void whenFindElementThenNotFindByTypeInteger() {
        Integer[] arrayInteger = new Integer[100];
        for (int i = 0; i < arrayInteger.length; i++) {
            arrayInteger[i] = i;
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer findElement = 101;
        int expect = -1;
        int actual = forkJoinPool.invoke(
                new ParallelMergeSearch<>(arrayInteger, findElement, 0, arrayInteger.length - 1)
        );
        assertThat(actual)
                .isEqualTo(expect);
    }

}