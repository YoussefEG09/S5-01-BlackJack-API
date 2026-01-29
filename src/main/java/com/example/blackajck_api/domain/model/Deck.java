package com.example.blackajck_api.domain.model;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards = new Stack<>();

    public Deck() {
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                int value = switch (rank) {
                    case "J", "Q", "K" -> 10;
                    case "A" -> 11;
                    default -> Integer.parseInt(rank);
                };
                cards.add(new Card(suit, rank, value));
            }
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.pop();
    }
}
