package com.example.blackajck_api.infrastructure.persistence.MySQL;


import com.example.blackajck_api.infrastructure.persistence.MySQL.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    boolean existsByName(String name);
}
