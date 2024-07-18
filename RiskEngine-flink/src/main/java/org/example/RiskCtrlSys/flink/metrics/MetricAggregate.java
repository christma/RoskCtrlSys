package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.example.RIskCtrlSys.modle.EventPO;

public class MetricAggregate implements AggregateFunction<EventPO, EventPO, EventPO> {
    @Override
    public EventPO createAccumulator() {
        return null;
    }

    @Override
    public EventPO add(EventPO eventPO, EventPO eventPO2) {
        return null;
    }

    @Override
    public EventPO getResult(EventPO eventPO) {
        return null;
    }

    @Override
    public EventPO merge(EventPO eventPO, EventPO acc1) {
        return null;
    }
}
