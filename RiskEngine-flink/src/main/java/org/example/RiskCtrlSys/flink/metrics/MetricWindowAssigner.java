package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.WindowAssigner;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.riskCtrlSys.utils.date.DateUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

import static com.ibm.icu.text.TimeZoneFormat.Style.ZONE_ID;

public class MetricWindowAssigner extends WindowAssigner<EventPO, TimeWindow> {

    private static final ZoneId ZONE_ID = ZoneOffset.systemDefault();
    private long size;
    private long step;
    private final long offset = 0L;

    @Override
    public Collection<TimeWindow> assignWindows(EventPO eventPO, long timestamp, WindowAssignerContext windowAssignerContext) {
        String _size = eventPO.getMetricsConfPO().getWindow_size();
        size = Long.parseLong(_size) * 1000L;
        String _step = eventPO.getMetricsConfPO().getWindow_step();
        step = Long.parseLong(_step) * 1000L;

        String _winStart = eventPO.getMetricsConfPO().getWindow_start_time();
        String _winEnd = eventPO.getMetricsConfPO().getWindow_end_time();
        String _days = eventPO.getMetricsConfPO().getWindow_day();

        if (_winStart.equals("0") && _winEnd.equals("0") && _days.equals("0")) {
            return windowNoDayRange(timestamp);
        } else {
            return windowDayRange(timestamp, _winStart, _winEnd, _days);

        }
    }

    private Collection<TimeWindow> windowDayRange(long timestamp, String _winStart, String _winEnd, String _days) {

        LocalDateTime _winStart_l = DateUtils.convertString2LocalDateTime(_winStart);
        long _winEndSecond = DateUtils.convertString2LocalDateTime(_winEnd).atZone(ZONE_ID).toEpochSecond();

        LocalDateTime _plus_day = DateUtils.localDateTimePlusDay(_winStart_l, Long.parseLong(_days));
        LocalDateTime _winEnd_l = DateUtils.localDateTimePlusSeconds(_plus_day, _winEndSecond);

        long winStart = DateUtils.convertLocalDateTime2Timestamp(_winStart_l);
        long winEnd = DateUtils.convertLocalDateTime2Timestamp(_winEnd_l);


        if (timestamp > Long.MIN_VALUE) {
            long lastStart = TimeWindow.getWindowStartWithOffset(
                    timestamp, this.offset, this.step
            );

            long lastEnd = lastStart + step;
            int winCounts = (int) ((lastEnd - winEnd) / this.step);
            List<TimeWindow> windows = new ArrayList<>(winCounts);

            while (lastEnd < winEnd) {
                windows.add(new TimeWindow(lastStart, lastEnd));
            }
            return windows;
        }
        throw new RuntimeException("时间窗口错误");

    }

    private Collection<TimeWindow> windowNoDayRange(long timestamp) {
        if (timestamp > Long.MIN_VALUE) {
            List<TimeWindow> windows = new ArrayList<>((int) (size / step));
            long lastStart = TimeWindow.getWindowStartWithOffset(timestamp, offset, step);
            for (long start = lastStart; start > timestamp - size; start -= step) {
                windows.add(new TimeWindow(start, start + size));
            }
            return windows;
        } else {
            throw new RuntimeException(
                    "Record has Long.MIN_VALUE timestamp (= no timestamp marker). "
                            + "Is the time characteristic set to 'ProcessingTime', or did you forget to call "
                            + "'DataStream.assignTimestampsAndWatermarks(...)'?");
        }
    }

    @Override
    public Trigger<EventPO, TimeWindow> getDefaultTrigger(StreamExecutionEnvironment streamExecutionEnvironment) {
        return null;
    }

    @Override
    public TypeSerializer<TimeWindow> getWindowSerializer(ExecutionConfig executionConfig) {
        return new TimeWindow.Serializer();
    }

    @Override
    public boolean isEventTime() {
        return true;
    }
}
