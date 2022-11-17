package ru.job4j.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 2. Джуниор
 * 2.4. Garbage Collection
 * 2.4.4. Типы ссылок и коллекции на soft weak ссылках
 * 1. Реализация кеша на SoftReference [#1592 #256228]
 * Описание задачи:
 * пт. 1. Создать структуру данных типа кеш.
 * Кеш должен быть абстрактный.
 * То есть необходимо, чтобы можно было задать ключ получения объекта кеша
 * и в случае если его нет в памяти,
 * задать поведение загрузки этого объекта в кеш.
 * Для это выделим класс:
 * абстрактный класс для управления кешам AbstractCache.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.11.2022
 */
public abstract class AbstractCache<K, V> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCache.class.getSimpleName());
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        this.cache.putIfAbsent(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V result = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (result == null) {
            LOG.info("File is not CACHE: {}", key);
            result = load(key);
            put(key, result);
        }
        return result;
    }

    protected abstract V load(K key);
}
