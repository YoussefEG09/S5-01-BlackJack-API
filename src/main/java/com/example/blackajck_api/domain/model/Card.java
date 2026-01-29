package com.example.blackajck_api.domain.model;

import com.example.blackajck_api.domain.model.enums.Rank;
import com.example.blackajck_api.domain.model.enums.Suit;

public class Card {

    private final String suit;
    private final String rank;
    private final int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }
}
