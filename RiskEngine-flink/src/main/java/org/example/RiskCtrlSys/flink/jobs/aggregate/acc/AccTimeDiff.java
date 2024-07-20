package org.example.RiskCtrlSys.flink.jobs.aggregate.acc;

import org.example.riskCtrlSys.utils.date.DateUtils;

import java.time.Duration;
import java.time.LocalDateTime;

public class AccTimeDiff implements AccAggregate {
    @Override
    public Double aggregate(String agg, String valueBefore, String valueAfter) {
        LocalDateTime before = DateUtils.convertString2LocalDateTime(valueBefore);
        LocalDateTime after = DateUtils.convertString2LocalDateTime(valueAfter);
        Duration diff = Duration.between(before, after);
        return diff.toMinutes() * 60.0;
    }
}
