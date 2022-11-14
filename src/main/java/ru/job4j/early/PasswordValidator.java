package ru.job4j.early;

import java.util.HashSet;
import java.util.Set;

/**
 * 1.2.5. Исключения
 * 4. Принципы раннего возврата и охранных выражений [#504875]
 * Задание
 * 1. Создать класс PasswordValidator, который занимается проверкой пароля:
 * 2. Добавить валидацию в метод validate(), применив принцип охранных выражений.
 * Если password null, то выбросить исключение
 * IllegalArgumentException с сообщением "Password can't be null";
 * 3. Учесть требования к паролю ниже.
 * Если хотя бы одно из требований нарушается,
 * то необходимо генерировать исключение
 * IllegalArgumentException с соответствующим сообщением
 * (в скобках будет указано какую строку-сообщение необходимо передавать в конструктор
 * при генерации исключения):
 * 1) Длина пароля находится в диапазоне [8, 32] ("Password should be length [8, 32]");
 * 2) Пароль содержит хотя бы один символ в верхнем регистре
 * ("Password should contain at least one uppercase letter");
 * 3) Пароль содержит хотя бы один символ в нижнем регистре
 * ("Password should contain at least one lowercase letter");
 * 4) Пароль содержит хотя бы одну цифру ("Password should contain at least one figure");
 * 5) Пароль содержит хотя бы один спец. символ (не цифра и не буква)
 * ("Password should contain at least one special symbol");
 * 6) Пароль не содержит подстрок без учета регистра:
 * qwerty, 12345, password, admin, user.
 * Без учета регистра, значит что, например, ни qwerty ни QWERTY ни qwErty и т.п.
 * не должно быть в составе пароля
 * ("Password shouldn't contain substrings: qwerty, 12345, password, admin, user").
 * Для проверок использовать статические методы класса Character.
 * 4. Для проверки реализованного метода используйте следующий класс PasswordValidatorTests:
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.11.2022
 */
public class PasswordValidator {
    private static final int MIN_SZE = 8;
    private static final int MAX_SIZE = 32;
    private static final Set<String> VALID_SUBSTRING = Set.of("qwerty", "12345",
            "password", "admin", "user");

    private enum Validate {
        LOVER, UPPER, DIGIT, SPECIAL,
    }

    public static String validate(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < MIN_SZE || password.length() > MAX_SIZE) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        Set<Validate> validates = isValidSymbol(password);
        if (!validates.contains(Validate.UPPER)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!validates.contains(Validate.LOVER)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!validates.contains(Validate.DIGIT)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!validates.contains(Validate.SPECIAL)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        if (isValidSubstring(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }
        return password;
    }

    private static boolean isValidSubstring(String string) {
        String lowerString = string.toLowerCase();
        return VALID_SUBSTRING.stream()
                .anyMatch(lowerString::contains);
    }

    private static Set<Validate> isValidSymbol(String string) {
        Set<Validate> resultValidate = new HashSet<>();
        for (char c : string.toCharArray()) {
            if (!resultValidate.contains(Validate.UPPER) && Character.isUpperCase(c)) {
                resultValidate.add(Validate.UPPER);
                continue;
            }
            if (!resultValidate.contains(Validate.LOVER) && Character.isLowerCase(c)) {
                resultValidate.add(Validate.LOVER);
                continue;
            }
            if (!resultValidate.contains(Validate.DIGIT) && Character.isDigit(c)) {
                resultValidate.add(Validate.DIGIT);
                continue;
            }
            if (!resultValidate.contains(Validate.SPECIAL) && !Character.isLetterOrDigit(c)) {
                resultValidate.add(Validate.SPECIAL);
                continue;
            }
            if (resultValidate.contains(Validate.UPPER)
                    && resultValidate.contains(Validate.LOVER)
                    && resultValidate.contains(Validate.DIGIT)
                    && resultValidate.contains(Validate.SPECIAL)) {
                break;
            }
        }
        return resultValidate;
    }
}
