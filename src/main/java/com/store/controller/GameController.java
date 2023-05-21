package com.store.controller;

import com.store.dto.GameRequestDTO;
import com.store.dto.GameResponseDTO;
import com.store.service.GameService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@ApplicationScoped
@Path("api/v1/game")
public class GameController {

    @Inject
    GameService gameService;

    @POST
    public Response createGame(@Valid GameRequestDTO gameRequestDTO, @Context UriInfo uriInfo) {
        GameResponseDTO game = gameService.createGame(gameRequestDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(game.key()).build();
        return Response.created(uri).entity(game).build();
    }

}
