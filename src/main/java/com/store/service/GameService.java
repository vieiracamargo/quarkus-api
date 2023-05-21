package com.store.service;

import com.store.dto.GameRequestDTO;
import com.store.dto.GameResponseDTO;
import com.store.entity.Game;
import com.store.repository.GameRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GameService {
    @Inject
    GameRepository gameRepository;

    @Transactional
    public GameResponseDTO createGame(GameRequestDTO gameRequestDTO){
        Game entity = gameRequestDTO.toEntity();
        gameRepository.persist("game", entity);
        return new GameResponseDTO(entity);
    }
}
