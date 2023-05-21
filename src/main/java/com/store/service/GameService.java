package com.store.service;

import com.store.dto.GameRequestDTO;
import com.store.dto.GameResponseDTO;
import com.store.entity.Game;
import com.store.exception.GameNotFoundException;
import com.store.repository.GameRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class GameService {
    @Inject
    GameRepository gameRepository;

    @Transactional
    public GameResponseDTO createGame(GameRequestDTO gameRequestDTO) {
        Game entity = gameRequestDTO.toEntity();
        gameRepository.persist("game", entity);
        return new GameResponseDTO(entity);
    }

    @Transactional
    public GameResponseDTO findGameByKey(String key){
        Game game = gameRepository.findGameBykey(key);

        if (game == null) {
            throw new GameNotFoundException("Could not find this game in the database");
        }

        return new GameResponseDTO(game);
    }

    @Transactional
    public List<GameResponseDTO> findAllGames(){
        return gameRepository.findAllGames()
                .stream()
                .map(GameResponseDTO::new)
                .toList();
    }
}
