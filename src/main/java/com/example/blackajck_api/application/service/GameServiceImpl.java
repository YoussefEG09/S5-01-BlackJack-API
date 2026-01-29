package com.example.blackajck_api.application.service;

import com.example.blackajck_api.domain.model.Deck;
import com.example.blackajck_api.domain.model.Game;
import com.example.blackajck_api.domain.model.Player;
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
    public Mono<String> createGame(String playerName) {
        Deck deck = new Deck();
        Player player = new Player(playerName);
        Game game = new Game(player);

        game.getPlayerHand().addCard(deck.draw());
        game.getPlayerHand().addCard(deck.draw());
        game.getDealerHand().addCard(deck.draw());

        GameDocument document = GameMapper.toDocument(game);
        return gameRepository.save(document)
                .map(GameDocument::getId);
    }

    @Override
    public Mono<Void> hit(String gameId) {
        return gameRepository.findById(gameId)
                .flatMap(doc -> {
                    Deck deck = new Deck();
                    doc.getPlayerHand().add(GameMapper.toCardDocument(deck.draw()));
                    return gameRepository.save(doc);
                })
                .then();
    }

    @Override
    public Mono<Void> stand(String gameId) {
        return gameRepository.findById(gameId)
                .flatMap(doc -> {
                    Deck deck = new Deck();
                    while (GameMapper.dealerScore(doc) < 17) {
                        doc.getDealerHand().add(GameMapper.toCardDocument(deck.draw()));
                    }
                    doc.setFinished(true);
                    return gameRepository.save(doc);
                })
                .then();
    }
}
