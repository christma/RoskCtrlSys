package org.example.RIskCtrlSys.modle;


import lombok.Data;

@Data
public class EventPO {

    private String user_id;
    private String event_time;
    private String event_behavior_name;
    private String event_target_name;


    public EventPO() {
    }

    public EventPO(String user_id, String event_time, String event_behavior_name, String event_target_name) {
        this.user_id = user_id;
        this.event_time = event_time;
        this.event_behavior_name = event_behavior_name;
        this.event_target_name = event_target_name;
    }
}
