package org.example;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.ClickHousePO;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RIskCtrlSys.modle.KafkaMessagePO;
import org.example.RIskCtrlSys.modle.MetricsConfPO;
import org.example.RiskCtrlSys.flink.clickhourse.sink.ClickHouseJdbcStatementBuilder;
import org.example.RiskCtrlSys.flink.metrics.KafkaETL;
import org.example.RiskCtrlSys.flink.metrics.MetricConfFlatMap;
import org.example.RiskCtrlSys.flink.utils.ClickHouseUtil;
import org.example.RiskCtrlSys.flink.utils.KafkaUtil;
import org.example.RiskCtrlSys.flink.utils.MySQLJDBCUtil;
import org.example.RiskCtrlSys.flink.utils.ParameterUtil;
import org.example.riskCtrlSys.utils.common.CommonUtil;

import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterUtil.getParameters("flink.properties");
        env.getConfig().setGlobalJobParameters(parameterTool);
        env.setParallelism(1);
        DataStream<KafkaMessagePO> stream = KafkaUtil.read(env);
        SingleOutputStreamOperator<EventPO> operator = stream.map(new KafkaETL());
        SingleOutputStreamOperator<EventPO> flatMap = operator.flatMap(new MetricConfFlatMap());
        flatMap.print();

        env.execute();
//        MetricsConfPO metricsConfPO = metricConfQuery();
//        System.out.println(metricsConfPO);

    }


    private static MetricsConfPO metricConfQuery() throws SQLException {
        MetricsConfPO metricsConfPO = null;
        ConcurrentHashMap<String, String> MetricAggMap = new ConcurrentHashMap<>();
        String sql = "select * from metric_attr where is_enable = 'true' and metric_agg_type = 'flink'";
        Connection conn = MySQLJDBCUtil.init();
        System.out.println(conn);
        PreparedStatement prepareStatement = MySQLJDBCUtil.initPrepareStatement(conn, sql);
        System.out.println(prepareStatement);
        try {
            ResultSet rs = MySQLJDBCUtil.executeQuery(prepareStatement);
            while (rs.next()) {
                MetricAggMap.put("metric_name", rs.getString("metric_name"));
                MetricAggMap.put("metric_code", rs.getString("metric_code"));
                MetricAggMap.put("metric_agg_type", rs.getString("metric_agg_type"));
                MetricAggMap.put("metric_store", rs.getString("metric_store"));
                MetricAggMap.put("scene", rs.getString("scene"));
                MetricAggMap.put("event", rs.getString("event"));
                MetricAggMap.put("main_dim", rs.getString("main_dim"));
                MetricAggMap.put("aggregation", rs.getString("aggregation"));
                MetricAggMap.put("is_enable", rs.getString("is_enable"));
                MetricAggMap.put("datasource", rs.getString("datasource"));
                MetricAggMap.put("window_size", rs.getString("window_size"));
                MetricAggMap.put("window_step", rs.getString("window_step"));
                MetricAggMap.put("window_type", rs.getString("window_type"));
                MetricAggMap.put("flink_filter", rs.getString("flink_filter"));
                MetricAggMap.put("flink_keyby", rs.getString("flink_keyby"));
                MetricAggMap.put("flink_watermark", rs.getString("flink_watermark"));
            }
            metricsConfPO = CommonUtil.setFieldValue(MetricsConfPO.class, MetricAggMap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MySQLJDBCUtil.closeResultSet();
        }

        MetricAggMap.forEach((key,value) -> {
            System.out.println(key +" -> "+ value);
        });

        return metricsConfPO;

    }
}