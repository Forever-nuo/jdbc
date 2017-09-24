package jdbc.dbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtilsDemo {


  @Test
  public void test() throws SQLException {
    QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
    String  sql = "select * from person ";
    List<Map<String, Object>> mapList = queryRunner.query(sql, new MapListHandler());
    System.out.println(11);
  }
}
