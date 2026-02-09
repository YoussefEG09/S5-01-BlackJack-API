package com.example.blackajck_api.application.service;

import com.example.blackajck_api.domain.mapper.GameMapper;
import com.example.blackajck_api.domain.model.Deck;
import com.example.blackajck_api.domain.model.Game;
import com.example.blackajck_api.domain.model.Player;
import com.example.blackajck_api.domain.model.enums.GameResult;
import com.example.blackajck_api.domain.service.GameService;
import com.example.blackajck_api.infrastructure.persistence.MongoDB.GameRepository;
import com.example.blackajck_api.infrastructure.persistence.MongoDB.document.GameDocument;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Mono<Game> createGame(String playerName) {
        Deck deck = new Deck();
        Game game = new Game(playerName);

        game.getPlayerHand().addCard(deck.draw());
        game.getPlayerHand().addCard(deck.draw());
        game.getDealerHand().addCard(deck.draw());

        GameDocument doc = GameMapper.toDocument(game);

        return gameRepository.save(doc)
                .map(GameMapper::toDomain);
    }

    @Override
    public Mono<Game> hit(String gameId) {
        return gameRepository.findById(gameId)
                .map(GameMapper::toDomain)
                .map(game -> {
                    Deck deck = new Deck();
                    game.getPlayerHand().addCard(deck.draw());
                    return game;
                })
                .flatMap(game ->
                        gameRepository.save(GameMapper.toDocument(game))
                                .thenReturn(game)
                );
    }

    @Override
    public Mono<Game> stand(String gameId) {
        return gameRepository.findById(gameId)
                .map(GameMapper::toDomain)
                .map(game -> {
                    Deck deck = new Deck();
                    while (game.getDealerHand().getScore() < 17) {
                        game.getDealerHand().addCard(deck.draw());
                    }
                    resolveGame(game);
                    return game;
                })
                .flatMap(game ->
                        gameRepository.save(GameMapper.toDocument(game))
                                .thenReturn(game)
                );
    }

    private void resolveGame(Game game) {
        int playerScore = game.getPlayerHand().getScore();
        int dealerScore = game.getDealerHand().getScore();

        if (game.getDealerHand().isBusted()) {
            game.finish(GameResult.PLAYER_WINS);
        } else if (playerScore > dealerScore) {
            game.finish(GameResult.PLAYER_WINS);
        } else if (playerScore < dealerScore) {
            game.finish(GameResult.DEALER_WINS);
        } else {
            game.finish(GameResult.PUSH);
        }
    }
}
