package com.store.controller;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(GameController.class)
class GameControllerTest {

    @Test
    @Order(1)
    void createGame() {
        Map<String,String> game = new HashMap<>();
        game.put("title", "God of War");
        game.put("description", "Resumo da história de God of War (2018) Depois de se vingar dos Deuses Gregos, Kratos migrou para o reino de Midgard. Vários anos se passaram e o deus da guerra se casou com Faye, uma guerreira Jötunn, com quem teve um filho chamado Atreus.");
        game.put("releaseDate", "2018-04-20");
        game.put("trailerUrl", "https://www.youtube.com/watch?v=FyIwEFXOcaE");

        given()
                .contentType("application/json")
                .body(game)
                .when().post()
                .then()
                .statusCode(201)
                .body(
                        "title", equalTo("God of War"),
                        "description", equalTo("Resumo da história de God of War (2018) Depois de se vingar dos Deuses Gregos, Kratos migrou para o reino de Midgard. Vários anos se passaram e o deus da guerra se casou com Faye, uma guerreira Jötunn, com quem teve um filho chamado Atreus."),
                        "releaseDate", equalTo("2018-04-20"),
                        "trailerUrl", equalTo("https://www.youtube.com/watch?v=FyIwEFXOcaE")
                );
    }

    @Test
    @Order(2)
    void findGameByKey() {
        given()
                .pathParam("key", "game1")
                .when().get("{key}")
                .then()
                .statusCode(200)
                .body("key", equalTo("game1"),
                        "title", equalTo("God of War"),
                        "description", equalTo("Resumo da história de God of War (2018) Depois de se vingar dos Deuses Gregos, Kratos migrou para o reino de Midgard. Vários anos se passaram e o deus da guerra se casou com Faye, uma guerreira Jötunn, com quem teve um filho chamado Atreus."),
                        "releaseDate", equalTo("2018-04-20"),
                        "trailerUrl", equalTo("https://www.youtube.com/watch?v=FyIwEFXOcaE")
                );
    }

    @Test
    @Order(3)
    void getAllGames() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", greaterThan(0));
    }

    @Test
    @Order(4)
    void updateGame() {
        Map<String,String> game = new HashMap<>();
        game.put("title", "Elden Ring");
        game.put("description", "Em Elden Ring, o jogador incorpora um Maculado, guerreiro guiado pela força da Graça para portar o poder do Anel Prístino e torna-se um Lorde Prístino. A trama tem como cenário as Terras Intermédias, um local governado pela Rainha Marika.");
        game.put("releaseDate", "2022-02-25");
        game.put("trailerUrl", "https://www.youtube.com/watch?v=OT8if6DXOFQ");

        given()
                .contentType("application/json")
                .pathParam("key","game1")
                .body(game)
                .when().put("{key}")
                .then()
                .statusCode(200)
                .body(
                        "title", equalTo("Elden Ring"),
                        "description", equalTo("Em Elden Ring, o jogador incorpora um Maculado, guerreiro guiado pela força da Graça para portar o poder do Anel Prístino e torna-se um Lorde Prístino. A trama tem como cenário as Terras Intermédias, um local governado pela Rainha Marika."),
                        "releaseDate", equalTo("2022-02-25"),
                        "trailerUrl", equalTo("https://www.youtube.com/watch?v=OT8if6DXOFQ")
                );
    }

    @Test
    @Order(5)
    void deleGameById() {
        given()
                .pathParam("key", "game1")
                .when().delete("{id}")
                .then()
                .statusCode(204);
    }

}