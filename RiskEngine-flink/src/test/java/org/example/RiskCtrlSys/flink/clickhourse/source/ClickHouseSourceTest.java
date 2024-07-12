package org.example.RiskCtrlSys.flink.clickhourse.source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.example.RIskCtrlSys.modle.ClickHousePO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ClickHouseSourceTest {

    private ClickHouseSource clickHouseSourceUnderTest;

    @BeforeEach
    void setUp() {

        clickHouseSourceUnderTest = new ClickHouseSource("jdbc:clickhouse://localhost:8123/default", "select name from default.rods");
    }

    @Test
    void testRun() throws Exception {

        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);
        DataStreamSource<ClickHousePO> source = environment.addSource(clickHouseSourceUnderTest);
        source.print();
        environment.execute();

        // Setup
//        final SourceFunction.SourceContext<ClickHousePO> output = null;

        // Run the test
//        clickHouseSourceUnderTest.run(output);

        // Verify the results
    }

    @Test
    void testRun_ThrowsException() {
        // Setup
        final SourceFunction.SourceContext<ClickHousePO> output = null;

        // Run the test
        assertThatThrownBy(() -> clickHouseSourceUnderTest.run(output)).isInstanceOf(Exception.class);
    }

    @Test
    void testCancel() {
        clickHouseSourceUnderTest.cancel();
    }
}
