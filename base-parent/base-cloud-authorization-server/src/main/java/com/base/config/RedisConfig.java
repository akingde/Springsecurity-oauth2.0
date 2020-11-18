package com.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {

	@SuppressWarnings("deprecation")
	@Bean
    public RedisTemplate<Object, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
	
	// 设置序列化
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
            Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
     om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    jackson2JsonRedisSerializer.setObjectMapper(om);
    // 配置redisTemplate
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
    redisTemplate.setConnectionFactory(lettuceConnectionFactory);
    RedisSerializer<?> stringSerializer = new StringRedisSerializer();
    redisTemplate.setKeySerializer(stringSerializer);// key序列化
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
    redisTemplate.setHashKeySerializer(stringSerializer);// Hash key序列化
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
	}
}
