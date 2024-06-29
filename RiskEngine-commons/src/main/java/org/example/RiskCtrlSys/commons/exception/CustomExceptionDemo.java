package org.example.RiskCtrlSys.commons.exception;

import org.example.RiskCtrlSys.commons.exception.custom.RedisException;
import org.example.RiskCtrlSys.commons.exception.enums.RedisExceptionInfo;

public class CustomExceptionDemo {
    public static void throwExceptionDemo() throws RedisException{
        throw new RedisException(RedisExceptionInfo.REDISTEMPLATE_NULL);
    }
}
