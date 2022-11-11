package ru.job4j.inheritance;

/**
 * 1.2.2. Наследование
 * 4. Переопределение метода
 * пример из
 * https://docs.oracle.com/javase/tutorial/java/IandI/override.html
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.11.2022
 */
public class Cat extends Animal {

    public static void testClassMethod() {
        System.out.println("Статический метод в Cat");
    }

    @Override
    public void testInstanceMethod() {
        System.out.println("Метод экземпляра в Cat");
    }
}
