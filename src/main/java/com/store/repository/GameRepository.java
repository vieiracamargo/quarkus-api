package com.store.repository;

import com.store.entity.Game;

import java.util.List;

public interface GameRepository {

    void persist(String keyPrefix, Game game);

    List<Game> findAllGames();

    Game findGameBykey(String key);

    String generateKey(String keyPrefix);
}
