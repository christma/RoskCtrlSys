package org.example.RiskCtrlSys.flink.redis.conf;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JedisConfTest {

    @Test
    void testGetRedisPool() throws Exception {
        // Setup
        // Run the test
        final JedisPool result = JedisConf.getRedisPool();

        // Verify the results
    }

    @Test
    void testGetRedisPool_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy(JedisConf::getRedisPool).isInstanceOf(IOException.class);
    }
}
