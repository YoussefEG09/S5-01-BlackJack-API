package com.example.blackajck_api.web.controller;

import com.example.blackajck_api.domain.mapper.GameResponseMapper;
import com.example.blackajck_api.domain.model.Game;
import com.example.blackajck_api.domain.service.GameService;
import com.example.blackajck_api.web.DTO.CreateGameRequest;
import com.example.blackajck_api.web.DTO.GameRequestDTO;
import com.example.blackajck_api.web.DTO.GameResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public Mono<ResponseEntity<GameResponseDTO>> createGame(
            @RequestBody @Valid GameRequestDTO request
    ) {
        return gameService.createGame(request.getPlayerName())
                .map(GameResponseMapper::toDto)
                .map(dto -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(dto)
                );
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<GameResponseDTO>> getGameById(
            @PathVariable String id
    ) {
        return gameService.getGameById(id)
                .map(GameResponseMapper::toDto)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/{id}/hit")
    public Mono<ResponseEntity<GameResponseDTO>> hit(
            @PathVariable String id
    ) {
        return gameService.hit(id)
                .map(GameResponseMapper::toDto)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/{id}/stand")
    public Mono<ResponseEntity<GameResponseDTO>> stand(
            @PathVariable String id
    ) {
        return gameService.stand(id)
                .map(GameResponseMapper::toDto)
                .map(ResponseEntity::ok);
    }
}
