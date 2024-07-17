package org.example.RiskCtrlSys.flink.jobs;

import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RIskCtrlSys.modle.KafkaMessagePO;
import org.example.RiskCtrlSys.flink.metrics.KafkaETL;
import org.example.RiskCtrlSys.flink.metrics.task.LoginFreHourTask;
import org.example.RiskCtrlSys.flink.utils.KafkaUtil;
import org.example.riskCtrlSys.utils.date.DateUtils;
import org.h2.mvstore.DataUtils;

import java.time.Duration;
import java.time.LocalDateTime;

public class MetricWithKafka {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.enableCheckpointing(60000);
        env.getCheckpointConfig().setCheckpointTimeout(30000);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setTolerableCheckpointFailureNumber(3);
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        env.setStateBackend(new HashMapStateBackend());
        env.setParallelism(1);


        DataStream<KafkaMessagePO> stream = KafkaUtil.read(env);

        SingleOutputStreamOperator<EventPO> operator = stream.map(new KafkaETL());

        operator.print();

        // 提取POJO 的时间 ：event_time

        SerializableTimestampAssigner<EventPO> timestampAssigner = (eventPO, l) -> {
            LocalDateTime localDateTime = DateUtils.convertString2LocalDateTime(eventPO.getEvent_time());
            return DateUtils.convertLocalDateTime2Timestamp(localDateTime);
        };

        SingleOutputStreamOperator<EventPO> watermarks = operator.assignTimestampsAndWatermarks(WatermarkStrategy
                .<EventPO>forBoundedOutOfOrderness(Duration.ofMinutes(1))
                .withTimestampAssigner(timestampAssigner));


        LoginFreHourTask.process(watermarks);



        env.execute();

    }
}
