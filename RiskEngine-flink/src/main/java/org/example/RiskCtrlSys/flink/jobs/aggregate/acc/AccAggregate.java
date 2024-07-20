package org.example.RiskCtrlSys.flink.jobs.aggregate.acc;

public interface AccAggregate {
    Double aggregate(String agg, String value_before, String value_after);
}
