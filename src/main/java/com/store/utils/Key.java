package com.store.utils;

import com.store.entity.Game;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Key {

    private final ValueCommands<String, Game> commands;


    public Key(RedisDataSource redisDataSource) {
        this.commands = redisDataSource.value(Game.class);
    }

    public String generate(String keyPrefix) {
        return keyPrefix+commands.incr("id");
    }
}
