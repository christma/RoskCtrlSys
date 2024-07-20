package org.example.RiskCtrlSys.flink.jobs;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RIskCtrlSys.modle.KafkaMessagePO;
import org.example.RiskCtrlSys.flink.metrics.*;
import org.example.RiskCtrlSys.flink.utils.KafkaUtil;

import java.time.Duration;

public class GenericMetricWithKafka {

    public static void main(String[] args) {




        Duration max_time = Duration.ofMinutes(1);
        KafkaUtil.read()
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy
                                .<EventPO>forBoundedOutOfOrderness(max_time)
                                .withTimestampAssigner(new MetricTimestampAssigner())
                )
                .flatMap(new MetricConfFlatMap())
                .filter(new MetricFilter())
                .keyBy(new MetricKeyBy())
                .window(new MetricWindowAssigner())
                .trigger(new MetricTrigger())
                .aggregate(new MetricAggregate(), new MetricWindowFunction()).print();
    }
}
