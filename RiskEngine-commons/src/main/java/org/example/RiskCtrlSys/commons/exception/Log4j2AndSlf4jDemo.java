package org.example.RiskCtrlSys.commons.exception;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Slf4j
public class Log4j2AndSlf4jDemo {

    private static final Logger  logger = LogManager.getLogger(Log4j2AndSlf4jDemo.class);

    public static void slf4jOutput() {
        log.warn("This is a slf4j demo");

    }


    public static void log4j2Output() {
        logger.error("this is an error message");
        logger.info("this is an info message");
    }
}
