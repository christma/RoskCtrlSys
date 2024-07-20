package org.example.RIskCtrlSys.modle;


import lombok.Data;

@Data
public class EventPO {

    private Integer user_id_int;
    private String event_time;
    private String event_behavior_name;
    private String event_target_name;
    private MetricsConfPO MetricsConfPO;


    public EventPO() {
    }

    public EventPO(Integer user_id_int, String event_time, String event_behavior_name, String event_target_name) {
        this.user_id_int = user_id_int;
        this.event_time = event_time;
        this.event_behavior_name = event_behavior_name;
        this.event_target_name = event_target_name;
    }

    public EventPO(Integer user_id_int, String event_time, String event_behavior_name, String event_target_name, org.example.RIskCtrlSys.modle.MetricsConfPO metricsConfPO) {
        this.user_id_int = user_id_int;
        this.event_time = event_time;
        this.event_behavior_name = event_behavior_name;
        this.event_target_name = event_target_name;
        MetricsConfPO = metricsConfPO;
    }
}
