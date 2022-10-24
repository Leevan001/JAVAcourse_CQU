package dao;

import model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

public class UserDao {
    public User login(Connection con, User user)throws Exception{
        User resultUser=null;
        String sql="select*from user where userName=? and passWord=? and power=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2, user.getPassWord());
        pstmt.setString(3, user.getPower());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            resultUser=new User();
            resultUser.setId(rs.getInt("id"));
            resultUser.setUserName(rs.getString("userName"));
            resultUser.setPassWord(rs.getString("passWord"));
            resultUser.setPower(rs.getString("power"));
        }
        return resultUser;

    }

    //用户注册的方法
    public int addUser(Connection con,User user)throws Exception{
        String sql="insert into user values(null,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2,user.getPassWord());
        pstmt.setString(3,user.getPower());

        return pstmt.executeUpdate();
    }
}
