package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javapp";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "11082000";

    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) { // т.е. если SessionFactory нет, то
            try {
                Properties properties= new Properties(); // Чтобы вручную конфигурировать создание SessionFactory,
                // мы пишем свойства. Properties устроен, как Map - содержит пары "ключ-значение".
                // Можно переместить их в отдельный файл. Либо можно написать отдельный класс с конфигурациями.

                //Вносим свойства. Интерфейс Environment - это окружение, в котором приложение запущено.
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver"); // Драйвер д/БД
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/javapp"); // Ссылка к БД
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "11082000");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect"); // Тип СУБД
                properties.put(Environment.SHOW_SQL, "true"); // Hibernate будет дублировать в консоль все запросы, которые выполняет
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread"); //класс текущей сессии???
                properties.put(Environment.HBM2DDL_AUTO, "create-drop"); //Это свойство, обеспечивающее проверку
                // или экспорт ddl схемы при создании SessionFactory


                sessionFactory = new Configuration() //Создаем экземпляр Configuration
                        .addProperties(properties) //или setProperties() ? // Вносим в эту конфигурацию свойства
                        .addAnnotatedClass(User.class) // Указываем, какой Entity-класс использовать
                        .buildSessionFactory(); // Создаем SessionFactory

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }



    public static Connection getConnection()  {

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection ok");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Connection ERROR");
        }
        return connection;
    }


}
