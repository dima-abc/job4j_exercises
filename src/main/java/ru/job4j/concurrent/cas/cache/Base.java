package ru.job4j.concurrent.cas.cache;

import java.util.Objects;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.5. Non Blocking Algoritm
 * 1. Неблокирующий кеш [#4741 #271070]
 * Base модель данных с версиями.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.12.2022
 */
public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
