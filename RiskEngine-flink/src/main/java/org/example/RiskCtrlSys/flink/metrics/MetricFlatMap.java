package org.example.RiskCtrlSys.flink.metrics;

import lombok.SneakyThrows;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import org.apache.flink.util.TimeUtils;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RiskCtrlSys.flink.utils.MySQLJDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MetricFlatMap extends RichFlatMapFunction<EventPO, EventPO> {

    private ConcurrentHashMap<String, Object> MetricAggMap = new ConcurrentHashMap<>();


    @Override
    public void open(Configuration parameters) throws Exception {
        String sql = "";
        PreparedStatement perStatement = MySQLJDBCUtil.init(sql);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(
                () -> metricAggQuery(perStatement),
                10L,
                10L,
                TimeUnit.MINUTES
        );
    }

    @Override
    public void flatMap(EventPO eventPO, Collector<EventPO> collector) throws Exception {
        collector.collect(eventPO);
    }

    private void metricAggQuery(PreparedStatement preparedStatement) {
        try {
            ResultSet resultSet = MySQLJDBCUtil.executeQuery(preparedStatement);
            while (resultSet.next()) {
                MetricAggMap.put("", "");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
