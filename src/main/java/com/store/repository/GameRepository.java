package com.store.repository;

import com.store.entity.Game;

public interface GameRepository {

    void persist(String keyPrefix, Game game);

    Game findGameBykey(String key);

    String generateKey(String keyPrefix);
}
