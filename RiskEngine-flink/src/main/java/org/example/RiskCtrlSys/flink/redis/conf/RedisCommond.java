package org.example.RiskCtrlSys.flink.redis.conf;


import lombok.Getter;

@Getter
public enum RedisCommond {

    GET(RedisDataType.STRING);

    private RedisDataType redisDataType;

    RedisCommond(RedisDataType redisDataType) {
        this.redisDataType = redisDataType;
    }
}
