package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    String sqlCommand;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        sqlCommand = "CREATE TABLE IF NOT EXISTS User" +
                "(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(45) ," +
                "lastname VARCHAR(45) ," +
                "age TINYINT(10) " +
                ")";
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()  ) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    public void dropUsersTable() {
        sqlCommand = "DROP TABLE IF EXISTS User";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("При тестировании удаления таблицы произошло исключение\n" + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " был добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Во время тестирования сохранения пользователя произошло исключение\n" + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM User WHERE ID = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("При тестировании удаления пользователя по id произошло исключение\n" + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User")) {
            ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM User");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("При попытке достать всех пользователей из базы данных произошло исключение\n" + e.getMessage());
        }
        return list;

    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE User");
        } catch (SQLException e) {
            System.out.println("При тестировании очистки таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }
}
