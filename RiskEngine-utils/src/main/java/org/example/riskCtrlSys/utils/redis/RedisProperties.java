package org.example.riskCtrlSys.utils.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.redis")
@Component
@Data
public class RedisProperties {
    private String host;
    private Integer port;
}
