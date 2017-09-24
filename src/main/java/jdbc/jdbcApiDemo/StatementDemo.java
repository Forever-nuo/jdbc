package jdbc.jdbcApiDemo;

import java.sql.*;

import org.junit.Test;

/**
 * 结果集对象的演示
 */
public class StatementDemo {

  /**
   * 获取结果集对象 方式1
   * <p>
   * 通过Connection对象的createStatement()对象
   * 改方法产生的Statement对象
   * 不滚动  上下滚动
   * 不敏感  结果集   数据库变动 结果集跟着变动
   * 不更新  修改结果集 数据库跟着变动
   */
  @Test
  public void getStatementMethod1() throws ClassNotFoundException, SQLException {
    Class<?> dClass = Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?username=root&&password=forever");
    Statement statement = connection.createStatement();
  }


  /**
   * 获取结果集对象 方式2
   */

  @Test
  public void getStatementMethod2() throws ClassNotFoundException, SQLException {
    Class<?> dClass = Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?username=root&&password=forever");
    /**
     * 参数1
     * ResultSet.TYPE_FORWARD_ONLY      只向前
     * ResultSet.TYPE_SCROLL_INSENSITIVE  可滚动 不敏感
     * ResultSet.TYPE_SCROLL_SENSITIVE  可滚动敏感
     * 参数2
     * ResultSet.CONCUR_READ_ONLY 结果集是只读的
     * ResultSet.CONCUR_UPDATABLE 结果集是可更新的
     */
    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
  }


}
