package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.java.utils.ParameterTool;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;

import static org.example.RiskCtrlSys.flink.utils.ParameterConstantsUtils.*;

public class ParameterUtil {
//    private static final String FILE = "flink.properties";

    public static ParameterTool getParameters() {
        InputStream inputStream = ParameterUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_BASE_FILE);
        ParameterTool parameterTool = null;
        try {
            parameterTool = ParameterTool.fromPropertiesFile(inputStream);
            String envActive = getEnvActiveValue(parameterTool);
            return ParameterTool.fromPropertiesFile(
                            Thread.currentThread()
                                    .getContextClassLoader()
                                    .getResourceAsStream(envActive)
                    )
//                .mergeWith(ParameterTool.fromArgs(args))
                    .mergeWith(ParameterTool.fromSystemProperties());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static String getEnvActiveValue(ParameterTool parameterTool) {
        String envActive = null;
        if (parameterTool.has(FLINK_ENV_ACTIVE)) {
            envActive = String.format(FLINK_ENV_FILE, parameterTool.getProperties());
        }
        return envActive;
    }
}

