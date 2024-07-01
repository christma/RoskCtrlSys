package org.example.riskCtrlSys.utils.redis;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Appendable.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @DisplayName("test setString")
    @Order(1)
    @Test
    void setString() {
        redisUtil.setString("test:set","this is test setString test");
    }

    @DisplayName("test getString")
    @Order(2)
    @Test
    void getString() {
        String value = (String)redisUtil.getString("test:set");
        System.out.println(value);
    }

    @DisplayName("test hashSet")
    @Order(3)
    @Test
    void hashSet() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        redisUtil.hashSet("test:hashSet",map);
    }

    @DisplayName("test hashGet")
    @Order(4)
    @Test
    void hashGet() {
        String value = (String) redisUtil.hashGet("test:hashGet","key1");
        System.out.println(value);
    }
}