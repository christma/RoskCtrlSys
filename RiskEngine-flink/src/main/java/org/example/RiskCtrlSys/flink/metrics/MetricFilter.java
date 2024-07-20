package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.functions.RichFilterFunction;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.riskCtrlSys.utils.common.CommonUtil;

public class MetricFilter extends RichFilterFunction<EventPO> {
    @Override
    public boolean filter(EventPO eventPO) throws Exception {
        String filter = eventPO.getMetricsConfPO().getFlink_filter();
        if (filter.isEmpty()) {
            return true;
        }
        return eventPO.getEvent_behavior_name() == CommonUtil.getFieldValue(eventPO, filter);
    }
}
