package jdbc.jdbcApiDemo;

import jdbc.jdbcUtil.JdbcUtilsV1;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * PrepareStatement对象的演示
 */
public class PreparedStatementDemo {


  /**
   * PreparedStatement对象的强大之处
   * 1.防止sql攻击
   * 2.增强代码的可读性,可维护性
   * 3.提高效率
   */


  /**
   *
   */
  @Test
  public void test() {
    System.out.println(11);

  }


  /**
   * 演示sql攻击
   */
  @Test
  public void sqlAttack() throws SQLException, ClassNotFoundException {
    Class<?> dClass = Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?username=root&&password=forever");
    Statement statement = connection.createStatement();

    String userName = "zhangsan'  or  'a' = 'a";
    String password = "a' or 'a'='a";
    String sql = "select * from person where userName = '"+userName + " ' and password = '" + password +"'";
    ResultSet resultSet = statement.executeQuery(sql);
    System.out.println(resultSet.next());
  }


  /**
   * 获得PreparedStatement对象
   */
  @Test
  public void testGetPreparedStatement() throws SQLException {
    Connection connection = ConnectionDemo.getConnection();

    /**
     * 使用connection对象的prepareStatement方法  获取到PreparedStatement对象
     * 参数 : sql 模板 ? 是占位符
     */
    PreparedStatement preparedStatement = connection.prepareStatement(" select * from person where username = ? and password = ?");
    /**
     * 使用preparedStatement 对象的setXXX 方法给sql模板的占位符 赋值
     * 参数1 站位符的位置
     * 参数2 站位符的值
     */
    preparedStatement.setString(1,"zhangsan");
    preparedStatement.setString(2,"123456");
    /**
     * preparedStatement 对象的 executeQuery()  没有参数
     * 得到ResultSet 结果集对象
     */
    ResultSet resultSet = preparedStatement.executeQuery();


    System.out.println(resultSet.next());
  }


  /**
   * 存大数据
   */

  @Test
  public void testSaveBlobData() throws SQLException {
    Connection connection = JdbcUtilsV1.getConnection();
    String sql = "insert into person (mp3) values (?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream ("下雨了.mp3");

    //使用setBinaryStream 参数的值是一个流对象
    preparedStatement.setBinaryStream(1,resourceAsStream);
    preparedStatement.executeUpdate();
  }


  /**
   * 获得数据
   * @throws SQLException
   * @throws IOException
   */
  @Test
  public void testGetBlobData() throws SQLException, IOException {
    Connection connection = JdbcUtilsV1.getConnection();
    String sql = "select MP3 from person where id =5";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    ResultSet resultSet = preparedStatement.executeQuery();
    InputStream inputStream =null;
    if(resultSet.next()){
      //使用getBinaryStream 取大数据 返回的是一个读取流
      inputStream= resultSet.getBinaryStream(1);
    }

    OutputStream outputStream = null;
    try {
      outputStream= new  FileOutputStream("D:/a.mp3");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IOUtils.copy(inputStream,outputStream);
  }

  /**
   * 演示 Statement批处理
   */

  @Test
  public void testExecuteBatch() throws SQLException {
    Connection connection = JdbcUtilsV1.getConnection();
    Statement statement = connection.createStatement();
    String sql1 = "insert into person (username) values (\"王宇\")";
    String sql2 = "insert into person (password) values (\"1234567\")";
    String sql3 = "insert into person (sex) values (\"1\")";
    statement.addBatch(sql1);
    statement.addBatch(sql2);
    statement.addBatch(sql3);
    statement.executeBatch();
  }

  /**
   * 演示 Statement批处理
   */

  @Test
  public void testExecuteBatch1() throws SQLException {
    Connection connection = JdbcUtilsV1.getConnection();
    String sql = "insert into person (username,name,password) values (?,?,?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    for (int i = 0; i < 5000; i++) {
      preparedStatement.setString(1,"a_"+i);
      preparedStatement.setString(2,"b_"+i);
      preparedStatement.setString(3,"p_"+i);
      preparedStatement.addBatch();
    }
    preparedStatement.executeBatch();

  }

}


