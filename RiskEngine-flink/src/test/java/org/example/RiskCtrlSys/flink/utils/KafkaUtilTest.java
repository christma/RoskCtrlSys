package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RIskCtrlSys.modle.KafkaMessagePO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KafkaUtilTest {

    @Test
    void read() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        DataStream<String> stream = KafkaUtil.read(env);
        DataStream<EventPO> stream = KafkaUtil.read();
        stream.print();
        env.execute();
    }
}