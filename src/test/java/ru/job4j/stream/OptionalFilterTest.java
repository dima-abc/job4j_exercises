package ru.job4j.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.stream.OptionalFilter.*;

/**
 * 1.4.2. Stream API
 * 7.2. Optional и Stream. Метод filter()
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 10.11.2022
 */
class OptionalFilterTest {
    @Test
    public void notExistAndNeedChild() {
        Child c1 = new Child("c1", 15);
        Child c2 = new Child("c2", 18);
        Child c3 = new Child("c1", 11);
        Child c4 = new Child("c2", 15);
        Worker worker1 = new Worker("123", List.of(c1, c2));
        Worker worker2 = new Worker("456", List.of(c3, c4));
        List<Child> result = OptionalFilter.defineChildren(List.of(worker1, worker2), "123");
        List<Child> expected = List.of();
        assertThat(result)
                .isEqualTo(expected);
    }

    @Test
    public void notExistAndChildrenOld() {
        Child c1 = new Child("c1", 15);
        Child c2 = new Child("c2", 18);
        Child c3 = new Child("c1", 20);
        Child c4 = new Child("c2", 15);
        Worker worker1 = new Worker("123", List.of(c1, c2, c3));
        Worker worker2 = new Worker("456", List.of(c4));
        List<Child> result = OptionalFilter.defineChildren(List.of(worker1, worker2), "123");
        List<Child> expected = List.of();
        assertThat(result)
                .isEqualTo(expected);
    }

    @Test
    public void whenExists() {
        Child c1 = new Child("c1", 15);
        Child c2 = new Child("c2", 13);
        Child c3 = new Child("c1", 10);
        Child c4 = new Child("c2", 15);
        Worker worker1 = new Worker("123", List.of(c1, c2, c3));
        Worker worker2 = new Worker("456", List.of(c4));
        int result = OptionalFilter.defineChildren(List.of(worker1, worker2), "123").size();
        int expected = 2;
        assertThat(result)
                .isEqualTo(expected);
    }

}