package com.webflux.webfluxapi;

import com.webflux.webfluxapi.document.Playlist;
import com.webflux.webfluxapi.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;


@Component
public class PlaylistHandler {

    @Autowired
    PlaylistService playlistService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playlistService.findAll(), Playlist.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playlistService.findById(id), Playlist.class);
    }

    public Mono<ServerResponse> save(ServerRequest request){
        final Mono<Playlist> playlistMono = request.bodyToMono(Playlist.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(playlistMono.flatMap(playlistService::save), Playlist.class));
    }
}
