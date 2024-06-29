package org.example.RiskCtrlSys.commons.exception.custom;

import org.example.RiskCtrlSys.commons.exception.BizRuntimeException;
import org.example.RiskCtrlSys.commons.exception.enums.BizExceptionInfo;

public class RedisException extends BizRuntimeException {
    public RedisException(BizExceptionInfo info) {
        super(info);
    }
}
