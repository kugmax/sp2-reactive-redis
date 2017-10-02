package com.kugmax.learn.sp2.reactiveredis.controller;

import com.kugmax.learn.sp2.reactiveredis.dao.GameDao;
import com.kugmax.learn.sp2.reactiveredis.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class GameController {

    private GameDao gameDao;

    @Autowired
    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
    public Mono<Game> getGame(@PathVariable("id") long id ) {
        return gameDao.get(id);
    }

    @RequestMapping(value = "/game/", method = RequestMethod.PUT)
    public void save(@RequestBody Game game) {
        gameDao.put(game);
    }
}
