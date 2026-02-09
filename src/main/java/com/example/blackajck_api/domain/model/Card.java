package com.example.blackajck_api.domain.model;

import com.example.blackajck_api.domain.model.enums.Rank;
import com.example.blackajck_api.domain.model.enums.Suit;

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

    @Override
    public String toString() {
        return rank.name() + suit.getSymbol();
    }
}
