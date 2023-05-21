package com.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game{
    private String key;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String trailerUrl;

}
