package org.example.riskCtrlSys.utils.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DateUtilsTest {

    @Test
    void testConvertLocalDateTime2String() {
        assertThat(DateUtils.convertLocalDateTime2String(LocalDateTime.of(2020, 1, 1, 0, 0, 0))).isEqualTo("2020-01-01 00:00:00");
    }

    @Test
    void testConvertString2LocalDateTime() {
        assertThat(DateUtils.convertString2LocalDateTime("str")).isEqualTo(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
    }

    @Test
    void testConvertTimestamp2LocalDateTime() {
        assertThat(DateUtils.convertTimestamp2LocalDateTime(0L)).isEqualTo(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
    }

    @Test
    void testConvertLocalDateTime2Timestamp() {
        assertThat(DateUtils.convertLocalDateTime2Timestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0))).isEqualTo(0L);
    }
}
