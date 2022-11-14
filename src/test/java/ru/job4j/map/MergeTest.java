package ru.job4j.map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.map.Merge.*;

/**
 * 1.3.3. Map. HashMap. LinkedHashMap
 * 8. Объединение значений в Map.
 * TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.11.2022
 */
class MergeTest {
    @Test
    public void collectData() {
        Map<Integer, String> names = new HashMap<>(Map.of(1, "name1", 2, "name2"));
        List<User> users = List.of(
                new User(1, "name1", "surname1"),
                new User(2, "name2", "surname2"),
                new User(3, "name3", "surname3"),
                new User(4, "name4", "surname4")
        );
        Map<Integer, String> rsl = Merge.collectData(names, users);
        Map<Integer, String> expected = Map.of(
                1, "name1 surname1",
                2, "name2 surname2",
                3, "name3 surname3",
                4, "name4 surname4"
        );
        assertThat(rsl)
                .isEqualTo(expected);
    }
}