package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.types.Row;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MySQLUtilTest {

    @Test
    void readMySQLWithTableOrSQLAPI() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStream<Row> dataStream = MySQLUtil.readMySQLWithTableOrSQLAPI(env);
        dataStream.print();

        env.execute();


    }
}