package com.example.blackajck_api.infrastructure.persistence.MongoDB.document;


import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
public class GameDocument {

    @Id
    private String id;

    private String playerId;
    private String playerName;

    private List<CardDocument> playerHand;
    private List<CardDocument> dealerHand;

    private boolean finished;

    public GameDocument() {
    }

    public GameDocument(String playerId, String playerName, List<CardDocument> playerHand,
                        List<CardDocument> dealerHand, boolean finished) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.finished = finished;
    }

    public String getId() {
        return id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<CardDocument> getPlayerHand() {
        return playerHand;
    }

    public List<CardDocument> getDealerHand() {
        return dealerHand;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}