package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.java.utils.ParameterTool;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;

public class ParameterUtil {
    private static final String FILE = "flink.properties";

    public static ParameterTool getParameters() throws IOException {
        InputStream inputStream = ParameterUtil.class.getClassLoader().getResourceAsStream(FILE);

        return ParameterTool.fromPropertiesFile(inputStream);
    }
}

