package org.example.RiskCtrlSys.flink.metrics.task;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.example.RIskCtrlSys.modle.EventPO;

public class LoginFreHourAggFunction implements AggregateFunction<EventPO, Tuple2<Integer, Integer>, Tuple2<Integer, Integer>> {

    @Override
    public Tuple2<Integer, Integer> createAccumulator() {
        return new Tuple2<>(0, 0);
    }

    @Override
    public Tuple2<Integer, Integer> add(EventPO input, Tuple2<Integer, Integer> acc) {

        acc.f0 = input.getUser_id_int();
        acc.f1 += 1;
        return acc;
    }

    @Override
    public Tuple2<Integer, Integer> getResult(Tuple2<Integer, Integer> acc) {
        return acc;
    }

    @Override
    public Tuple2<Integer, Integer> merge(Tuple2<Integer, Integer> acc0, Tuple2<Integer, Integer> acc1) {
        return null;
    }
}
