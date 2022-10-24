package view;

import ConnectSQL.StringUtil;
import ConnectSQL.init_SQL;
import dao.addStuDao;
import model.student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class changestudent extends JFrame{
    private JButton yesbutton;
    private JLabel idnum;
    private JLabel password;
    private JLabel GPA;
    private JLabel stuclass;
    private JLabel stuzh;
    private JTextField textId;
    private JTextField textZh;
    private JTextField textClass;
    private JTextField textGPA;
    private JTextField textPassword;
    private JPanel changepanel;
    private init_SQL dbutil=new init_SQL();
    private String oldname;
    public changestudent(String oldname){
        setContentPane(changepanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400,350);
        setVisible(true);
        this.oldname=oldname;
        yesbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changectionPerformed(e);
            }
        });
    }

    private void changectionPerformed(ActionEvent e) {
        int sid= Integer.parseInt(this.textId.getText());            //ID，姓名，班级，GPA,出生地
        String  name=this.textZh.getText();
        String stuclass=this.textClass.getText();
        float GPA= Float.parseFloat(this.textGPA.getText());
        String passWord=this.textPassword.getText();//进行学生信息的简单判断
        if(StringUtil.isEmpty(String.valueOf(sid))){
            JOptionPane.showMessageDialog(null,"修改的学生账号不能为空！");
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
            int change= addStuDao.update(con,student,oldname);
            if(change==1){
                JOptionPane.showMessageDialog(null,"修改成功！");
            }else{
                JOptionPane.showMessageDialog(null,"修改失败，ID和GPA应该为数字！");
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

    public static void main(String[] args) {
        changestudent okkk=new changestudent("202020");
    }
}
