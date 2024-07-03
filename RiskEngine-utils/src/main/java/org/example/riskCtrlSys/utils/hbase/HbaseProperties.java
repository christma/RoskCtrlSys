package org.example.riskCtrlSys.utils.hbase;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "hbase.conf")
public class HbaseProperties {
    private Map<String,String> configMap;
}
