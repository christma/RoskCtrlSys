package org.example.RiskCtrlSys.flink.kafka;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.RIskCtrlSys.modle.KafkaMessagePO;
import org.example.riskCtrlSys.utils.json.JsonUtils;

import java.util.Arrays;

public class MyDeserializationSchema implements KafkaDeserializationSchema<KafkaMessagePO> {

    private static final String ENCODING = "UTF-8";

    @Override
    public boolean isEndOfStream(KafkaMessagePO kafkaMessagePO) {
        return false;
    }

    @Override
    public KafkaMessagePO deserialize(ConsumerRecord<byte[], byte[]> consumerRecord) throws Exception {
        byte[] bytes = consumerRecord.value();
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String value = new String(consumerRecord.value(), ENCODING);
        if (JsonUtils.isValidJson(value)) {
            return JsonUtils.jsonStr2Obj(value, KafkaMessagePO.class);
        } else {
            return null;
        }
    }

    @Override
    public TypeInformation<KafkaMessagePO> getProducedType() {
        return TypeInformation.of(KafkaMessagePO.class);
    }
}
