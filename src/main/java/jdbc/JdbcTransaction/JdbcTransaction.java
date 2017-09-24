package jdbc.JdbcTransaction;

import jdbc.jdbcUtil.JdbcUtilsV1;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransaction {


  /**
   * 没有开启事务的转账
   */
  @Test
  public void testDemo1() throws SQLException {


    Connection connection = JdbcUtilsV1.getConnection();
    //张三-100
    String sql_zs = "update account set balance = balance -100 where `NAME` = 'zs' ";
    PreparedStatement psmt1 = connection.prepareStatement(sql_zs);
    psmt1.executeUpdate();

    int x = 1 / 0;

    //李四+100
    String sql_ls = "update account set balance = balance +100 where `NAME` = 'li' ";
    PreparedStatement psmt2 = connection.prepareStatement(sql_ls);
    psmt2.executeUpdate();


  }


  /**
   * 开启事务的转账
   */
  @Test
  public void testDemo2() throws SQLException {
    Connection connection = JdbcUtilsV1.getConnection();
    try {
      //开启事务  默认自动提交 true
      connection.setAutoCommit(false);
      //张三-100
      String sql_zs = "update account set balance = balance -100 where `NAME` = 'zs' ";
      PreparedStatement psmt1 = connection.prepareStatement(sql_zs);
      psmt1.executeUpdate();

      int x = 1 / 0;

      //李四+100
      String sql_ls = "update account set balance = balance +100 where `NAME` = 'li' ";
      PreparedStatement psmt2 = connection.prepareStatement(sql_ls);
      psmt2.executeUpdate();
    } catch (Exception e) {
      //发生异常进行回滚
      connection.rollback();
    } finally {
      //最终commit
      connection.commit();
    }


  }


}
