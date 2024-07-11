package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedisUtilTest {

    @Test
    void writeByBahirWithString() throws Exception {
        // Setup
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Tuple2<String, String> tuple = Tuple2.of("bahir", "this is a flink write redis test");
        DataStreamSource<Tuple2<String, String>> source = env.fromElements(tuple);


        // Run the test
        RedisUtil.writeByBahirWithString(source);

        // Verify the results

        env.execute();
    }
}