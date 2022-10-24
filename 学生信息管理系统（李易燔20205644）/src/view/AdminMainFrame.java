package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AdminMainFrame extends JFrame {
 //   JFrame jframe=new JFrame();
    JMenuBar menubar;
    JMenu menu;
    JMenuItem itemz,items,itemc,itemg;//增删改查
    JDesktopPane table;
    JPanel contentpane;//信息栏
    public AdminMainFrame(){
        setTitle("教师管理界面");
        menubar=new JMenuBar();
        menu=new JMenu("操作菜单");
        ImageIcon ico=new ImageIcon("F:\\学生信息管理系统（李易燔20205644）\\src\\搜索.png");
        ico.setImage(ico.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));

        ImageIcon ico1=new ImageIcon("F:\\学生信息管理系统（李易燔20205644）\\src\\修改.png");
        ico1.setImage(ico1.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));

        ImageIcon ico2=new ImageIcon("F:\\学生信息管理系统（李易燔20205644）\\src\\删除.png");
        ico2.setImage(ico2.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));

        ImageIcon ico3=new ImageIcon("F:\\学生信息管理系统（李易燔20205644）\\src\\增加.png");
        ico3.setImage(ico3.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));

        itemc=new JMenuItem("查看学生信息",ico);
        itemg=new JMenuItem("修改学生信息",ico1);
        items=new JMenuItem("删除学生信息",ico2);
        itemz=new JMenuItem("增添学生信息",ico3);
        menu.add(itemz);
        menu.add(items);
        menu.add(itemc);
        menu.add(itemg);
        menubar.add(menu);

//        table=new JDesktopPane();
//        contentpane=new JPanel();
//        contentpane.setBorder(new EmptyBorder(5,5,5,5));
//        contentpane.setLayout(new BorderLayout(0,0));
//        setContentPane(contentpane);
//        contentpane.add(table,BorderLayout.CENTER);

//        final JLabel background=new JLabel();
//        final ImageIcon icon4=new ImageIcon("F:\\学生信息管理系统（李易燔20205644）\\src\\true.jpg");
//        icon4.setImage(icon4.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_DEFAULT));
//        background.setIcon(icon4);
//        background.setBounds(0,0,this.getWidth(),this.getHeight());
//        table.add(background);
//        getContentPane().addComponentListener(new ComponentAdapter(){
//            public void componentResized(ComponentEvent e){
//                int width=e.getComponent().getWidth();
//                int height=e.getComponent().getHeight();
//                icon4.setImage(icon4.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
//                background.setBounds(0,0,width,height);
//            }
//        });
        itemc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findstudent findstu=new findstudent();
            }
        });

        itemg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findstudent findstu=new findstudent();
            }
        });

        items.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findstudent findstu=new findstudent();
            }
        });


        itemz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addstu addstu=new addstu();
            }
        });

        setJMenuBar(menubar);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600,450);
    }

    public static void main(String[] args) {
        AdminMainFrame adminMainFrame = new AdminMainFrame();
    }
}
