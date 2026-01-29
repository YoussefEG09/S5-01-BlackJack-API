package com.example.blackajck_api.domain.service;

import reactor.core.publisher.Mono;

public interface GameService {

    Mono<String> createGame(String playerName);

    Mono<Void> hit(String gameId);

    Mono<Void> stand(String gameId);
}