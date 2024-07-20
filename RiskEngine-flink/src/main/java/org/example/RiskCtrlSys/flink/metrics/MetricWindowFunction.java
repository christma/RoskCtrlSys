package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.example.RIskCtrlSys.modle.EventPO;

public class MetricWindowFunction implements WindowFunction<Tuple2<EventPO, Double>, Tuple2<EventPO, Double>, Integer, TimeWindow> {


    @Override
    public void apply(Integer key, TimeWindow timeWindow, Iterable<Tuple2<EventPO, Double>> input, Collector<Tuple2<EventPO, Double>> out) throws Exception {

        Tuple2<EventPO, Double> next = input.iterator().next();
        out.collect(next);
    }
}
