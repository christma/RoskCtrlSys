package org.example;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.ClickHousePO;
import org.example.RiskCtrlSys.flink.clickhourse.sink.ClickHouseJdbcStatementBuilder;
import org.example.RiskCtrlSys.flink.utils.ClickHouseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // 明确加载 ClickHouse JDBC 驱动程序
        try {
            Class.forName("com.clickhouse.jdbc.ClickHouseDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        ClickHousePO ck1 = new ClickHousePO("ck-test-07");
        ClickHousePO ck2 = new ClickHousePO("ck-test-08");
        // 示例数据流
        DataStream<ClickHousePO> stream = env.fromCollection(Arrays.asList(ck1, ck2));
        String sql = "INSERT INTO default.rods (name) VALUES (?)";

        ClickHouseUtil.write(stream,sql,2);
        // 添加 JDBC sink
//        stream.addSink(JdbcSink.sink(
//                "INSERT INTO default.rods (name) VALUES (?)",
//                new ClickHouseJdbcStatementBuilder(),
//                JdbcExecutionOptions.builder()
//                        .withBatchSize(2)
//                        .build(),
//                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
//                        .withUrl("jdbc:clickhouse://localhost:8123/default")
//                        .withDriverName("com.clickhouse.jdbc.ClickHouseDriver")
//                        .build()
//        ));

        try {
            env.execute("Flink to ClickHouse");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}