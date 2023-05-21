package com.store.repository;

import com.store.entity.Game;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RedisGamesGameRepository implements GameRepository {

    private final ValueCommands<String, Game> commands;

    public RedisGamesGameRepository(RedisDataSource redisDataSource) {
        this.commands = redisDataSource.value(Game.class);
    }
    public void persist(String keyPrefix, Game game) {
        String key = generateKey(keyPrefix);
        game.setKey(key);
        commands.set(key, game);
    }
    public Game findGameBykey(String key){
        return commands.get(key);
    }

    public String generateKey(String keyPrefix) {
        return keyPrefix+commands.incr("id");
    }
}
