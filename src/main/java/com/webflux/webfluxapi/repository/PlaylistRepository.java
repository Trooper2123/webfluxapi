package com.webflux.webfluxapi.repository;

import com.webflux.webfluxapi.document.Playlist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends ReactiveMongoRepository<Playlist,String> {
}
