package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String hostName = "localhost";
    private static String port = "3306";
    private static String dbName = "simplehr";
    private static String userName = "root";
    private static String password = "myPass123";
    private static String URL = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName;

    // Connection to MySQL
    public static Connection getMySQLConnection() throws SQLException, ClassCastException {
        return DriverManager.getConnection(URL, userName, password);
    }
}
