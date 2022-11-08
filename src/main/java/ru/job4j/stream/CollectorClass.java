package ru.job4j.stream;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 1.4.2. Stream API
 * 2.0. Понятие редукции. Произвольный Collector для сборки в коллекцию
 * Говоря простыми словами редукция (от англ. reduction - уменьшение) - это сведения,
 * набора элементов к чему-то одному. Например,
 * использование коллекторов предполагает сведение результата чаще всего к коллекции.
 * Но так же есть и другие способы редукции, их мы разберем позже.
 * Рассмотрим пример, создания произвольного коллектора.
 * Собирать будем в связный список. Добавление будет идти всегда в начало (метод collectDeque)
 * Ваша задача прописать создание коллекции и добавление элемента. Коллекцией будет LinkedList, для добавление нужно использовать метод add()
 * Рассмотрим пример, создания произвольного коллектора. Собирать будем в связный список. Добавление будет идти всегда в начало
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.11.2022
 */
public class CollectorClass {
    public static List<Integer> collect(List<Integer> list) {
        Supplier<List<Integer>> supplier = LinkedList::new;
        BiConsumer<List<Integer>, Integer> biConsumer = List::add;
        BinaryOperator<List<Integer>> operator = (left, right) -> {
            left.addAll(right);
            return left;
        };
        return list.stream()
                .collect(
                        Collector.of(
                                supplier,
                                biConsumer,
                                operator
                        ));
    }

    public static Deque<Integer> collectDeque(List<Integer> list) {
        Supplier<Deque<Integer>> supplier = LinkedList::new;
        BiConsumer<Deque<Integer>, Integer> biConsumer = Deque::addFirst;
        BinaryOperator<Deque<Integer>> operator = (left, right) -> {
            right.forEach(left::addFirst);
            return left;
        };

        return list.stream()
                .collect(Collector.of(
                        supplier,
                        biConsumer,
                        operator)
                );
    }
}
