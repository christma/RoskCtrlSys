package org.example.RiskCtrlSys.commons.exception;

import org.example.RiskCtrlSys.commons.exception.custom.RedisException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomExceptionDemoTest {

    @Test
    void testThrowExceptionDemo() {
        assertThatThrownBy(() -> CustomExceptionDemo.throwExceptionDemo()).isInstanceOf(RedisException.class);
    }
}
