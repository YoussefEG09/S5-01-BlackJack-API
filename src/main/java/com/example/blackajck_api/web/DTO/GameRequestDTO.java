package com.example.blackajck_api.web.DTO;

import jakarta.validation.constraints.NotBlank;

public class GameRequestDTO {

    @NotBlank(message = "Player name is mandatory")
    private String playerName;

    public GameRequestDTO() {
    }

    public GameRequestDTO(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
