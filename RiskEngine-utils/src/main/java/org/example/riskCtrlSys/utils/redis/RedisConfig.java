package org.example.riskCtrlSys.utils.redis;

import com.alibaba.fastjson2.support.spring.data.redis.GenericFastJsonRedisSerializer;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private RedisPoolProperties redisPoolProperties;

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 使用 FastJson2RedisSerializer 进行序列化
//        FastJson2RedisSerializer<Object> serializer = new FastJson2RedisSerializer<>(Object.class);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());

        return redisTemplate;

    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {

        // 单机模式
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
//         集群模式
//        Map<String, Object> map = new HashMap<>();
//        map.put("spring.redis.cluster.node",redisProperties.getNodes());
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(
//                new MapPropertySource(
//                        "RedisClusterConfiguration", map
//                )
//        );

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(redisPoolProperties.getMaxIdle());
        genericObjectPoolConfig.setMinIdle(redisPoolProperties.getMinIdel());
        genericObjectPoolConfig.setMaxWait(redisPoolProperties.getMaxWait());
        genericObjectPoolConfig.setMaxTotal(redisPoolProperties.getMaxActive());


        LettucePoolingClientConfiguration clientConfiguration = LettucePoolingClientConfiguration
                .builder()
                .poolConfig(genericObjectPoolConfig)
                .build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfiguration);


    }
}
