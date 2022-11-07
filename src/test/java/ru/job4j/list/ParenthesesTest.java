package ru.job4j.list;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 2.1.4. List
 * 1. Открытые и закрытые скобки
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
class ParenthesesTest {
    @Test
    public void whenValidInner() {
        assertThat(
                Parentheses.valid(new char[]{'(', '(', ')', ')'}))
                .isTrue();
    }

    @Test
    public void whenValidSeq() {
        assertThat(
                Parentheses.valid(new char[]{'(', ')', '(', ')'}))
                .isTrue();
    }

    @Test
    public void whenInValidInner() {
        assertThat(
                Parentheses.valid(new char[]{')', ')', '(', '('}))
                .isFalse();
    }

    @Test
    public void whenInValidSeq() {
        assertThat(
                Parentheses.valid(new char[]{'(', ')', '(', '('}))
                .isFalse();
    }
}