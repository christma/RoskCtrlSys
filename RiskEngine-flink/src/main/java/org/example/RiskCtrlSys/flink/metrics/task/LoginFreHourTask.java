package org.example.RiskCtrlSys.flink.metrics.task;


import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.example.RIskCtrlSys.modle.EventPO;

import java.util.Objects;

// 每5分钟 统计 近1 小时 登陆频率
public class LoginFreHourTask {
    public static void process(DataStream<EventPO> input) {
        //filter
        SingleOutputStreamOperator<EventPO> filtered = input.filter((FilterFunction<EventPO>) eventPO -> {
            if (Objects.equals(eventPO.getEvent_behavior_name(), "behavior1")) {
                return true;
            }
            return false;
        });

        // keyed

        KeyedStream<EventPO, Integer> keyedStream = filtered.keyBy(new KeySelector<EventPO, Integer>() {
            @Override
            public Integer getKey(EventPO eventPO) throws Exception {
                return eventPO.getUser_id_int();
            }
        });

        keyedStream.window(SlidingEventTimeWindows.of(Time.hours(1), Time.minutes(5)))
                .aggregate(new LoginFreHourAggFunction()).print();
    }
}
