package jdbc.jdbcApiDemo;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * jdbc 的Connection对象演示
 */
public class ConnectionDemo {

  @Test
  public void test() {
    System.out.println(11);
  }


  public static Connection getConnection() {



    Connection connection = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?username=root&&password=forever");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return connection;
  }

}
