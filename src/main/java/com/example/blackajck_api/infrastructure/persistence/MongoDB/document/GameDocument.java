package com.example.blackajck_api.infrastructure.persistence.MongoDB.document;


import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

    @Document(collection = "games")
    public class GameDocument {

        @Id
        private String id;

        private String playerName;

        private List<String> playerCards;
        private List<String> dealerCards;

        private boolean finished;
        private String result;

        public GameDocument() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id){
            this.id = id;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public List<String> getPlayerCards() {
            return playerCards;
        }

        public void setPlayerCards(List<String> playerCards) {
            this.playerCards = playerCards;
        }

        public List<String> getDealerCards() {
            return dealerCards;
        }

        public void setDealerCards(List<String> dealerCards) {
            this.dealerCards = dealerCards;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }