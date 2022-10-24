package view;

import ConnectSQL.init_SQL;
import dao.addStuDao;
import model.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class findstudent extends JFrame{
    private JTable showtable;
    private JTextField find_request=new JTextField(8);
    private JLabel findrequest=new JLabel("请输入学生账号：");
    private JButton check=new JButton("搜索");
    private JButton change=new JButton("修改");
    private JButton del=new JButton("删除");
    private JScrollPane myScrollPane;
    private init_SQL dbutil=new init_SQL();
    private addStuDao addStuDao=new addStuDao();
    private JPanel jpanel_check=new JPanel();
 //   private JPanel table_panel=new JPanel();

//    private JLabel message_change=new JLabel("修改成的信息(逗号隔开)：");
//    private JTextField change_text=new JTextField(20);
//    private JPanel jPanel_change=new JPanel();


    Vector rowData,columnNames;
    public findstudent(){

        setTitle("学生信息查看界面");
        setSize(450,400);
        this.setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);//设置表头
//        Object[][] data={{1,"23","4",45,"6"}};
//        String []columns={"id","账号","班级","GPA","密码"};
//        DefaultTableModel model=new DefaultTableModel(data,columns);
//        showtable.setModel(model);
//        showtable=null;

        //查找事件
        this.setLayout(new BorderLayout());
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchstuactionPerformed(e);
            }
        });

        //删除事件
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delactionPerformed(e);
            }
        });

        //修改事件
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addactionPerformed(e);
            }
        });

        jpanel_check.add(findrequest);
        jpanel_check.add(find_request);
        jpanel_check.add(check);
        jpanel_check.add(change);
        jpanel_check.add(del);
        jpanel_check.setBounds(0,0,400,30);

//        jPanel_change.add(message_change);
//        jPanel_change.add(change_text);
  //      myScrollPane.add(jpanel_check);
        this.add(jpanel_check,BorderLayout.NORTH);
  //      table_panel.setBounds(10,30,400,30);
//       this.add(jPanel_change,BorderLayout.SOUTH);
        this.fillTable(new student());

    }

    private void addactionPerformed(ActionEvent e) {
        //先进行查找
        String stuzh=this.find_request.getText();
        //       System.out.println(stuzh);
        student stu=new student();
        stu.setName(stuzh);
        System.out.println(stu.getName());
        this.fillTable(stu);
        if(showtable.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"查无此人！");
            return;
        }
        String str = String.valueOf(showtable.getValueAt(0,1));
        System.out.println("查找到的账号："+str);
        changestudent changeWindow=new changestudent(str);
    }

    //删除
    private void delactionPerformed(ActionEvent e) {

        //先和查找操作一样
        String stuzh=this.find_request.getText();
        //       System.out.println(stuzh);
        student stu=new student();
        stu.setName(stuzh);
        System.out.println(stu.getName());
        this.fillTable(stu);
        if(showtable.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"查无此人！");
            return;
        }
        Connection con=null;
        try {
            con=dbutil.getCon();
            try {
                int result= addStuDao.deletestu(con,stuzh);
                System.out.println(result);
                JOptionPane.showMessageDialog(null,"删除成功！");
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                dbutil.closeCon(con);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.fillTable(new student());

    }

    //查询事件处理
    private void searchstuactionPerformed(ActionEvent e) {
        String stuzh=this.find_request.getText();
 //       System.out.println(stuzh);
        student stu=new student();
        stu.setName(stuzh);
        System.out.println(stu.getName());
        this.fillTable(stu);
        if(showtable.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"查无此人！");
        }

    }

    private void fillTable(student stu){
        columnNames=new Vector();
        //设置列名
        columnNames.add("id");
        columnNames.add("账号");
        columnNames.add("班级");
        columnNames.add("GPA");
        columnNames.add("密码");

        rowData = new Vector();
        //rowData可以存放多行,开始从数据库里取

        Connection con=null;
        try {
            con=dbutil.getCon();
            ResultSet rs=addStuDao.list(con,stu);
//            rs.next();
//            System.out.println(rs.getString("id"));
//            System.out.println(rs.getString("name"));
            //对结果集进行遍历
            while(rs.next()){
                Vector v=new Vector<>();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("class_number"));
                v.add(rs.getString("GPA"));
                v.add(rs.getString("password"));
                System.out.println(rs.getString("id")+"   "+rs.getString("name"));
                rowData.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dbutil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //初始化Jtable
        System.out.println(rowData);

        System.out.println(columnNames);
        showtable=new JTable(rowData,columnNames);
//       showtable.setPreferredScrollableViewportSize(new Dimension(300,250));
        showtable.setEnabled(false);//设置表为不可更改


        System.out.println("表格列数："+showtable.getColumnCount());
        System.out.println("表格行数"+showtable.getRowCount());

        myScrollPane=new JScrollPane(showtable);
        myScrollPane.setBounds(10,60,400,200);
        this.add(myScrollPane,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        findstudent okk=new findstudent();
    }
}