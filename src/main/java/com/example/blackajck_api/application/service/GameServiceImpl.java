package com.example.blackajck_api.application.service;

import com.example.blackajck_api.domain.mapper.GameMapper;
import com.example.blackajck_api.domain.model.Deck;
import com.example.blackajck_api.domain.model.Game;
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

        Game game = new Game(playerName);

        GameDocument document = GameMapper.toDocument(game);

        return gameRepository.save(document)
                .map(GameMapper::toDomain);
    }

    @Override
    public Mono<Game> getGameById(String gameId) {

        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException("Game not found")
                ))
                .map(GameMapper::toDomain);
    }

    @Override
    public Mono<Game> hit(String gameId) {

        return getGameById(gameId)
                .flatMap(game -> {

                    game.hit();

                    GameDocument updatedDoc =
                            GameMapper.toDocument(game);

                    return gameRepository.save(updatedDoc)
                            .map(GameMapper::toDomain);
                });
    }

    @Override
    public Mono<Game> stand(String gameId) {

        return getGameById(gameId)
                .flatMap(game -> {

                    game.stand();

                    GameDocument updatedDoc =
                            GameMapper.toDocument(game);

                    return gameRepository.save(updatedDoc)
                            .map(GameMapper::toDomain);
                });
    }

}
