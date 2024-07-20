package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.java.utils.ParameterTool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ParameterUtilTest.class)
class ParameterUtilTest {

    @Test
    void testGetParameters() throws Exception {
        ParameterTool parameters = ParameterUtil.getParameters();
        assertEquals("ImoocTest", parameters.get("imooc"));
    }


}
