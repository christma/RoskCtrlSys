package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.example.RIskCtrlSys.modle.EventPO;

public class MetricTrigger extends Trigger<EventPO, TimeWindow> {
    @Override
    public TriggerResult onElement(EventPO eventPO, long l, TimeWindow timeWindow, TriggerContext triggerContext) throws Exception {
        return null;
    }

    @Override
    public TriggerResult onProcessingTime(long l, TimeWindow timeWindow, TriggerContext triggerContext) throws Exception {
        return null;
    }

    @Override
    public TriggerResult onEventTime(long l, TimeWindow timeWindow, TriggerContext triggerContext) throws Exception {
        return null;
    }

    @Override
    public void clear(TimeWindow timeWindow, TriggerContext triggerContext) throws Exception {

    }
}
