package com.kugmax.learn.sp2.reactiveredis.dao;

import com.kugmax.learn.sp2.reactiveredis.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveHashCommands;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GameDaoRedis implements GameDao {

    public static final String BASE_KEY = "com:kugmax:sp2:reactive:game:";

    private ReactiveRedisTemplate template;

    @Autowired
    public GameDaoRedis(ReactiveRedisTemplate template) {
        this.template = template;
    }

    @Override
    public void put(Game game) {
//        ByteBuffer key =  ByteBuffer.wrap(content.getBytes("UTF-8")) ;
//
//        ByteBuffer key = stringBuffer;
//        CharBuffer f;
//
//        Map<ByteBuffer, ByteBuffer> fields = new HashMap<>();
//        fields.put("name", game.getName());
//        fields.put("description", game.getDescription());
//
//        template.execute( reactiveRedisConnection -> {
//            ReactiveHashCommands reactiveHashCommands = reactiveRedisConnection.hashCommands();
//            reactiveHashCommands.hMSet(key, fields);
//
//
//            }
//        );


//        String content = "It's easy to convert ByteBuffer to String in Java";
//        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes("UTF-8"));
//        String converted = new String(buffer.array(), "UTF-8");
//        System.out.println("Original : " + content);
//        System.out.println("Converted : " + converted);

    }

    @Override
    public Mono<Game> get(long id) {
//        template.createMono(reactiveRedisConnection -> reactiveRedisConnection. );


        return null;
    }

    private ByteBuffer buildKey(long id) {
//        return ByteBuffer.wrap( (BASE_KEY + String.valueOf(id)).getBytes("UTF-8")) ;
        return null;
    }


}
