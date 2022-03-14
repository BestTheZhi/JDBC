package ZHILIU.JDBCTest;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ZHI LIU
 * @date 2021-07-06
 */
public class JDBCTest {


    @Test
    public void getConnectionTest() throws SQLException {

        Connection conn = JDBCUtils.getConnection();

        System.out.println(conn);

    }

}
