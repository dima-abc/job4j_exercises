package ru.job4j.inheritance;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 1.2.2. Наследование
 * 4. Переопределение метода
 * пример из
 * https://docs.oracle.com/javase/tutorial/java/IandI/override.html
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.11.2022
 */
class CatTest {
    @Test
    void catMethodTestOverride() {
        Cat myCat = new Cat();
        Animal myAnimal = myCat;
        Animal.testClassMethod();
        Cat.testClassMethod();
        myAnimal.testInstanceMethod();
        myCat.testInstanceMethod();
        assertThat(true)
                .isTrue();
    }
}