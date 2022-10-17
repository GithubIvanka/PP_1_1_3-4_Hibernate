package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String hostName = "localhost";
    private static String port = "3306";
    private static String dbName = "simplehr";
    private static String userName = "root";
    private static String password = "myPass123";
    private static String URL = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String dialect = "org.hibernate.dialect.MySQL5Dialect";

    private static SessionFactory sessionFactory;

    // Connection to MySQL for JDBC
    public static Connection getMySQLConnection() throws SQLException, ClassCastException {
        return DriverManager.getConnection(URL, userName, password);
    }

    // Utility method to return SessionFactory for Hibernate
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // Create properties
            Properties properties = new Properties();
            properties.put(Environment.DRIVER,driver);
            properties.put(Environment.URL, URL);
            properties.put(Environment.USER, userName);
            properties.put(Environment.PASS, password);
            properties.put(Environment.DIALECT, dialect);
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "update");

            // Create configuration
            Configuration configuration = new Configuration().setProperties(properties).addAnnotatedClass(User.class);

            // Create ServiceRegistry
//            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(config.getProperties())
//                    .build();

//            sessionFactory = config.buildSessionFactory(serviceRegistry);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
