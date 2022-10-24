package view;
import ConnectSQL.StringUtil;
import ConnectSQL.init_SQL;
import dao.UserDao;
import model.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class LoginFrame {
    private JTextField IDText;            //学号文本框
    private JPasswordField passwdText;  //密码文本框
    private JComboBox box;                //权限下拉列表框
    private JFrame jframe;                //窗体

    private UserDao userDao=new UserDao();
    private init_SQL dbUtil=new init_SQL();
    public LoginFrame(){


        JLabel userlab = new JLabel("学/工号:");//创建学号标签
        IDText = new JTextField(25);    //实例学号文本框
        JLabel passwdlab = new JLabel("密 码:",JLabel.CENTER);//创建密码标签
        passwdText = new JPasswordField(25);//实例化密码文本框
        JLabel IDlabel = new JLabel("身份:");
        String str[] = {"学生","老师"};//创建下拉列表选项数组
        box = new JComboBox(str);
        jframe = new JFrame("学生管理系统登录界面");//实例化一个窗口,并设置标题
        JPanel userPanel= new JPanel();   //创建userPanel面板
        JPanel passwdPanel = new JPanel();//创建passwdPanel面板
        JPanel buttonPanel = new JPanel();//创建 buttonPanel面板
        JPanel labelPanel = new JPanel(); //labelPanel面板
        userPanel.add(userlab);//将userlab添加到userPanel面板
        userPanel.add(IDText); //将IDText添加到userPanel面板
        passwdPanel.add(passwdlab);
        passwdPanel.add(passwdText);
        JButton submit = new JButton("登录");//创建按钮并设置文本
        JButton createNew = new JButton("注册");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginActionPerformed(e);
            }
        });
        createNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegFrame();
            }
        });

        buttonPanel.add(IDlabel);//
        buttonPanel.add(box);//将下拉列表框添加到buttonPanel面板
        buttonPanel.add(submit);
        buttonPanel.add(createNew);
        Font font=new Font("楷体",Font.BOLD+Font.PLAIN,35);

        jframe.setLayout(new GridLayout(4,1));
        jframe.add(userPanel);
        jframe.add(passwdPanel);
        jframe.add(buttonPanel);
        jframe.add(labelPanel);
        jframe.setSize(400,200);
        jframe.setResizable(false);
        jframe.setLocation(250,350);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭方式
        jframe.setVisible(true);
    }

    private void LoginActionPerformed(ActionEvent e) {
        String userName=this.IDText.getText();//获取ID
        String passWord=new String(this.passwdText.getPassword());//获取密码
        if(StringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(null,"用户名不能为空！");
        }
        if(StringUtil.isEmpty(passWord)){
            JOptionPane.showMessageDialog(null,"密码不能为空！");
        }
        Connection con=null;
        String power=null;
        String juris= (String) this.box.getSelectedItem();
        User user=new User(userName,passWord,power);
        if(juris.equals("老师")){
            user.setPower("老师");
            try {
                con=dbUtil.getCon();
                User currentUser=userDao.login(con,user);
                if(currentUser!=null){
                    //JOptionPane.showMessageDialog(null,"登陆成功！");
                    System.out.println("老师登陆成功");
                    //销毁当前窗口
                    dispose();
                    new AdminMainFrame().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"登陆失败!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                //关闭数据库
                try {
                    dbUtil.closeCon(con);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }else if(juris.equals("学生")){
            user.setPower("学生");
            try {
                con=dbUtil.getCon();
                User currentUser=userDao.login(con,user);
                if(currentUser!=null){
                    //JOptionPane.showMessageDialog(null,"登陆成功！");
                    System.out.println("学生登陆成功");
                    //销毁当前窗口
                    dispose();
                    new StudentMainFrame().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"登陆失败!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                //关闭数据库
                try {
                    dbUtil.closeCon(con);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        LoginFrame ok=new LoginFrame();
    }
}
