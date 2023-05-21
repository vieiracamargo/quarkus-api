package com.store.dto;

import com.store.entity.Game;

import java.time.LocalDate;

public record GameResponseDTO(
        String key,
        String title,

        String description,

        LocalDate releaseDate,

        String trailerUrl
) {
    public GameResponseDTO(Game game){
        this(
                game.getKey(),
                game.getTitle(),
                game.getDescription(),
                game.getReleaseDate(),
                game.getTrailerUrl()
        );
    }

    public Game toEntity(){
        return new Game(
                null,
                this.title(),
                this.description(),
                this.releaseDate(),
                this.trailerUrl()
        );
    }
}
