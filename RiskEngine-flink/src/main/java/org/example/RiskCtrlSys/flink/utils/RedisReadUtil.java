package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.RedisPO;
import org.example.RiskCtrlSys.flink.redis.conf.RedisCommond;
import org.example.RiskCtrlSys.flink.redis.source.RedisSource;

public class RedisReadUtil {

    public static DataStream<RedisPO> read(StreamExecutionEnvironment env, RedisCommond redisCommond, String key) {

        return env.addSource(new RedisSource(redisCommond, key));
    }
}
