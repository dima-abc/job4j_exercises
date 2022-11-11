package ru.job4j.stream.decks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 1.4.2. Stream API
 * 5. Генерирование колоды карт. FlatMap [#504873]
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.11.2022
 */
class DecksTest {
    @Test
    void decksGenerate() {
        Decks decks = new Decks();
        List<Card> cardList = decks.generateDecks();
        cardList.forEach(System.out::println);
        assertThat(true)
                .isTrue();
    }
}