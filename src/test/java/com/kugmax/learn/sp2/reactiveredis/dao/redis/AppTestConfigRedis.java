package com.kugmax.learn.sp2.reactiveredis.dao.redis;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
@ComponentScan({"com.kugmax.learn.sp2.reactiveredis.dao"})
//@EnableAutoConfiguration(RedisReactiveAutoConfiguration.)
public class AppTestConfigRedis {
    private static RedisServer redisServer;

    @Bean
    public static PropertyPlaceholderConfigurer properties() throws Exception {
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new ClassPathResource("application.properties"));
        return placeholderConfigurer;
    }

//    @Bean
//    public ReactiveRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        return new ReactiveRedisTemplate(jedisConnectionFactory, );
//    }
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(10);
//        jedisPoolConfig.setMaxIdle(10);
//
//        JedisConnectionFactory bean = new JedisConnectionFactory();
//        bean.setHostName("localhost");
//        bean.setDatabase(5);
//        bean.setPort(6380);
//        bean.setPoolConfig(jedisPoolConfig);
//
//        return bean;
//    }
//
//    @PostConstruct
//    public void startRedis() throws IOException {
//        redisServer = new RedisServer(6380);
//        try {
//            redisServer.start();
//        } catch (Exception e) {}
//    }
//
//    @PreDestroy
//    public void stopRedis() {
//        try {
//            redisServer.stop();
//        } catch (Exception e) {}
//    }
}
