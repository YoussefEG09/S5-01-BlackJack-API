package com.example.blackajck_api.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        int score = cards.stream().mapToInt(Card::getValue).sum();
        long aces = cards.stream().filter(c -> c.getValue() == 11).count();

        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }

        return score;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && getScore() == 21;
    }

    public boolean isBusted() {
        return getScore() > 21;
    }
}
