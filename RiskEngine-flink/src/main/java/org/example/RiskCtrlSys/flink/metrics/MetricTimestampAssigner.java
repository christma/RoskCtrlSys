package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.riskCtrlSys.utils.date.DateUtils;

import java.time.LocalDateTime;

public class MetricTimestampAssigner implements SerializableTimestampAssigner<EventPO> {


    @Override
    public long extractTimestamp(EventPO eventPO, long l) {
        return DateUtils.convertLocalDateTime2Timestamp(DateUtils.convertString2LocalDateTime(eventPO.getEvent_time()));
    }
}
