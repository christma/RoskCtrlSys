package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.connector.kafka.source.reader.deserializer.KafkaRecordDeserializationSchema;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RIskCtrlSys.modle.KafkaMessagePO;
import org.example.RiskCtrlSys.flink.kafka.MyDeserializationSchema;
import org.example.RiskCtrlSys.flink.metrics.KafkaETL;

import java.io.IOException;

public class KafkaUtil {
    public static KafkaSource<KafkaMessagePO> KAFKA_SOURCE = null;

    public static final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    static ParameterTool parameterTool = null;


    public static DataStream<EventPO> read() {
        initEnv();
        kafkaSourceBuilder();
        return makeEventStream();
    }

    private static DataStream<EventPO> makeEventStream() {
        return env.fromSource(KAFKA_SOURCE, WatermarkStrategy.noWatermarks(), "kafka source").map(new KafkaETL());
    }

    private static void kafkaSourceBuilder() {
        try {
            parameterTool = ParameterUtil.getParameters();
            String brokers = parameterTool.get("kafka.brokers");
            String topic = parameterTool.get("kafka.topic");
            String groupId = parameterTool.get("kafka.groupId");
            KAFKA_SOURCE = KafkaSource.<KafkaMessagePO>builder().setBootstrapServers(brokers).setTopics(topic).setGroupId(groupId).setStartingOffsets(OffsetsInitializer.earliest()).setDeserializer(KafkaRecordDeserializationSchema.of(new MyDeserializationSchema()))
//                    .setValueOnlyDeserializer(new SimpleStringSchema())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void initEnv() {
        env.enableCheckpointing(60000);
        env.getCheckpointConfig().setCheckpointTimeout(30000);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setTolerableCheckpointFailureNumber(3);
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        env.setStateBackend(new HashMapStateBackend());
        env.setParallelism(1);
        parameterTool = ParameterUtil.getParameters();
        env.getConfig().setGlobalJobParameters(parameterTool);
    }
}
