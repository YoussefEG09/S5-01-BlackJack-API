package com.example.blackajck_api.domain.mapper;

import com.example.blackajck_api.domain.model.Card;
import com.example.blackajck_api.domain.model.Game;
import com.example.blackajck_api.web.DTO.GameResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class GameResponseMapper {

    private GameResponseMapper() {
        // Utility class
    }

    public static GameResponseDTO toDto(Game game) {

        GameResponseDTO dto = new GameResponseDTO();

        dto.setId(game.getId());
        dto.setPlayerName(game.getPlayerName());

        dto.setPlayerCards(mapCards(game.getPlayerHand().getCards()));
        dto.setDealerCards(mapCards(game.getDealerHand().getCards()));

        dto.setPlayerScore(game.getPlayerHand().getScore());
        dto.setDealerScore(game.getDealerHand().getScore());

        dto.setFinished(game.isFinished());
        dto.setResult(
                game.getResult() != null
                        ? game.getResult().name()
                        : null
        );

        return dto;
    }

    private static List<String> mapCards(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.toList());
    }
}