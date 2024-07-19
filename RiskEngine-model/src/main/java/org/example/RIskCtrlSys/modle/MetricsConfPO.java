package org.example.RIskCtrlSys.modle;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
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

    public MetricsConfPO() {
    }

    public MetricsConfPO(String metric_name, String metric_code, String metric_agg_type, String metric_store, String scene, String event, String main_dim, String aggregation, String is_enable, String datasource, String window_size, String window_step, String window_type, String flink_filter, String flink_keyby, String flink_watermark) {
        this.metric_name = metric_name;
        this.metric_code = metric_code;
        this.metric_agg_type = metric_agg_type;
        this.metric_store = metric_store;
        this.scene = scene;
        this.event = event;
        this.main_dim = main_dim;
        this.aggregation = aggregation;
        this.is_enable = is_enable;
        this.datasource = datasource;
        this.window_size = window_size;
        this.window_step = window_step;
        this.window_type = window_type;
        this.flink_filter = flink_filter;
        this.flink_keyby = flink_keyby;
        this.flink_watermark = flink_watermark;
    }

}
