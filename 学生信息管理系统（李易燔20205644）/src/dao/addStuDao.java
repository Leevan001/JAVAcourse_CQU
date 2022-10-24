package dao;

import ConnectSQL.StringUtil;
import ConnectSQL.init_SQL;
import model.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//增加学生的方法
public class addStuDao {//类名没有取好，应该是adminstrator，将在这个类中完成对数据库中学生信息的增删改查
    public static int add(Connection con, student student)throws Exception{//增加
        String sql="insert into student values(null,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,student.getName());
        pstmt.setString(2,student.getStuclass());
        pstmt.setString(3, String.valueOf(student.getGPA()));
        pstmt.setString(4,student.getPassword());
        return pstmt.executeUpdate();
    }
    //查询
    public ResultSet list(Connection con, student student)throws Exception{
        StringBuffer strb=new StringBuffer("select * from student " );
        if(StringUtil.isNotEmpty(student.getName())){
            strb.append("where name = '"+student.getName()+"'");
        }
        PreparedStatement pstmt=con.prepareStatement(strb.toString());
        return pstmt.executeQuery();
    }
    public int deletestu(Connection con,String zh)throws Exception{
        String sql="delete from student where name=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,zh);
        return pstmt.executeUpdate();
    }

    //修改
    public static int update(Connection con, student st, String oldzh)throws Exception{
        String sql="update student set name=?,class_number=?,GPA=?,password=? where name=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,st.getName());
        pstmt.setString(2,st.getStuclass());
        pstmt.setString(3, String.valueOf(st.getGPA()));
        pstmt.setString(4, st.getPassword());
        pstmt.setString(5,oldzh);
        return pstmt.executeUpdate();

    }

    //测试程序
    public void List(int i)throws Exception{
        init_SQL dbutil=new init_SQL();
        Connection con=dbutil.getCon();
        StringBuffer strb=new StringBuffer("select * from student ");
        strb.append("where name ='10086'");
        PreparedStatement pstmt=con.prepareStatement(strb.toString());
        ResultSet rs= pstmt.executeQuery();
        rs.next();
        System.out.println(rs.getString("id"));
        System.out.println(rs.getString("name"));
    }
//进行搜索测试
    public static void main(String[] args) {
        addStuDao okk=new addStuDao();
        try {
            okk.List(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
