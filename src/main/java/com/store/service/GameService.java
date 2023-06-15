package com.store.service;

import com.store.dto.GameRequestDTO;
import com.store.dto.GameResponseDTO;
import com.store.entity.Game;
import com.store.exception.GameNotFoundException;
import com.store.repository.GameRepository;
import com.store.utils.Key;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class GameService {

    private static final Logger LOGGER = Logger.getLogger(GameService.class);
    @Inject
    GameRepository gameRepository;

    @Inject
    Key key;

    @Transactional
    public GameResponseDTO createGame(GameRequestDTO gameRequestDTO) {
        LOGGER.info("Saving new game");
        Game entity = gameRequestDTO.toEntity();
        gameRepository.persist(key.generate("game"), entity);
        LOGGER.info("Game has been created with key" + entity.getKey());
        return new GameResponseDTO(entity);
    }

    @Transactional
    public GameResponseDTO findGameByKey(String key){
        Game game = gameRepository.findGameBykey(key);

        if (game == null) {
            LOGGER.error("Could not find game with key" + key + " in the database");
            throw new GameNotFoundException("Could not find this game in the database");
        }

        LOGGER.info("Get the game with the key" + key);
        return new GameResponseDTO(game);
    }

    @Transactional
    public List<GameResponseDTO> findAllGames(){
        LOGGER.info("Get all games inside the database");
        return gameRepository.findAllGames()
                .stream()
                .map(GameResponseDTO::new)
                .toList();
    }
    @Transactional
    public GameResponseDTO updateGame(String key, GameRequestDTO gameRequestDTO){
        LOGGER.info("Updating game data");
        findGameByKey(key);
        Game entity = gameRequestDTO.toEntity();
        gameRepository.persist(key, entity);
        LOGGER.info("Game with key" + key + "successfully updated");
        return new GameResponseDTO(entity);
    }

    @Transactional
    public void deleteGame(String key){
        findGameByKey(key);
        gameRepository.deleteGame(key);
        LOGGER.info("Game with key " + key + " successfully deleted");
    }
}
