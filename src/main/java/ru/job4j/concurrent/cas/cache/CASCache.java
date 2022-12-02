package ru.job4j.concurrent.cas.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.5. Non Blocking Algoritm
 * 1. Неблокирующий кеш [#4741 #271070]
 * CASCache реализация неблокирующего кэш.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
public class CASCache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        return model.equals(memory.computeIfPresent(model.getId(),
                (v, vNew) -> {
                    Base stored = memory.get(model.getId());
                    if (stored.getVersion() != model.getVersion()) {
                        throw new OptimisticException("Version are not equal");
                    }
                    Base result = new Base(model.getId(), model.getVersion() + 1);
                    result.setName(model.getName());
                    return result;
                }));
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public Base get(Integer key) {
        return memory.get(key);
    }
}
