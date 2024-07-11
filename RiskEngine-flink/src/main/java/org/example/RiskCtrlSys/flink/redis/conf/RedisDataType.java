package org.example.RiskCtrlSys.flink.redis.conf;

import lombok.Getter;


@Getter
public enum RedisDataType {
    STRING,
    HASH,
    LIST,
    SET,
    SORTED_SET;

    RedisDataType() {
    }
}
