package com.kugmax.learn.sp2.reactiveredis.dao;

import com.kugmax.learn.sp2.reactiveredis.model.Game;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameDao {
    Flux<Void> put(Game game);

    Mono<Game> get(long id);
}
