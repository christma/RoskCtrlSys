package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;

import java.io.IOException;

public class RedisWriteUtil {

    private static FlinkJedisPoolConfig JEDIS_POOL_CONFIG = null;

    static {
        ParameterTool parameterTool = null;

        try {
            parameterTool = ParameterUtil.getParameters("flink.properties");
            String host = parameterTool.get("redis.host");
            String port = parameterTool.get("redis.port");
            JEDIS_POOL_CONFIG = new FlinkJedisPoolConfig.Builder().setHost(host).setPort(Integer.parseInt(port)).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeByBahirWithString(DataStream<Tuple2<String, String>> input) {
        input.addSink(new RedisSink<Tuple2<String, String>>(JEDIS_POOL_CONFIG, new RedisSinkByBahirWithString()));
    }


}



