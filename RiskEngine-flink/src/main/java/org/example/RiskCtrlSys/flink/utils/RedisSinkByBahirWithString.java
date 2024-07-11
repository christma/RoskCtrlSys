package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;


public class RedisSinkByBahirWithString implements RedisMapper<Tuple2<String,String>> {

    @Override
    public RedisCommandDescription getCommandDescription() {
        return new RedisCommandDescription(RedisCommand.SET);
    }

    @Override
    public String getKeyFromData(Tuple2<String, String> input) {
        return input.f0;
    }

    @Override
    public String getValueFromData(Tuple2<String, String> input) {
        return input.f1;
    }
}
