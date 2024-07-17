package org.example.RiskCtrlSys.flink.metrics.task;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.example.RIskCtrlSys.modle.EventPO;

public class LoginFreHourAggFunction implements AggregateFunction<EventPO, Tuple2<String, Integer>, Tuple2<String, Integer>> {

    @Override
    public Tuple2<String, Integer> createAccumulator() {
        return new Tuple2<>("", 0);
    }

    @Override
    public Tuple2<String, Integer> add(EventPO input, Tuple2<String, Integer> acc) {

        acc.f0 = input.getUser_id();
        acc.f1 += 1;
        return acc;
    }

    @Override
    public Tuple2<String, Integer> getResult(Tuple2<String, Integer> acc) {
        return acc;
    }

    @Override
    public Tuple2<String, Integer> merge(Tuple2<String, Integer> acc0, Tuple2<String, Integer> acc1) {
        return null;
    }
}
