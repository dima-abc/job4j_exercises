package ru.job4j.concurrent.pool.forkjoinpool.search;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.6. Пулы
 * 3. ForkJoinPool [#315067 #273731]
 * FindIndex метод рекурсивна ищет индекс элемента в массиве.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.12.2022
 */
public class FindIndex<T> {
    private static final int FAIL = -1;
    private static int i = 0;

    /**
     * Линейный поиск
     *
     * @param array   Array T
     * @param element T object
     * @return int Index Array or -1.
     */
    public int searchIndex(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return FAIL;
    }

    /**
     * Рекурсивный линейный поиск элемента в массиве.
     *
     * @param array  Array T
     * @param findEl T type element
     * @param lefI   Left index int
     * @param rigI   Right index int
     * @return index Array
     */
    public int recursiveSearch(T[] array, T findEl, int lefI, int rigI) {
        if (rigI < lefI) {
            return FAIL;
        }
        if (findEl.equals(array[lefI])) {
            return lefI;
        }
        if (findEl.equals(array[rigI])) {
            return rigI;
        }
        return recursiveSearch(array, findEl, lefI + 1, rigI - 1);
    }
}
