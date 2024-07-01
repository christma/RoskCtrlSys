package org.example.riskCtrlSys.utils.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
@Component
@Data
public class RedisPoolProperties {

    private Integer maxIdle;
    private Integer minIdel;
    private Integer maxActive;
    private Duration maxWait;
}
