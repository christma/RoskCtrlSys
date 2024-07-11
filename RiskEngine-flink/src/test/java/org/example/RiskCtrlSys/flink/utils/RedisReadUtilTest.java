package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.RedisPO;
import org.example.RiskCtrlSys.flink.redis.conf.RedisCommond;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedisReadUtilTest {

    @Test
    void read() throws Exception {
        StreamExecutionEnvironment  environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);
        DataStream<RedisPO> bahir = RedisReadUtil.read(environment, RedisCommond.GET, "bahir");
        bahir.print();
        environment.execute();
    }
}