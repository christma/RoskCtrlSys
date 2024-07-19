package org.example;

import java.sql.PreparedStatement;

import org.example.RIskCtrlSys.modle.MetricsConfPO;
import org.example.RiskCtrlSys.flink.utils.MySQLJDBCUtil;
import org.example.riskCtrlSys.utils.common.CommonUtil;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class MetricConfMySQLThread_test implements Runnable {

    private PreparedStatement preparedStatement;
    private ConcurrentHashMap<String, String> metricAggMap = new ConcurrentHashMap<>();
    private MetricsConfPO metricsConfPO;

    public MetricConfMySQLThread_test(PreparedStatement preparedStatement) {
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
                        metricAggMap.put(name, rs.getString(name));
                    }
                }
            }
            metricsConfPO = CommonUtil.setFieldValue(MetricsConfPO.class, metricAggMap);
        } catch (SQLException e) {
//            logger.severe("SQL Exception: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
//                    logger.severe("Error closing ResultSet: " + e.getMessage());
                }
            }
        }
        return metricsConfPO;
    }
    @Override
    public void run() {
        MetricsConfPO result = metricConfQuery();
        System.out.println(result);

    }
}
