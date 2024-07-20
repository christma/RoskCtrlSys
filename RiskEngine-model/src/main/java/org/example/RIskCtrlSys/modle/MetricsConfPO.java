package org.example.RIskCtrlSys.modle;


import lombok.Data;


@Data
public class MetricsConfPO {
    private String metric_name;
    private String metric_code;
    private String metric_agg_type;
    private String metric_store;
    private String scene;
    private String event;
    private String main_dim;
    private String aggregation;
    private String is_enable;
    private String datasource;
    private String window_size;
    private String window_step;
    private String window_type;
    private String flink_filter;
    private String flink_keyby;
    private String flink_watermark;
    private String window_day;
    private String window_start_time;
    private String window_end_time;
    private String acc_aggregate;


}
