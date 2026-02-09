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
    private boolean finished;
    private GameResult result;

    public Game(String playerName) {
        this.id = UUID.randomUUID().toString();
        this.playerName = playerName;
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.finished = false;
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
