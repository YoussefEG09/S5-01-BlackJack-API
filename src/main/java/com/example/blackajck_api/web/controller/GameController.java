package com.example.blackajck_api.web.controller;

import com.example.blackajck_api.domain.model.Game;
import com.example.blackajck_api.domain.service.GameService;
import com.example.blackajck_api.web.DTO.CreateGameRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public Mono<ResponseEntity<Game>> createGame(
            @RequestBody @Valid CreateGameRequest request
    ) {
        return gameService.createGame(request.getPlayerName())
                .map(game ->
                        ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(game)
                );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Game>> getGameById(@PathVariable String id) {
        return gameService.getGameById(id)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/{id}/hit")
    public Mono<ResponseEntity<Game>> hit(@PathVariable String id) {
        return gameService.hit(id)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/{id}/stand")
    public Mono<ResponseEntity<Game>> stand(@PathVariable String id) {
        return gameService.stand(id)
                .map(ResponseEntity::ok);
    }
}
