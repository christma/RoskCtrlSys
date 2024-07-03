package org.example.riskCtrlSys.utils.hbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HbaseProperties.class)
public class HbaseConf {

    @Autowired
    private HbaseProperties hbaseProperties;

}
