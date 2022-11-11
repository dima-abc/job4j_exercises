package ru.job4j.stream.decks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1.4.2. Stream API
 * 5. Генерирование колоды карт. FlatMap [#504873]
 * Класс содержит метод генерации колоды карт.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.11.2022
 */
public class Decks {
    public List<Card> generateDecks() {
        return Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Value.values())
                        .map(card -> new Card(suit, card)))
                .collect(Collectors.toList());
    }
}
