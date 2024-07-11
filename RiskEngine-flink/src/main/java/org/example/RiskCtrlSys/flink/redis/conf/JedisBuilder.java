package org.example.RiskCtrlSys.flink.redis.conf;

import redis.clients.jedis.JedisPool;

public class JedisBuilder {
    private JedisPool jedisPool = null;

    public JedisBuilder(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void close() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    public String get(String key) {
        return jedisPool.getResource().get(key);
    }
}
