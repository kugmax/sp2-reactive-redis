package com.kugmax.learn.sp2.reactiveredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class ReactiveRedisLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveRedisLearnApplication.class, args);
	}

	@Bean
	public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory, ResourceLoader resourceLoader) {
//        JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(resourceLoader.getClassLoader());
        RedisSerializer stringSerializer = new StringRedisSerializer();

        RedisSerializationContext<String, String> serializationContext = RedisSerializationContext.newSerializationContext()
				.key(stringSerializer)
				.value(stringSerializer)
				.hashKey(stringSerializer)
				.hashValue(stringSerializer).build();
        return new ReactiveRedisTemplate(reactiveRedisConnectionFactory, serializationContext);
    }
}
