package ZHILIU.JDBCTest;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author ZHI LIU
 * @date 2021-07-06
 */
public class JDBCUtils {

    /**
     * 使用Druid数据库连接池技术
     *
     */
    private static DataSource source1;
    static{
        try {
            Properties pros = new Properties();

            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");

            pros.load(is);

            source1 = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {

        Connection conn = source1.getConnection();
        return conn;
    }

    /**
     *
     * 使用dbutils.jar中提供的DbUtils工具类，实现资源的关闭
     */
    public static void closeResource(Connection conn){

        DbUtils.closeQuietly(conn);

    }

    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        JDBCUtils.closeResource(connection);

    }

}
