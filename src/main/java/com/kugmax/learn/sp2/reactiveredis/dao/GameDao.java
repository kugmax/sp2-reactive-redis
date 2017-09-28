package com.kugmax.learn.sp2.reactiveredis.dao;

import com.kugmax.learn.sp2.reactiveredis.model.Game;
import reactor.core.publisher.Mono;

public interface GameDao {
    void put(Game game);

    Mono<Game> get(long id);
}
