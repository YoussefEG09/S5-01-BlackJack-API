package com.example.blackajck_api.domain.model;

import java.util.UUID;

public class Game {

    private final String id;
    private final Player player;
    private final Hand playerHand;
    private final Hand dealerHand;
    private boolean finished;

    public Game(Player player) {
        this.id = UUID.randomUUID().toString();
        this.player = player;
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.finished = false;
    }

    public String getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
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

    public void finishGame() {
        this.finished = true;
    }
}
