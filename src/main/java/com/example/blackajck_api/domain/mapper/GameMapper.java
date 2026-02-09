package com.example.blackajck_api.domain.mapper;

import com.example.blackajck_api.domain.model.Card;
import com.example.blackajck_api.domain.model.Game;
import com.example.blackajck_api.domain.model.enums.GameResult;
import com.example.blackajck_api.infrastructure.persistence.MongoDB.document.GameDocument;

import java.util.stream.Collectors;

public class GameMapper {

    private GameMapper() {}

    public static GameDocument toDocument(Game game) {
        GameDocument doc = new GameDocument();
        doc.setId(game.getId());
        doc.setPlayerName(game.getPlayerName());
        doc.setPlayerCards(
                game.getPlayerHand().getCards()
                        .stream()
                        .map(Card::toString)
                        .collect(Collectors.toList())
        );
        doc.setDealerCards(
                game.getDealerHand().getCards()
                        .stream()
                        .map(Card::toString)
                        .collect(Collectors.toList())
        );
        doc.setFinished(game.isFinished());
        doc.setResult(game.getResult() != null ? game.getResult().name() : null);
        return doc;
    }

    public static Game toDomain(GameDocument doc) {
        Game game = new Game(doc.getPlayerName());

        doc.getPlayerCards()
                .stream()
                .map(Card::fromString)
                .forEach(game.getPlayerHand()::addCard);

        doc.getDealerCards()
                .stream()
                .map(Card::fromString)
                .forEach(game.getDealerHand()::addCard);

        if (doc.getResult() != null) {
            game.finish(GameResult.valueOf(doc.getResult()));
        }

        return game;
    }
}

