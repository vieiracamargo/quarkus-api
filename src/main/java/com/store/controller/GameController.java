package com.store.controller;

import com.store.dto.GameRequestDTO;
import com.store.dto.GameResponseDTO;
import com.store.service.GameService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@ApplicationScoped
@Path("api/v1/game")
public class GameController {

    @Inject
    GameService gameService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame(@Valid GameRequestDTO gameRequestDTO, @Context UriInfo uriInfo) {
        GameResponseDTO game = gameService.createGame(gameRequestDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(game.key()).build();
        return Response.created(uri).entity(game).build();
    }

    @GET
    @Path("{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findGameByKey(@PathParam("key") String key){
        return Response.ok().entity(gameService.findGameByKey(key)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllGames(){
        return Response.ok().entity(gameService.findAllGames()).build();
    }

    @PUT
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame(@PathParam("key") String key, @Valid GameRequestDTO gameRequestDTO) {
        GameResponseDTO game = gameService.updateGame(key,gameRequestDTO);
        return Response.ok().entity(game).build();
    }
}
