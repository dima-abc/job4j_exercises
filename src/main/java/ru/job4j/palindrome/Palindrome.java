package ru.job4j.palindrome;

/**
 * Является ли строка палиндромом.
 * Все пробелы и знаки препинания игнорируются.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 24.11.2022
 */
public class Palindrome {
    public boolean isPalindrome(String s) {
        String temp = s.replaceAll("\\W+", "").toLowerCase();
        String revers = new StringBuilder(temp).reverse().toString();
        return temp.equals(revers);
    }
}
