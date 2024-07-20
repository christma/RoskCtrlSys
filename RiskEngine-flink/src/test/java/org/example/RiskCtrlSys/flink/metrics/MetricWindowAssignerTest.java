package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.example.RIskCtrlSys.modle.EventPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class MetricWindowAssignerTest {

    private MetricWindowAssigner metricWindowAssignerUnderTest;

    @BeforeEach
    void setUp() {
        metricWindowAssignerUnderTest = new MetricWindowAssigner();
    }

    @Test
    void testAssignWindows() {
        // Setup
        final EventPO eventPO = new EventPO(0, "event_time", "event_behavior_name", "event_target_name");

        // Run the test
        final Collection<TimeWindow> result = metricWindowAssignerUnderTest.assignWindows(eventPO, 0L, null);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetDefaultTrigger() {
        assertThat(metricWindowAssignerUnderTest.getDefaultTrigger(new StreamExecutionEnvironment())).isNull();
    }

    @Test
    void testGetWindowSerializer() {
        assertThat(metricWindowAssignerUnderTest.getWindowSerializer(new ExecutionConfig())).isNull();
    }

    @Test
    void testIsEventTime() {
        assertThat(metricWindowAssignerUnderTest.isEventTime()).isFalse();
    }
}
