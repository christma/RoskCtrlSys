package org.example.RiskCtrlSys.flink.redis.source;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.example.RIskCtrlSys.modle.RedisPO;
import org.example.RiskCtrlSys.flink.redis.conf.JedisBuilder;
import org.example.RiskCtrlSys.flink.redis.conf.JedisConf;
import org.example.RiskCtrlSys.flink.redis.conf.RedisCommond;
import redis.clients.jedis.JedisPool;

public class RedisSource extends RichParallelSourceFunction<RedisPO> {


    private volatile boolean isRunning = true;
    private JedisBuilder redisBuilder;
    private RedisCommond redisCommond;
    private String key;

    public RedisSource(RedisCommond redisCommond, String key) {
        this.key = key;
        this.redisCommond = redisCommond;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        JedisPool redisPool = JedisConf.getRedisPool();
        redisBuilder = new JedisBuilder(redisPool);
    }

    @Override
    public void run(SourceContext<RedisPO> output) throws Exception {

        String data = null;
//        while (isRunning) {
        switch (redisCommond.getRedisDataType()) {
            case STRING:
                data = redisBuilder.get(key);
        }


        output.collect(new RedisPO(data));

//        }
    }

    @Override
    public void cancel() {
        this.isRunning = false;

    }
}
