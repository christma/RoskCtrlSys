package org.example.RiskCtrlSys.flink.utils;

import org.apache.flink.api.java.utils.ParameterTool;

import java.io.IOException;
import java.sql.*;

public class MySQLJDBCUtil {
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static String database = null;
    private static PreparedStatement preparedStatement = null;
    private static Connection conn;

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


    public static PreparedStatement init(String sql) throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
        return conn.prepareStatement(sql);

    }

    public static void close() throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (conn != null) {
            conn.close();
        }

    }

    public static ResultSet executeQuery(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }


}
