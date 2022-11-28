package ru.job4j.palindrome;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Является ли строка палиндромом.
 * Все пробелы и знаки препинания игнорируются.
 * TEST.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 24.11.2022
 */
class PalindromeTest {
    @Test
    void whenOneTrue() {
        String s = "Кони, топот, инок,";
        Palindrome palindrome = new Palindrome();
        boolean result = palindrome.isPalindrome(s);
        assertThat(result).isTrue();
    }

    @Test
    void wneTwoFalse() {
        String s = "Palindrome, , ,";
        Palindrome palindrome = new Palindrome();
        boolean result = palindrome.isPalindrome(s);
        assertThat(result).isFalse();
    }

    @Test
    void whenThreeTrue() {
        String s = "Колесо. Жалко поклаж. Оселок.";
        Palindrome palindrome = new Palindrome();
        boolean result = palindrome.isPalindrome(s);
        assertThat(result).isTrue();
    }

    @Test
    void whenForeTrue() {
        String s = "И лежу. - Ужели?";
        Palindrome palindrome = new Palindrome();
        boolean result = palindrome.isPalindrome(s);
        assertThat(result).isTrue();
    }

    @Test
    void whenMadamImAdam() {
        String s ="Madam i’m Adam";
        Palindrome palindrome = new Palindrome();
        boolean result = palindrome.isPalindrome(s);
        assertThat(result)
                .isTrue();
    }

    @Test
    void whenOlsonInOslo() {
        String s ="Olso1n' '.  In 1Oslo";
        Palindrome palindrome = new Palindrome();
        boolean result = palindrome.isPalindrome(s);
        assertThat(result)
                .isTrue();
    }

    @Test
    void when123454321() {
        String s ="123454321";
        Palindrome palindrome = new Palindrome();
        boolean result = palindrome.isPalindrome(s);
        assertThat(result)
                .isTrue();
    }

    @Test
    void whenMadamimAdam() {
        String s ="madam i’m Adam";
        Palindrome palindrome = new Palindrome();
        boolean result = palindrome.isPalindrome(s);
        assertThat(result)
                .isTrue();
    }
}