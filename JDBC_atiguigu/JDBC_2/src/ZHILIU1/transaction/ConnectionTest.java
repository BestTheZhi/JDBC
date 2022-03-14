package ZHILIU1.transaction;


import ZHILIU1.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author ZHI LIU
 * @date 2021-07-04
 */
public class ConnectionTest {

    @Test
    public void testGetConnection() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);

    }


}
