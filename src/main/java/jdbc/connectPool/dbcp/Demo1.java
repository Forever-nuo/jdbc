package jdbc.connectPool.dbcp;


import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;


import java.sql.Connection;
import java.sql.SQLException;

public class Demo1 {
  @Test
  public void fun() throws SQLException {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/jdbc");
    dataSource.setUsername("root");
    dataSource.setPassword("forever");

    dataSource.setMaxActive(20);
    dataSource.setMinIdle(3);
    dataSource.setMaxWait(1000);
    Connection connection = dataSource.getConnection();
    System.out.println(connection.getClass().getName());

    System.out.println(11);
  }
}
