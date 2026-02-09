package com.example.blackajck_api.web.DTO;

import jakarta.validation.constraints.NotBlank;

public class CreateGameRequest {

    @NotBlank
    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
