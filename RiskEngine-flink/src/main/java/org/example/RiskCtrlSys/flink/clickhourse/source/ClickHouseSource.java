package org.example.RiskCtrlSys.flink.clickhourse.source;

import com.clickhouse.jdbc.ClickHouseConnection;
import com.clickhouse.jdbc.ClickHouseDataSource;
import com.clickhouse.jdbc.ClickHouseStatement;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.example.RIskCtrlSys.modle.ClickHousePO;

import java.sql.ResultSet;
import java.util.Properties;

public class ClickHouseSource implements SourceFunction<ClickHousePO> {

    private String URL;
    private String SQL;

    public ClickHouseSource(String URL, String SQL) {
        this.URL = URL;
        this.SQL = SQL;
    }


    @Override
    public void run(SourceContext<ClickHousePO> output) throws Exception {

        Properties properties = new Properties();
        ClickHouseDataSource source = new ClickHouseDataSource(URL, properties);

        try (ClickHouseConnection conn = source.getConnection()) {
            ClickHouseStatement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                output.collect(new ClickHousePO(name));
            }

        }
    }

    @Override
    public void cancel() {

    }

    public static void main(String[] args) {
        ClickHouseSource clickHouseSource = new ClickHouseSource("jdbc:clickhouse://localhost:8123/default", "select name from default.rods");
//        clickHouseSource.run("");
    }
}
