package com.kugmax.learn.sp2.reactiveredis.dao.redis;

import com.kugmax.learn.sp2.reactiveredis.dao.GameDao;
import com.kugmax.learn.sp2.reactiveredis.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppTestConfigRedis.class, loader = AnnotationConfigContextLoader.class)
public class TestGameDaoRedis {

    @Autowired
    public GameDao gameDao;

    @Autowired
    public ReactiveRedisTemplate<String, String> template;

    @Before
    public void before() {
        ReactiveRedisConnection reactiveRedisConnection = template.getConnectionFactory().getReactiveConnection();
        reactiveRedisConnection.serverCommands().flushDb().block();
        reactiveRedisConnection.close();
    }

    @Test
    public void put_get() {
        Game game = new Game(1L, "First", "Nice");
        gameDao.put(game).subscribe();

        assertEquals(game, gameDao.get(1L).block() );
    }

    @Test
    public void get_not_exists() {
        Game game = new Game(0L, null, null);
        assertEquals(game, gameDao.get(1L).block() );
    }
}
