package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.functions.RichFilterFunction;
import org.example.RIskCtrlSys.modle.EventPO;

public class MetricFilter extends RichFilterFunction<EventPO> {
    @Override
    public boolean filter(EventPO eventPO) throws Exception {
        return false;
    }
}
