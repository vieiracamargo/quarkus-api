package com.store.repository;

import com.store.entity.Game;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.KeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RedisGamesGameRepository implements GameRepository {
    private final KeyCommands<String> keyCommands;
    private final ValueCommands<String, Game> commands;

    public RedisGamesGameRepository(RedisDataSource redisDataSource) {
        this.commands = redisDataSource.value(Game.class);
        this.keyCommands = redisDataSource.key(String.class);
    }
    public void persist(String key, Game game) {
        game.setKey(key);
        commands.set(key, game);
    }
    public Game findGameBykey(String key){
        return commands.get(key);
    }

    public List<Game> findAllGames(){
        List<String> keys = keyCommands.keys("game*");
        return keys.stream().map(commands::get).toList();
    }

    public void deleteGame(String key){
        commands.getdel(key);
    }

}
