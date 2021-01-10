package com.company.receptionist;

import com.company.establishConnection;
import com.company.manager.managerDashBoard;
import com.company.widgetDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class customerList {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JScrollPane tableScrollPane1;
    public static JTextField searchField;
    public static JButton cancel;
    public static establishConnection dc = new establishConnection();

    public customerList(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("CUSTOMER LIST",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        searchField =design.createTextField(600,100,250,30);
        homeFrame.add(searchField);

        DefaultTableModel model=new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID No","Name","Mobile No","Id Type","Id No","Room No","Night Stay"});
        try {

            String q="select * from customer_detail";
            ResultSet rs=dc.sta.executeQuery(q);
            while (rs.next()){
                String s1=rs.getString("cust_id");
                String s2=rs.getString("cust_name");
                String s3=rs.getString("mobile_no");
                String s4=rs.getString("id_proof");
                String s5=rs.getString("id_number");
                String s6=rs.getString("room_number");
                String  s7=rs.getString("stay_night");
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
            if (e.getSource()==cancel){
                new receptionistDashBoard();
                homeFrame.setVisible(false);
            }
        }
    }

}
