package com.kugmax.learn.sp2.reactiveredis.dao.redis;

import com.kugmax.learn.sp2.reactiveredis.dao.GameDao;
import com.kugmax.learn.sp2.reactiveredis.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppTestConfigRedis.class, loader = AnnotationConfigContextLoader.class)
public class TestGameDaoRedis {

    @Autowired
    public GameDao gameDao;

    @Test
    public void ff() {
        Game game = new Game(1L, "First", "Nice");
        gameDao.put(game);

        assertEquals(game, gameDao.get(1L).block() );
    }
}
