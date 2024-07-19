package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.java.functions.KeySelector;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.riskCtrlSys.utils.common.CommonUtil;

public class MetricKeyBy implements KeySelector<EventPO, Integer> {
    @Override
    public Integer getKey(EventPO eventPO) throws Exception {
        String keyby = eventPO.getMeiMetricsConfPO().getFlink_keyby();
        return (Integer) CommonUtil.getFieldValue(eventPO, keyby);
    }
}
