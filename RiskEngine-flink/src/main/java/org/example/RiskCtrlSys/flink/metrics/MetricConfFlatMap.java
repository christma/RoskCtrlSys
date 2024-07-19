package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RIskCtrlSys.modle.MetricsConfPO;
import org.example.RiskCtrlSys.flink.utils.MySQLJDBCUtil;

import java.sql.*;
import java.util.concurrent.*;

public class MetricConfFlatMap extends RichFlatMapFunction<EventPO, EventPO> {

    private Connection conn = null;
    private PreparedStatement preparedStatement;
//    ScheduledFuture future = null;

    @Override
    public void open(Configuration parameters) throws Exception {
        conn = MySQLJDBCUtil.init();
    }

    @Override
    public void flatMap(EventPO eventPO, Collector<EventPO> collector) throws Exception {
        String sql = "select * from risk.metric_attr where is_enable = 'true' and metric_agg_type = 'flink'";
        preparedStatement = MySQLJDBCUtil.initPrepareStatement(conn,sql);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture future = executorService.schedule(
                new MetricConfMySQLThread(preparedStatement),
                3L,
                TimeUnit.SECONDS
        );
        //获取线程返回值
        MetricsConfPO metricsConfPO = (MetricsConfPO) future.get();
        eventPO.setMeiMetricsConfPO(metricsConfPO);
        collector.collect(eventPO);
    }


}
