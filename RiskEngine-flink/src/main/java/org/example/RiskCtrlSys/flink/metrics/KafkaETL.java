package org.example.RiskCtrlSys.flink.metrics;

import org.apache.flink.api.common.functions.MapFunction;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RIskCtrlSys.modle.KafkaMessagePO;

public class KafkaETL implements MapFunction<KafkaMessagePO, EventPO> {

    @Override
    public EventPO map(KafkaMessagePO kafkaMessagePO) throws Exception {
        String user_id = kafkaMessagePO.getUser_id_str();
        String event_time = kafkaMessagePO.getEvent_time();
        String behavior_name = kafkaMessagePO.getBehavior_name();
        String event_target_id = kafkaMessagePO.getEvent_target_id();
        return new EventPO(user_id, event_time, behavior_name, event_target_id);
    }
}
