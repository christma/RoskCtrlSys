package org.example.RiskCtrlSys.commons.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.RiskCtrlSys.commons.exception.enums.BizExceptionInfo;

@Slf4j
public class  BizRuntimeException extends RuntimeException{

    public BizRuntimeException(BizExceptionInfo info){
        log.error(info.getExceptionMsg());
    }

}
