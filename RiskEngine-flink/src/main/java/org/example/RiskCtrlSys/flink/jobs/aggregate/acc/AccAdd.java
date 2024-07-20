package org.example.RiskCtrlSys.flink.jobs.aggregate.acc;

public class AccAdd implements AccAggregate {
    @Override
    public Double aggregate(String agg, String value_before, String value_after) {
        return 0.0;
    }
}
