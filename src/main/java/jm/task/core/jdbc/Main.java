package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

//        UserDao hibernate = new UserDaoHibernateImpl();
//        UserDao jdbc = new UserDaoJDBCImpl();

        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Ivan", "Ivanka", (byte) 25);
        us.saveUser("Maria", "Novikova", (byte) 23);
        us.saveUser("Petr", "Petrov", (byte) 30);
        us.saveUser("Aleksandra", "Saveleva", (byte) 19);
        List<User> users = us.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        us.cleanUsersTable();
        us.dropUsersTable();
        Util.closeSessionFactory();
    }
}
