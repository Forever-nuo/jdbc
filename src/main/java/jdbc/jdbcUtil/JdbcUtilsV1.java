package jdbc.jdbcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

/**
 * v1.0
 * @author cxf
 *
 */
public class JdbcUtilsV1 {
	private static Properties props = null;
	
	// 只在JdbcUtils类被加载时执行一次！
	static {
		// 给props进行初始化，即加载dbconfig.properties文件到props对象中
		try {
			InputStream in = JdbcUtilsV1.class.getClassLoader()
					.getResourceAsStream("dbconfig.properties");
			props = new Properties();
			props.load(in);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		// 加载驱动类
		try {
			Class.forName(props.getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 获取连接!
	public static Connection getConnection()  {
		// 得到Connection
    Connection connection =null;
    try {
      connection= DriverManager.getConnection(props.getProperty("url"),
              props.getProperty("username"),
              props.getProperty("password"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }
}
