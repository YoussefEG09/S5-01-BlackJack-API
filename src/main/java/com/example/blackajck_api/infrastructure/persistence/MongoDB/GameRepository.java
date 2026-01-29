package com.example.blackajck_api.infrastructure.persistence.MongoDB;

import com.example.blackajck_api.infrastructure.persistence.MongoDB.document.GameDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepository extends ReactiveMongoRepository<GameDocument, String> {
}
