package com.example.blackajck_api.infrastructure.persistence.MongoDB.document;

public class CardDocument {

    private String suit;
    private String rank;
    private int value;

    public CardDocument() {
    }

    public CardDocument(String suit, String rank, int value) {
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
