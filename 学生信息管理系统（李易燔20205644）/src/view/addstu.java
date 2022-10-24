package view;

import ConnectSQL.StringUtil;
import ConnectSQL.init_SQL;
import dao.addStuDao;
import jdk.nashorn.internal.scripts.JO;
import model.student;
import sun.security.util.Password;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class addstu extends JFrame{
    private JTextField stu_id;
    private JPanel addstuPanel;
    private JTextField stu_name;
    private JTextField stu_class;
    private JTextField stu_GPA;
    private JButton add_stu;
    private JTextField pass_word;
    private JButton reset;
    private JLabel stuid;
    private JLabel stuname;
    private JLabel stuclass;
    private JLabel stuGPA;
    private JLabel password;

    private init_SQL dbutil=new init_SQL();

    addstu(){
        setContentPane(addstuPanel);
        setTitle("学生信息添加界面");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400,350);
        setVisible(true);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetactionPerformed(e);
            }
        });
        add_stu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentactionPerformed(e);
            }
        });
    }
//设置提交按钮
    private void studentactionPerformed(ActionEvent e) {
        int sid= Integer.parseInt(this.stu_id.getText());            //ID，姓名，班级，GPA,出生地
        String  name=this.stu_name.getText();
        String stuclass=this.stu_class.getText();
        float GPA= Float.parseFloat(this.stu_GPA.getText());
        String passWord=this.pass_word.getText();//进行学生信息的简单判断
        if(StringUtil.isEmpty(String.valueOf(sid))){
            JOptionPane.showMessageDialog(null,"学生账号不能为空！");
            return;
        }
        if(StringUtil.isEmpty(String.valueOf(passWord))){
            JOptionPane.showMessageDialog(null,"学生账号的密码不能为空！");
            return;
        }
        student student=new student(name,stuclass,GPA,passWord);
        Connection con=null;
        try {
            con=dbutil.getCon();
            int addnum= addStuDao.add(con,student);
            if(addnum==1){
                JOptionPane.showMessageDialog(null,"添加成功！");
            }else{
                JOptionPane.showMessageDialog(null,"添加失败，ID和GPA应该为数字！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                dbutil.closeCon(con);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //置空
    private void resetactionPerformed(ActionEvent e) {
        this.pass_word.setText("");
        this.stu_class.setText("");
        this.stu_GPA.setText("");
        this.stu_id.setText("");
        this.stu_name.setText("");
    }

    public static void main(String[] args) {
        addstu ok=new addstu();
    }
}
