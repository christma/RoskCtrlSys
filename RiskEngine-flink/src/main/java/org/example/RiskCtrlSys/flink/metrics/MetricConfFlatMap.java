package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RIskCtrlSys.modle.MetricsConfPO;
import org.example.RiskCtrlSys.flink.utils.MySQLJDBCUtil;

import java.sql.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class MetricConfFlatMap extends RichFlatMapFunction<EventPO, EventPO> {

    private Connection conn = null;
    private PreparedStatement preparedStatement;
    ScheduledFuture future = null;
    private transient AtomicReference<MetricsConfPO> latestMetricsConf;
    private transient ScheduledExecutorService executorService;
    @Override
    public void open(Configuration parameters) throws Exception {
        executorService = Executors.newScheduledThreadPool(1);
        latestMetricsConf = new AtomicReference<>();


        conn = MySQLJDBCUtil.init();
        String sql = "select * from risk.metric_attr where is_enable = 'true' and metric_agg_type = 'flink'";
        preparedStatement = MySQLJDBCUtil.initPrepareStatement(conn, sql);

        executorService.scheduleAtFixedRate(
                new MetricConfMySQLThread(preparedStatement, latestMetricsConf),
                0L,
                10L,
                TimeUnit.SECONDS
        );
    }

    @Override
    public void close() throws Exception {
        super.close();
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override
    public void flatMap(EventPO eventPO, Collector<EventPO> collector) throws Exception {

        //获取线程返回值
        MetricsConfPO metricsConfPO = latestMetricsConf.get();
        eventPO.setMeiMetricsConfPO(metricsConfPO);
        collector.collect(eventPO);
//
//        if (metricsConfPO != null) {
//            eventPO.setMeiMetricsConfPO(metricsConfPO);
//            collector.collect(eventPO);
//        }else{
//            System.out.println("-----------------");
//        }
    }


}
