package com.example.blackajck_api.web.exception;

import com.example.blackajck_api.application.exception.GameAlreadyFinishedException;
import com.example.blackajck_api.application.exception.GameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGameNotFound(
            GameNotFoundException ex
    ) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "timestamp", LocalDateTime.now(),
                                "error", "GAME_NOT_FOUND",
                                "message", ex.getMessage()
                        ))
        );
    }

    @ExceptionHandler(GameAlreadyFinishedException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGameFinished(
            GameAlreadyFinishedException ex
    ) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of(
                                "timestamp", LocalDateTime.now(),
                                "error", "GAME_ALREADY_FINISHED",
                                "message", ex.getMessage()
                        ))
        );
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGeneric(
            Exception ex
    ) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of(
                                "timestamp", LocalDateTime.now(),
                                "error", "INTERNAL_ERROR",
                                "message", ex.getMessage()
                        ))
        );
    }
}
