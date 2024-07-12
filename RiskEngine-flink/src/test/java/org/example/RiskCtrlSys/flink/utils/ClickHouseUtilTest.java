package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.ClickHousePO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ClickHouseUtilTest {

    private ClickHouseUtil clickHouseUtilUnderTest;

    @BeforeEach
    void setUp() {
        clickHouseUtilUnderTest = new ClickHouseUtil();
    }

    @Test
    void testRead() throws Exception {
        // Setup
        final StreamExecutionEnvironment env =  StreamExecutionEnvironment.getExecutionEnvironment();

        // Run the test
        final DataStream<ClickHousePO> result = clickHouseUtilUnderTest.read(env, "select name from default.rods");

        // Verify the results

        result.print().setParallelism(1);

        env.execute();
    }

    @Test
    void testWrite() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        ClickHousePO ck1 = new ClickHousePO("ck-test-11");
        ClickHousePO ck2 = new ClickHousePO("ck-test-22");

        DataStreamSource<ClickHousePO> ds = env.fromCollection(Arrays.asList(ck1, ck2));
        String sql = "insert into default.rods( name )values( ? )";
        ClickHouseUtil.write(ds, sql, 2);


        env.execute();
    }
}
