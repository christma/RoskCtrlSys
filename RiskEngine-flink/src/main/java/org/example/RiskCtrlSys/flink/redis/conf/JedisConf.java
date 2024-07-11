package org.example.RiskCtrlSys.flink.redis.conf;

import org.apache.flink.api.java.utils.ParameterTool;
import org.example.RiskCtrlSys.flink.utils.ParameterUtil;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

public class JedisConf {
    public static JedisPool getRedisPool() throws IOException {
        ParameterTool parameters = ParameterUtil.getParameters("flink.properties");
        String host = parameters.get("redis.host");
        int port = Integer.parseInt(parameters.get("redis.port"));
//        System.out.println(host + ":" + port);

        return new JedisPool(host, port);

    }
//
//    public static void main(String[] args) throws IOException {
//        System.out.println(getRedisPool());
//    }
}
