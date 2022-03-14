package ZHILIU5.blob;

import ZHILIU3.bean.Customer;
import ZHILIU3.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @author ZHI LIU
 * @date 2021-07-04
 *
 * 使用PreparedStatement操作Blob类型的数据
 *
 * 还有文件相对路径测试问题
 * 如果是在main方法里使用，则会在当前Project目录下生成此文件。
 * 如果是在Junit测试单元@test的测试方法里使用则会在当前module下生成此文件。
 *
 */
public class BlobTest {

    //添加一条数据，包括blob类型的数据
    @Test
    public void testInsert() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into customers(name,email,birth,photo)values(?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1,"小志");
        ps.setObject(2,"TheZhi@qq.com");
        ps.setObject(3,"2002-06-08");

        //相对路径
        //FileInputStream is = new FileInputStream(new File("C:\\Code\\JDBC\\JDBC_1\\Uzi.jpg"));
        FileInputStream is = new FileInputStream(new File("Uzi.jpg"));
        ps.setBlob(4,is);

        ps.execute();

        JDBCUtils.closeResource(conn,ps);

    }

    //简单的测试
    @Test
    public void testDelete() throws Exception {
        String sql = "update customers set id = ? where name = ?";

        JDBCUtils.update(sql,21,"小志");


    }

    //查询数据表customers中Blob类型的字段
    @Test
    public void testQuery(){
        Connection conn = null;
        PreparedStatement ps = null;
        InputStream is = null;
        FileOutputStream fos = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth,photo from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 22);
            rs = ps.executeQuery();
            if(rs.next()){
                //			方式一：
                //			int id = rs.getInt(1);
                //			String name = rs.getString(2);
                //			String email = rs.getString(3);
                //			Date birth = rs.getDate(4);
                //方式二：
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");

                Customer cust = new Customer(id, name, email, birth);
                System.out.println(cust);

                //将Blob类型的字段下载下来，以文件的方式保存在本地
                Blob photo = rs.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("ZhiLiu.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1){
                    fos.write(buffer, 0, len);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            JDBCUtils.closeResource(conn, ps, rs);
        }


    }




    /*
    * 如果是在main方法里使用，则会在当前Project目录下生成此文件。
    * 如果是在Junit测试单元@test的测试方法里使用则会在当前module下生成此文件。
    *
    */
    @Test
    public void testPath() throws FileNotFoundException {
        FileInputStream is = new FileInputStream(new File("Uzi.jpg"));

    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream is = new FileInputStream(new File("JDBC_1/Uzi.jpg"));
    }


}
