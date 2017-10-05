package com.kugmax.learn.sp2.reactiveredis.dao;

import com.kugmax.learn.sp2.reactiveredis.model.Game;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.ReactiveHashCommands;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

@Repository
public class GameDaoRedis implements GameDao {
    private static final Log log = LogFactory.getLog(GameDaoRedis.class);

    public static final String BASE_KEY = "com:kugmax:sp2:reactive:game:";

    @Value("${dao.game.redis.ttl.seconds}")
    public long TTL_SECONDS;

    private ReactiveRedisTemplate<String, String> template;

    @Autowired
    public GameDaoRedis(ReactiveRedisTemplate<String, String> template) {
        this.template = template;
    }

    @Override
    public void put(Game game) {
        log.info("### game " + game);

        Mono<Boolean> booleanMono = template.opsForValue().set("Here", "use");
        booleanMono.subscribe();

        ByteBuffer key = wrap( buildKey(game.getGameID()) );

        Map<ByteBuffer, ByteBuffer> fields = new HashMap<>();
        fields.put( wrap("name"), wrap(game.getName()) );
        fields.put( wrap("description"), wrap(game.getDescription()) );

        Duration ttl = Duration.ofSeconds(TTL_SECONDS);

        Flux<?> result = template.execute( reactiveRedisConnection -> {
            ReactiveHashCommands reactiveHashCommands = reactiveRedisConnection.hashCommands();
            reactiveHashCommands.hMSet(key, fields).subscribe();
            reactiveRedisConnection.keyCommands().expire(key, ttl).subscribe();
            return Flux.empty();
            }
        );

        result.subscribe();
    }

    @Override
    public Mono<Game> get(long id) {
        ReactiveHashOperations<String, String, String> hops = template.opsForHash();

        Flux<Map.Entry<String, String>> entriesGame = hops.entries(buildKey(id));

        return entriesGame.collect(
                Collector.of(
                        () -> new Game(id),
                        this::persist,
                        (game, game2) -> game,
                        Function.identity()
                )
        );
    }

    private void persist(Game game, Map.Entry<String, String> entry) {
        if ("name".equals(entry.getKey()) ) {
            game.setName(entry.getValue());

        } else if ("description".equals(entry.getKey())) {
            game.setDescription(entry.getValue());
        } else {
            log.error("Unsupported entry " + entry);
        }
    }

    private String buildKey(long id) {
        return BASE_KEY + String.valueOf(id);
    }

    private ByteBuffer wrap(String txt) {
        try {
            return ByteBuffer.wrap(txt.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("Nice api spring-data ((((( " + e.getMessage(), e);
        }

        return null; //NullPointerException
    }
}
