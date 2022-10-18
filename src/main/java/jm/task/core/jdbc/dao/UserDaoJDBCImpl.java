package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NULL," +
                    "`age` INT NULL," +
                    "PRIMARY KEY (`id`)," +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dropUsersTable() {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String query = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String query = "INSERT INTO users (name, lastName, age) values (" +
                    "'" + name + "'," +
                    "'" + lastName + "'," +
                    age + ");";
            statement.executeUpdate(query);
            System.out.printf("User с именем %s добавлен в базу данных.\n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeUserById(long id) {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String query = "DELETE IF EXIST FROM simplehr.users WHERE id = " + id + ";";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try (Statement statement = Util.getMySQLConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public void cleanUsersTable() {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String query = "DELETE FROM users;";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
