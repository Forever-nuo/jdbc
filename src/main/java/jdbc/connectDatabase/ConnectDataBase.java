package jdbc.connectDatabase;

import com.mysql.jdbc.Driver;
import jdbc.jdbcUtil.JdbcUtilsV1;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 演示连接数据库的3种方式 Created by forever on 2017-9-13.
 */
public class ConnectDataBase {

  /**
   * 连接数据库方式1
   */
  @Test
  public void testMethod1() throws SQLException {
    //创建一个驱动类
    Driver driver = new Driver();

    //Connect方法的参数
    String url = "jdbc:mysql://localhost:3306/jdbc&characterEncoding=utf8";
    Properties info = new Properties();
    info.setProperty("username", "root");
    info.setProperty("password", "forever");

    //使用驱动类获得连接对象的connect 方法获取Connection对象
    Connection connect = driver
      .connect(url, info);
  }

  /**
   * 连接数据库方式2 使用DriverManager 驱动管家类
   */
  @Test
  public void testMethod2() throws SQLException {
    //使用驱动管家 注册 驱动列
    DriverManager.registerDriver(new Driver());
    //使用驱动管家的getConnection 方法  获取连接对象
    Connection connection = DriverManager
      .getConnection("jdbc:mysql://localhost:3306/jdbc?username=root&password=forever");
  }

  /**
   * 连接数据库方式2 使用DriverManager 驱动管家类
   */
  @Test
  public void test()
    throws ClassNotFoundException, SQLException {
    Connection connection1 = JdbcUtilsV1.getConnection();

    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc");
  }
}
