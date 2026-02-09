package com.example.blackajck_api.domain.model;

import com.example.blackajck_api.domain.model.enums.Rank;
import com.example.blackajck_api.domain.model.enums.Suit;

import java.util.Arrays;

public class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        return rank.getValue();
    }

    public static Card fromString(String value) {
        // Ej: "ACE♠"
        String rankPart = value.substring(0, value.length() - 1);
        String suitSymbol = value.substring(value.length() - 1);

        Rank rank = Rank.valueOf(rankPart);

        Suit suit = Arrays.stream(Suit.values())
                .filter(s -> s.getSymbol().equals(suitSymbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid suit"));

        return new Card(suit, rank);
    }

    @Override
    public String toString() {
        return rank.name() + suit.getSymbol();
    }
}
