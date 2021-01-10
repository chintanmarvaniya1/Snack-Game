package com.company.manager;

import com.company.establishConnection;
import com.company.widgetDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class employeeList {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JScrollPane tableScrollPane1;
    public static JTextField searchField;
    public static JButton search,cancel;
    public static establishConnection dc = new establishConnection();


    public employeeList(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("EMPLOYEE LIST",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        searchField =design.createTextField(600,100,250,30);
        homeFrame.add(searchField);

        search=design.createImageButton("images/search.png",900,100,30,30);
        search.addActionListener(new Action_performed());
        homeFrame.add(search);

        DefaultTableModel model=new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID No","Name","Mobile No","Designation","Qualification","Id Type","Id No"});
        try {
            String q="select * from employee_detail";
            ResultSet rs=dc.sta.executeQuery(q);
            while (rs.next()){
                String s1=rs.getString("emp_id");
                String s2=rs.getString("emp_name");
                String s3=rs.getString("mobile_no");
                String s4=rs.getString("designation");
                String s5=rs.getString("qualification");
                String s6=rs.getString("id_proof");
                String  s7=rs.getString("id_number");
                model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7});
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        tableScrollPane1 =design.createTable(model,200,200,1100,450);
        homeFrame.add(tableScrollPane1);

        cancel=design.createButton("Cancel",650,700,150,30,25);
        cancel.addActionListener(new Action_performed());
        homeFrame.add(cancel);

        homeFrame.add(background);
        homeFrame.setVisible(true);
    }

    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==search){
                new updateEmployee(searchField.getText());
                homeFrame.setVisible(false);
            }
            else if (e.getSource()==cancel){
                new managerDashBoard();
                homeFrame.setVisible(false);
            }
        }
    }
}
