package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.SqlDialect;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.io.IOException;

public class MySQLUtil {
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static String database = null;

    static {
        try {
            ParameterTool parameterTool = ParameterUtil.getParameters("flink.properties");
            url = parameterTool.get("mysql.url");
            username = parameterTool.get("mysql.username");
            password = parameterTool.get("mysql.password");
            database = parameterTool.get("mysql.database");
            System.out.println(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static DataStream<Row> readMySQLWithTableOrSQLAPI(StreamExecutionEnvironment env) throws Exception {

        EnvironmentSettings environmentSettings = EnvironmentSettings.newInstance().inStreamingMode().build();

        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env, environmentSettings);

        tableEnvironment.getConfig().setSqlDialect(SqlDialect.DEFAULT);

        String DDL = "create table mysql_source(" +
                "id int," +
                "name string," +
                "primary key (id) not enforced" +
                ")with(" +
                "'connector'='jdbc'," +
                "'url'='" + url + "'," +
                "'username'='" + username + "'," +
                "'password'='" + password + "'," +
                "'table-name'='" + database + "'" +
                ")";

        tableEnvironment.executeSql(DDL);

        Table table = tableEnvironment.sqlQuery("select * from mysql_source");

        return tableEnvironment.toDataStream(table, Row.class);


    }


//    public static void main(String[] args) throws Exception {
//
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//
//        EnvironmentSettings environmentSettings = EnvironmentSettings.newInstance().inStreamingMode().build();
//
//        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env,environmentSettings);
//
//        tableEnvironment.getConfig().setSqlDialect(SqlDialect.DEFAULT);
//
//        String DDL = "create table mysql_source(" +
//                "id int," +
//                "name string," +
//                "primary key (id) not enforced" +
//                ")with(" +
//                "'connector'='jdbc'," +
//                "'url'='" + url + "'," +
//                "'username'='" + username + "'," +
//                "'password'='" + password + "'," +
//                "'table-name'='" + database + "'" +
//                ")";
//
//        tableEnvironment.executeSql(DDL);
//
//        Table table = tableEnvironment.sqlQuery("select * from mysql_source");
//
//        DataStream<Row> dataStream = tableEnvironment.toDataStream(table, Row.class);
//        dataStream.print();
//
//        env.execute();
//    }

}
