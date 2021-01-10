package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class widgetDesign {
    public JFrame createFrame(String rsc, int x, int y, int width, int height){
        JFrame jf =new JFrame();
        jf.setTitle(rsc);
        jf.setBounds(x,y,width,height);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jf;
    }

    public JPanel createPanel(int x,int y,int width,int height){
        JPanel p1=new JPanel();
        p1.setBounds(x,y,width,height);
        p1.setBackground(new Color(3,3,3,20));
        return p1;
    }

    public JPanel colorPanel(int x,int y,int width,int height){
        JPanel heading=new JPanel();
        heading.setBackground(new Color(47, 47, 47));                                                   //Heading
        heading.setBounds(0,50,1500,100);
        return heading;
    }

    public  JLabel setImageAsBackground(String rsc, int x, int y, int width, int height)
    {
        ImageIcon admin_Image=new ImageIcon(rsc);
        Image admin_img=admin_Image.getImage();
        Image temp_admin_img= admin_img.getScaledInstance(width,height,Image.SCALE_REPLICATE);
        admin_Image=new ImageIcon(temp_admin_img);
        JLabel admin_logo=new JLabel("",admin_Image,JLabel.CENTER);
        admin_logo.setBounds(x,y,width,height);
        admin_logo.setBorder(null);
        return admin_logo;

    }

    public JButton createButton(String name,int x,int y,int width,int height,int size){
        JButton btn = new JButton(name);
        btn.setFont(new Font("serif", Font.BOLD, size));
        btn.setForeground(new Color(47, 47, 47));
        btn.setBackground(new Color(255, 255, 255));
        btn.setBounds(x, y, width, height);
        return btn;
    }

    public JButton createImageButton(String src,int x,int y,int width,int height){
        ImageIcon log_out=new ImageIcon(src);
        Image log_out_img=log_out.getImage();
        Image temp_log_out= log_out_img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        log_out=new ImageIcon(temp_log_out);
        JButton logout=new JButton(log_out);
        logout.setSize(width,height);
        logout.setBorder(null);
        logout.setBounds(x,y,width,height);
        return logout;
    }

    public JTextField createTextField(int x,int y,int width,int height){
        JTextField id_field =new JTextField();
        id_field.setBounds(x,y,width,height);
        id_field.setFont(new Font("serif",Font.PLAIN,20));
        id_field.setBorder(null);
        return id_field;
    }

    public JLabel createLabelText(String src,int x,int y,int width,int hight,int size){
        JLabel name=new JLabel(src);
        name.setForeground(Color.WHITE);
        name.setBounds(x,y,width,hight);
        name.setFont(new Font("serif",Font.PLAIN,size));
        return name;
    }

    public JRadioButton createRadioButton(String src,int x,int y,int width,int hight,int size){
        JRadioButton male=new JRadioButton(src);
        male.setBounds(x,y,width,hight);
        male.setBackground(new Color(47, 47, 47));
        male.setForeground(Color.WHITE);
        male.setBorder(null);
        male.setFont(new Font("serif",Font.PLAIN,size));
        return male;
    }

    public  JComboBox<String> createList(String[] lst,int x,int y,int width,int hight) {
        JComboBox<String> job_field = new JComboBox<>(lst);
        job_field.setBackground(Color.WHITE);
        job_field.setBounds(x,y,width,hight);
        return job_field;
    }

    public JCheckBox createCheckBox(String name,int x,int y,int width,int hight){
        JCheckBox ava=new JCheckBox(name,true);
        ava.setBackground(new Color(47, 47, 47));
        ava.setForeground(Color.WHITE);
        ava.setBorder(null);
        ava.setBounds(x,y,width,hight);
        return ava;
    }

    public JScrollPane createTable(DefaultTableModel model, int x, int y, int width, int hight){
        JTable t1=new JTable(model);
        t1.setBorder(null);
        t1.setRowHeight(30);
        t1.setBackground(Color.WHITE);
        t1.setFont(new Font("serif",Font.PLAIN,15));
        JScrollPane js=new JScrollPane(t1);
        js.setBounds(x,y,width,hight);
        return js;
    }
}
