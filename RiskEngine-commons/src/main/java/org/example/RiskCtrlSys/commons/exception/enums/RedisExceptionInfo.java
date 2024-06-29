package org.example.RiskCtrlSys.commons.exception.enums;

import lombok.Getter;
import org.example.RiskCtrlSys.commons.exception.BizRuntimeException;

@Getter
public enum RedisExceptionInfo  implements BizExceptionInfo {

    REDISTEMPLATE_NULL("-300", "Redis对象不能为null");

    private String exceptionCode;
    private String exceptionMsg;

    RedisExceptionInfo(String exceptionCode, String exceptionMsg) {
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }
}
