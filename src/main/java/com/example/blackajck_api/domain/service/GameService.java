package com.example.blackajck_api.domain.service;

import com.example.blackajck_api.domain.model.Game;
import reactor.core.publisher.Mono;

public interface GameService {

    Mono<Game> createGame(String playerName);

    Mono<Game> getGameById(String gameId);

    Mono<Game> hit(String gameId);

    Mono<Game> stand(String gameId);
}