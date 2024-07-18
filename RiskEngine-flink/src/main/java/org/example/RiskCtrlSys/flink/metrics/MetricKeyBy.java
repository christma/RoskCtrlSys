package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.java.functions.KeySelector;
import org.example.RIskCtrlSys.modle.EventPO;

public class MetricKeyBy implements KeySelector<EventPO,EventPO> {
    @Override
    public EventPO getKey(EventPO eventPO) throws Exception {
        return null;
    }
}
