package com.example.blackajck_api.domain.model;

import com.example.blackajck_api.domain.model.enums.GameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    private final String id;
    private final String playerName;
    private final Hand playerHand;
    private final Hand dealerHand;
    private final Deck deck;
    private boolean finished;
    private GameResult result;

    public Game(String playerName) {
        this.id = UUID.randomUUID().toString();
        this.playerName = playerName;
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.deck = new Deck();
        this.finished = false;

        playerHand.addCard(deck.draw());
        dealerHand.addCard(deck.draw());
        playerHand.addCard(deck.draw());
        dealerHand.addCard(deck.draw());

        checkInitialBlackjack();
    }

    public void hit() {
        if (finished) {
            throw new IllegalStateException("Game already finished");
        }

        playerHand.addCard(deck.draw());

        if (playerHand.isBusted()) {
            finish(GameResult.PLAYER_BUST);
        }
    }


    public void stand() {
        if (finished) {
            throw new IllegalStateException("Game already finished");
        }

        while (dealerHand.getScore() < 17) {
            dealerHand.addCard(deck.draw());
        }

        if (dealerHand.isBusted()) {
            finish(GameResult.DEALER_BUST);
            return;
        }

        resolveWinner();
    }


    private void checkInitialBlackjack() {
        if (playerHand.isBlackJack() && dealerHand.isBlackJack()) {
            finish(GameResult.PUSH);
        } else if (playerHand.isBlackJack()) {
            finish(GameResult.PLAYER_BLACKJACK);
        } else if (dealerHand.isBlackJack()) {
            finish(GameResult.DEALER_BLACKJACK);
        }
    }

    private void resolveWinner() {
        int playerScore = playerHand.getScore();
        int dealerScore = dealerHand.getScore();

        if (playerScore > dealerScore) {
            finish(GameResult.PLAYER_WIN);
        } else if (dealerScore > playerScore) {
            finish(GameResult.DEALER_WIN);
        } else {
            finish(GameResult.PUSH);
        }
    }

    public String getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public boolean isFinished() {
        return finished;
    }

    public GameResult getResult() {
        return result;
    }


    public void finish(GameResult result) {
        this.finished = true;
        this.result = result;
    }
}
