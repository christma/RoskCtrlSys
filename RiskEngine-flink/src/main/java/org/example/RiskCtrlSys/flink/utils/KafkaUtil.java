package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.connector.kafka.source.reader.deserializer.KafkaRecordDeserializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.RIskCtrlSys.modle.KafkaMessagePO;
import org.example.RiskCtrlSys.flink.kafka.MyDeserializationSchema;

import java.io.IOException;

import static org.apache.flink.connector.kafka.sink.KafkaSink.builder;

public class KafkaUtil {
    public static KafkaSource<KafkaMessagePO> KAFKA_SOURCE = null;

    static {
        ParameterTool parameterTool = null;

        try {
            parameterTool = ParameterUtil.getParameters("flink.properties");
            String brokers = parameterTool.get("kafka.brokers");
            String topic = parameterTool.get("kafka.topic");
            String groupId = parameterTool.get("kafka.groupId");
            KAFKA_SOURCE = KafkaSource.<KafkaMessagePO>builder()
                    .setBootstrapServers(brokers)
                    .setTopics(topic)
                    .setGroupId(groupId)
                    .setStartingOffsets(OffsetsInitializer.earliest())
                    .setDeserializer(KafkaRecordDeserializationSchema.of(new MyDeserializationSchema()))
//                    .setValueOnlyDeserializer(new SimpleStringSchema())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static DataStream<KafkaMessagePO> read(StreamExecutionEnvironment env) {
        return env.fromSource(KAFKA_SOURCE, WatermarkStrategy.noWatermarks(), "kafka source");

    }
}
