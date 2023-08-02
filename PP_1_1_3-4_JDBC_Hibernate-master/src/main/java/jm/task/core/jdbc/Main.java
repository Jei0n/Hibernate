package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

//        UserServiceImpl userServiceImpl = new UserServiceImpl();
//        try {
//            userServiceImpl.createUsersTable();  //Создаем таблицу, применяя метод класса UserServiceImpl
//            System.out.println("Таблица создана");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        List <User> users2 = new ArrayList<User>(); // Создали лист юзеров
//
//        users2.add (new User ("Ola", "Katina", (byte)32)); //Заполнили лист юзеров
//        users2.add (new User("Kolya", "Ivanov", (byte)23)); //Заполнили лист юзеров
//        users2.add (new User("Lena", "Sorina", (byte)81)); //Заполнили лист юзеров
//        users2.add (new User("Petya", "Petrov", (byte)17)); //Заполнили лист юзеров
//
//        try {
//            for (User user : users2) { //Перебрать каждого юзера из нашего списка юзеров
//                userServiceImpl.saveUser(user.getName(), user.getLastName(), (byte) user.getAge()); // Сохранить каждого юзера
//                System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            List<User> tableOfUsers= userServiceImpl.getAllUsers(); //Получаем список всех юзеров, применяя метод класса userServiceImpl и сохраняя результат метода в список
//            System.out.println("Список получен");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            userServiceImpl.cleanUsersTable(); // чистим таблицу, т.е. удаляем из нее инфу
//            System.out.println("Таблица очищена");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            userServiceImpl.dropUsersTable(); // Удаляем саму таблицу
//            System.out.println("Таблица удалена");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        Util.getConnection();
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

//        userDao.removeUserById(1);
//        List<User> allUsers = userDao.getAllUsers();
//        System.out.println(allUsers.toString());
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
    }
}
