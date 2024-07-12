package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.ClickHousePO;
import org.example.RiskCtrlSys.flink.clickhourse.sink.ClickHouseJdbcSink;
import org.example.RiskCtrlSys.flink.clickhourse.source.ClickHouseSource;

import java.io.IOException;
import java.util.Arrays;

public class ClickHouseUtil {
    private static String URL = null;


    static {
        try {
            ParameterTool parameterTool = ParameterUtil.getParameters("flink.properties");
            URL = parameterTool.get("clickhouse.url");
            System.out.println(URL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // 读 clickhouse
    public DataStream<ClickHousePO> read(StreamExecutionEnvironment env, String sql) {
        return env.addSource(new ClickHouseSource(URL, sql));
    }

    //批量写clickhouse

    public static DataStreamSink write(DataStream dataStream, String sql, Integer batchSize) {
        ClickHouseJdbcSink clickHouseJdbcSink = new ClickHouseJdbcSink(sql, URL, batchSize);
        return dataStream.addSink(clickHouseJdbcSink.getSink());
    }


}
