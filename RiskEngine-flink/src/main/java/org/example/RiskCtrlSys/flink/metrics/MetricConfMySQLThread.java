package org.example.RiskCtrlSys.flink.metrics;

import org.example.RIskCtrlSys.modle.MetricsConfPO;
import org.example.RiskCtrlSys.flink.utils.MySQLJDBCUtil;
import org.example.riskCtrlSys.utils.common.CommonUtil;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class MetricConfMySQLThread implements Callable<MetricsConfPO> {
    private PreparedStatement preparedStatement = null;
    private ConcurrentHashMap<String, String> MetricAggMap = new ConcurrentHashMap<>();
    private MetricsConfPO metricsConfPO = null;

    public MetricConfMySQLThread(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    private MetricsConfPO metricConfQuery() {
        ResultSet rs = null;
        try {
            rs = MySQLJDBCUtil.executeQuery(preparedStatement);
            while (rs.next()) {

                Field[] fields = MetricsConfPO.class.getDeclaredFields();
                for (Field field : fields) {
                    String name = field.getName();
                    if (CommonUtil.isExistColumn(rs, name)) {
                        MetricAggMap.put(name, rs.getString(name));
                    }
                }
//
//                MetricAggMap.put("metric_name", rs.getString("metric_name"));
//                MetricAggMap.put("metric_code", rs.getString("metric_code"));
//                MetricAggMap.put("metric_agg_type", rs.getString("metric_agg_type"));
//                MetricAggMap.put("metric_store", rs.getString("metric_store"));
//                MetricAggMap.put("scene", rs.getString("scene"));
//                MetricAggMap.put("event", rs.getString("event"));
//                MetricAggMap.put("main_dim", rs.getString("main_dim"));
//                MetricAggMap.put("aggregation", rs.getString("aggregation"));
//                MetricAggMap.put("is_enable", rs.getString("is_enable"));
//                MetricAggMap.put("datasource", rs.getString("datasource"));
//                MetricAggMap.put("window_size", rs.getString("window_size"));
//                MetricAggMap.put("window_step", rs.getString("window_step"));
//                MetricAggMap.put("window_type", rs.getString("window_type"));
//                MetricAggMap.put("flink_filter", rs.getString("flink_filter"));
//                MetricAggMap.put("flink_keyby", rs.getString("flink_keyby"));
//                MetricAggMap.put("flink_watermark", rs.getString("flink_watermark"));
            }
            metricsConfPO = CommonUtil.setFieldValue(MetricsConfPO.class, MetricAggMap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MySQLJDBCUtil.closeResultSet();
        }
        return metricsConfPO;

    }


    @Override
    public MetricsConfPO call() throws Exception {
        return metricConfQuery();
    }
}
