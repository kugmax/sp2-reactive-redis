package com.kugmax.learn.sp2.reactiveredis.dao.redis;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
@ComponentScan({"com.kugmax.learn.sp2.reactiveredis.dao"})
public class AppTestConfigRedis {
    private static RedisServer redisServer;

    @Bean
    public static PropertyPlaceholderConfigurer properties() throws Exception {
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new ClassPathResource("application.properties"));
        return placeholderConfigurer;
    }

    @Bean
	public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        RedisSerializer stringSerializer = new StringRedisSerializer();

        RedisSerializationContext<String, String> serializationContext = RedisSerializationContext.newSerializationContext()
				.key(stringSerializer)
				.value(stringSerializer)
				.hashKey(stringSerializer)
				.hashValue(stringSerializer).build();
        return new ReactiveRedisTemplate(reactiveRedisConnectionFactory, serializationContext);
    }

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6380);
    }

    @PostConstruct
    public void startRedis() throws IOException {
        redisServer = new RedisServer(6380);
        try {
            redisServer.start();
        } catch (Exception e) {}
    }

    @PreDestroy
    public void stopRedis() {
        try {
            redisServer.stop();
        } catch (Exception e) {}
    }
}
