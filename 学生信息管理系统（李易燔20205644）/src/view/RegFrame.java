package view;

import ConnectSQL.StringUtil;
import ConnectSQL.init_SQL;
import dao.UserDao;
import jdk.nashorn.internal.scripts.JO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class RegFrame {
    private JTextField IDText;            //学号文本框
    private JPasswordField passwdText;  //密码文本框
    private JPasswordField checkPassword;//确认密码
    private JComboBox box;                //权限下拉列表框
    private JFrame jframe;                //窗体

    private UserDao userDao=new UserDao();
    private init_SQL dbUtil=new init_SQL();
    public RegFrame(){


        JLabel userlab = new JLabel("学/工号:");//创建学号标签
        IDText = new JTextField(25);    //实例学号文本框

        JLabel passwdlab = new JLabel("密 码:",JLabel.CENTER);//创建密码标签
        passwdText = new JPasswordField(25);//实例化密码文本框

        JLabel checklab=new JLabel("请确认密码",JLabel.CENTER);
        checkPassword=new JPasswordField(23);

        JLabel IDlabel = new JLabel("身份:");
        String str[] = {"学生","老师"};//创建下拉列表选项数组
        box = new JComboBox(str);
        jframe = new JFrame("学生管理系统注册界面");//实例化一个窗口,并设置标题
        JPanel userPanel= new JPanel();   //创建userPanel面板
        JPanel passwdPanel = new JPanel();//创建passwdPanel面板
        JPanel checkPass=new JPanel();//创建确认密码面板
        JPanel buttonPanel = new JPanel();//创建 buttonPanel面板
        JPanel labelPanel = new JPanel(); //labelPanel面板
        userPanel.add(userlab);//将userlab添加到userPanel面板
        userPanel.add(IDText); //将IDText添加到userPanel面板
        passwdPanel.add(passwdlab);
        passwdPanel.add(passwdText);
        JButton createNew = new JButton("注册");
        checkPass.add(checklab);
        checkPass.add(checkPassword);

        createNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regActionPerformed(e);
            }
        });


        buttonPanel.add(IDlabel);//
        buttonPanel.add(box);//将下拉列表框添加到buttonPanel面板
        buttonPanel.add(createNew);
        Font font=new Font("楷体",Font.BOLD+Font.PLAIN,35);

        jframe.setLayout(new GridLayout(4,1));
        jframe.add(userPanel);
        jframe.add(passwdPanel);
        jframe.add(checkPass);
        jframe.add(buttonPanel);
        jframe.setSize(400,200);
        jframe.setResizable(false);
        jframe.setLocation(250,350);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//窗口关闭方式
        jframe.setVisible(true);

    }

    //注册事件
    private void regActionPerformed(ActionEvent e) {
        String userName=this.IDText.getText();
        String passWord=new String(this.passwdText.getPassword());//passwo需要强转一下
        String checkPassword=new String(this.checkPassword.getPassword());
        String power=(String) this.box.getSelectedItem();
        //进行密码判断等操作
        if(StringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(null,"注册账号不能为空！");
            return;
        }
        if(StringUtil.isEmpty(passWord)){
            JOptionPane.showMessageDialog(null,"密码不能为空！");
            return;
        }
        if(StringUtil.isEmpty(checkPassword)){
            JOptionPane.showMessageDialog(null,"确认密码不能为空！");
            return;
        }
        if(passWord.equals(checkPassword)){
            System.out.println("可以注册！");
            Connection con=null;
            User user=new User(userName,passWord,power);

            try{
                con=dbUtil.getCon();
                int addnum=userDao.addUser(con,user);
                if(addnum==1){
                    JOptionPane.showMessageDialog(null,"注册成功！");
                }else{
                    JOptionPane.showMessageDialog(null,"注册失败");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                try {
                    dbUtil.closeCon(con);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }else{
            JOptionPane.showMessageDialog(null,"两次密码不一致！");
            return;
        }

    }

    public static void main(String[] args) {
        RegFrame ok=new RegFrame();
    }
}
