package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.WindowAssigner;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.example.RIskCtrlSys.modle.EventPO;

import java.util.Collection;
import java.util.Collections;

public class MetricWindowAssigner extends WindowAssigner<EventPO, TimeWindow> {

    private long size;
    private long step;
    private long offset;

    @Override
    public Collection<TimeWindow> assignWindows(EventPO eventPO, long l, WindowAssignerContext windowAssignerContext) {
        return Collections.emptyList();
    }

    @Override
    public Trigger<EventPO, TimeWindow> getDefaultTrigger(StreamExecutionEnvironment streamExecutionEnvironment) {
        return null;
    }

    @Override
    public TypeSerializer<TimeWindow> getWindowSerializer(ExecutionConfig executionConfig) {
        return null;
    }

    @Override
    public boolean isEventTime() {
        return false;
    }
}
