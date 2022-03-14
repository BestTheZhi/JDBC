package ZHILIU4.exer;

import ZHILIU3.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author ZHI LIU
 * @date 2021-07-04
 *
 * JDBC 练习
 *
 */
public class Exer1Test {

    @Test
    public void testInsert(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name = input.next();
        System.out.println("请输入邮箱：");
        String email = input.next();
        System.out.println("请输入生日：");
        String birthday = input.next();
        //birth是date类型  String '2021-09-08' -> date

        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        int insertCount = update(sql,name,email,birthday);
        if(insertCount > 0){
            System.out.println("添加成功");
        } else{
            System.out.println("添加失败");
        }

    }

    //通用的增删改操作
    public int update(String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //ps.execute();
            /*
            * executeUpdate()
            * 返回执行语句影响的行数
            */
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }
        return 0;
    }

}
