package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.example.RIskCtrlSys.modle.EventPO;

public class MetricTimestampAssigner implements SerializableTimestampAssigner<EventPO> {
    @Override
    public long extractTimestamp(EventPO eventPO, long l) {
        return 0;
    }
}
