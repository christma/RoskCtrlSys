package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.example.RIskCtrlSys.modle.RedisPO;
import scala.Tuple2;

public class RedisSource extends RichParallelSourceFunction<RedisPO> {


    private volatile boolean isRunning = true;
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
    }

    @Override
    public void run(SourceContext<RedisPO> output) throws Exception {
        while (isRunning){
            RedisPO redisPO = new RedisPO();
            output.collect(redisPO);

        }
    }

    @Override
    public void cancel() {
        this.isRunning = false;

    }
}
