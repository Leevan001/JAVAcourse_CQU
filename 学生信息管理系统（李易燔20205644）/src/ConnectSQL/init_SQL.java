package ConnectSQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class init_SQL {
    private String dbUrl="jdbc:mysql://localhost:3306/system";//数据库地址
    private String dbUserName="root";//用户名
    private String dbPassword="123";//密码
    private String jdbcName="com.mysql.cj.jdbc.Driver";//驱动器


    //获取数据库连接
    public Connection getCon()throws Exception{
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }
    public void closeCon(Connection con)throws Exception{
        if(con!=null){
            con.close();
        }
    }

    //数据库连接测试程序
/*
    public static void main(String[] args) {
        init_SQL dbUtil=new init_SQL();
        try{
            dbUtil.getCon();
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接成功");
        }
    }

 */
}
