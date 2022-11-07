package ru.job4j.list;

/**
 * 2.1.4. List
 * 1. Открытые и закрытые скобки
 * Реализовать метод проверки корректности открытых и закрытых скобок.
 * Например, ()(()((()))) - true, ()) - false
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
public class Parentheses {
    public static boolean valid(char[] data) {
        int step = 0;
        for (char datum : data) {
            if (datum == '(') {
                step++;
            }
            if (datum == ')') {
                step--;
            }
            if (step < 0) {
                return false;
            }
        }
        return step == 0;
    }
}
