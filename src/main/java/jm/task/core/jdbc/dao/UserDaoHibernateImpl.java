package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
//    private Transaction transaction = null;
    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NOT NULL," +
                "`lastName` VARCHAR(45) NULL," +
                "`age` INT NULL," +
                "PRIMARY KEY (`id`)," +
                "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
            session.beginTransaction();
            session.createSQLQuery(query).executeUpdate();
            session.getTransaction().commit();
//            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
//            transaction.commit();
            session.getTransaction().commit();
        } catch (Exception e) {
//            if (transaction !=null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
//            transaction.commit();
            System.out.printf("User с именем %s добавлен в базу данных.\n", name);
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
        }
    }
    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
//            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {
//        List<User> allUsers = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
//            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
        }
    }
}
