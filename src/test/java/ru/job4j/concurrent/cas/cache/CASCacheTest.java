package ru.job4j.concurrent.cas.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.5. Non Blocking Algoritm
 * 1. Неблокирующий кеш [#4741 #271070]
 * CASCache реализация неблокирующего кэш.
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
class CASCacheTest {
    private CASCache cache;

    @BeforeEach
    public void init() {
        cache = new CASCache();
    }

    @Test
    public void whenAddBase1AddBase2ThenTrue() {
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        assertThat(cache.add(base2))
                .isTrue();
    }

    @Test
    public void whenAddBaseTrueAddBaseThenFalse() {
        Base base = new Base(1, 1);
        cache.add(base);
        assertThat(cache.add(base))
                .isFalse();
    }

    @Test
    public void whenAddBaseGetKeyThenBase() {
        Base base = new Base(1, 1);
        cache.add(base);
        assertThat(base)
                .isEqualTo(cache.get(base.getId()));
    }

    @Test
    public void whenUpdateBaseThenTrue() {
        Base base = new Base(1, 1);
        base.setName("one");
        cache.add(base);
        base.setName("versionTwo");
        assertThat(cache.update(base))
                .isTrue();
    }

    @Test
    public void whenUpdateBaseThenFalse() {
        Base base = new Base(1, 1);
        base.setName("one");
        assertThat(cache.update(base))
                .isFalse();
    }

    @Test
    public void whenUpdateBaseVersion1GetBaseVersion2() {
        Base base = new Base(1, 1);
        base.setName("one");
        cache.add(base);
        base.setName("versionTwo");
        cache.update(base);
        int expected = base.getVersion() + 1;
        assertThat(expected)
                .isEqualTo(cache.get(1).getVersion());
    }

    @Test
    public void whenBaseVersion1UpdateBaseVersion2ThenException() {
        Base base = new Base(1, 1);
        base.setName("version1");
        Base base1 = new Base(1, 2);
        base1.setName("version2");
        cache.add(base);
        assertThatThrownBy(() -> cache.update(base1))
                .isInstanceOf(OptimisticException.class)
                .hasMessageContaining("Version are not equal");
    }

    @Test
    public void whenAddBaseDeleteGetKeyThenNull() {
        Base base = new Base(1, 1);
        cache.add(base);
        cache.delete(base);
        assertThat(cache.get(base.getId()))
                .isNull();
    }

}