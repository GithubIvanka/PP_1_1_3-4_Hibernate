package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
        dao.saveUser("Ivan", "Ivanka", (byte) 25);
        dao.saveUser("Maria", "Novikova", (byte) 23);
        dao.saveUser("Petr", "Petrov", (byte) 30);
        dao.saveUser("Aleksandra", "Saveleva", (byte) 19);
        List<User> users = dao.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        dao.cleanUsersTable();
        dao.dropUsersTable();

    }
}
