package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.example.RIskCtrlSys.modle.EventPO;

public class MetricWindowFunction implements WindowFunction<EventPO, EventPO, EventPO, TimeWindow> {


    @Override
    public void apply(EventPO eventPO, TimeWindow timeWindow, Iterable<EventPO> iterable, Collector<EventPO> collector) throws Exception {
        
    }
}
