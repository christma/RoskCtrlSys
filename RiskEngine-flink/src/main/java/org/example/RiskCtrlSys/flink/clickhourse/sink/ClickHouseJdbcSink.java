package org.example.RiskCtrlSys.flink.clickhourse.sink;

import lombok.Getter;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import javax.crypto.spec.PSource;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;

import static org.apache.log4j.spi.LocationInfo.NA;

@Getter
public class ClickHouseJdbcSink<T> {

    private final SinkFunction<T> sink;

    public ClickHouseJdbcSink(String sql, String url, int batchSize) {

        System.out.println(sql + "   " + url + "   " + batchSize);
//        this.sink = JdbcSink.sink(
//                "INSERT INTO default.rods (name) VALUES (?)",
//                new ClickHouseJdbcStatementBuilder(),
//                JdbcExecutionOptions.builder()
//                        .withBatchSize(batchSize)
//                        .build(),
//                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
//                        .withUrl("jdbc:clickhouse://localhost:8123/default")
//                        .withDriverName("com.clickhouse.jdbc.ClickHouseDriver")
//                        .build()
//        );
        this.sink = JdbcSink.sink(
                sql,
                new ClickHouseJdbcStatementBuilder(),
                JdbcExecutionOptions.builder()
                        .withBatchSize(batchSize)
                        .build(),
                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                        .withUrl(url)
                        .withDriverName("com.clickhouse.jdbc.ClickHouseDriver")
                        .build()
        );
    }

    public static void setPreparedStatement(PreparedStatement ps, Field[] fields, Object obj) throws Exception {
        for (int i = 1; i <= fields.length; i++) {
            Field field = fields[i - 1];
            field.setAccessible(true);
            Object o = field.get(obj);
            if (o == null) {
                ps.setNull(i, 0);
                continue;
            }

            String fieldValue = o.toString();
            if (!"null".equals(fieldValue) && !"".equals(fieldValue)) {
                ps.setString(i, fieldValue);
            } else {
                ps.setNull(i, 0);
            }

        }

    }

}
