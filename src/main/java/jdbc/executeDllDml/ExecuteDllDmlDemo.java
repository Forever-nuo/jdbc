package jdbc.executeDllDml;

import org.junit.Test;

import java.sql.*;

/**
 * 演示jdbc 执行dll 语句和 dml语句 Created by forever on 2017-9-14.
 */
public class ExecuteDllDmlDemo {

  /**
   * 获取sql语句的发送器
   */
  public Statement getStatement() {
    Statement statement = null;
    //创建连接
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?username=root&password=forever&characterEncoding=utf8");
      statement = connection.createStatement();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return statement;
  }

  /**
   * 执行dll语句
   */
  @Test
  public void testExecuteDll() throws SQLException {
    //获得sql语句发送器
    Statement statement = getStatement();
    String sql = "create table person ( name  varchar(25), age int , sex int )";
    //返回是否执行成功
    boolean success = statement.execute(sql);
  }

  /**
   * 新增数据
   */
  @Test
  public void testAddData() throws SQLException {

    //获得sql语句发送器
    Statement statement = getStatement();
    String sql = "insert into person (name,age) values ('张三',15)";
    //返回结果 影响的行数
    int rows = statement.executeUpdate(sql);
    System.out.println(rows);


  }

  /**
   * 新增数据
   */
  @Test
  public void testUpdateData() throws SQLException {
    //获得sql语句发送器
    Statement statement = getStatement();
    String sql = "update   person set name = '孙悟空' , age= 1 where age =15";
    //返回结果 影响的行数
    int rows = statement.executeUpdate(sql);
    System.out.println(rows);
  }

  /**
   * 删除数据
   */
  @Test
  public void testDeleteData() throws SQLException {
    //获得sql语句发送器
    Statement statement = getStatement();
    String sql = "delete  from person  where age =1";
    //返回结果 影响的行数
    int rows = statement.executeUpdate(sql);
    System.out.println(rows);
  }

  /**
   * 执行dql 查询语句
   */

  @Test
  public void testQueryData() throws SQLException, ClassNotFoundException {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      //建立连接
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?username=root&password=forever&characterEncoding=utf8");
      //获得sql语句发送器
      statement = connection.createStatement();
      String sql = "select * from person";
      //使用executeQuery方法 参数只能是查询语句 返回结果是结果集
      resultSet = statement.executeQuery(sql);

      //解析结果接
      while (resultSet.next()) {
        //根据getXXX()方法 参数 列的位置从1开始 或列的列名
        String name = resultSet.getString(0);
        Integer age = resultSet.getInt("age");
        System.out.println("姓名:  " + name + "年龄:  " + age);
      }
    } finally { //释放资源 后开的先释放
      if (resultSet != null) {
        resultSet.close();
      }
      if (connection != null) {
        connection.close();
      }
      if (statement != null) {
        statement.close();
      }
    }


  }


}
