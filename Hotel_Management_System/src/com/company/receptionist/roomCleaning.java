package com.company.receptionist;

import com.company.establishConnection;
import com.company.manager.employeeList;
import com.company.widgetDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class roomCleaning {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JScrollPane scrollPanel1;
    public static JTextField roomNoField;
    public static JLabel roomNoLabel, currStatusLabel, currStatusField, newStatusLabel;
    public static JComboBox<String> newStatusField;
    public static JButton enter,search,cancel;
    public static establishConnection dc = new establishConnection();
    public static widgetDesign design = new widgetDesign();

    public roomCleaning(){

        homeFrame= design.createFrame("CLEAN STATUS",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        DefaultTableModel model=new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Room NO","Occupancy","Cleaning"});
        try {
            String q="select * from room_detail";
            ResultSet rs=dc.sta.executeQuery(q);
            while (rs.next()){
                String s1=rs.getString("room_no");
                String s2=rs.getString("occupancy");
                String s3=rs.getString("clean_status");
                model.addRow(new Object[]{s1,s2,s3});
            }

        }catch (Exception ex){
            System.out.println(ex);
        }
        scrollPanel1 =design.createTable(model,100,50,500,650);
        homeFrame.add(scrollPanel1);

        roomNoLabel =design.createLabelText("Room No",700,150,220,30,25);
        homeFrame.add(roomNoLabel);
        roomNoField =design.createTextField(900,150,220,30);
        homeFrame.add(roomNoField);

        currStatusLabel =design.createLabelText("Current Status",700,250,220,30,25);
        homeFrame.add(currStatusLabel);
        currStatusField =design.createLabelText("",900,250,220,30,25);
        homeFrame.add(currStatusField);

        newStatusLabel =design.createLabelText("Update to",700,350,220,30,25);
        homeFrame.add(newStatusLabel);
        String[] x={"Clean","Not Clean"};
        newStatusField =design.createList(x,900,350,220,30);
        homeFrame.add(newStatusField);

        search=design.createImageButton("images/search.png",1150,150,30,30);
        search.addActionListener(new Action_performed());
        homeFrame.add(search);

        enter =design.createButton("Update",800,450,150,40,25);
        enter.addActionListener(new Action_performed());
        homeFrame.add(enter);

        cancel=design.createButton("Cancel",1000,450,150,40,25);
        cancel.addActionListener(new Action_performed());
        homeFrame.add(cancel);

        homeFrame.add(background);
        homeFrame.setVisible(true);
    }

    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==search){
                try {
                    String q = "select * from room_detail where room_no='" + roomNoField.getText() + "'";
                    ResultSet result = dc.sta.executeQuery(q);
                    if (result.next())
                        currStatusField.setText(result.getString("clean_status"));
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }else if (e.getSource()==enter){
                try{
                    String q = "update room_detail set clean_status=? where room_no='"+roomNoField.getText()+"'";
                    PreparedStatement query = dc.connect.prepareStatement(q);
                    query.setString(1, (String) newStatusField.getSelectedItem());
                    query.executeUpdate();

                    new receptionistDashBoard();
                    homeFrame.setVisible(false);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else if (e.getSource()==cancel){
                new receptionistDashBoard();
                homeFrame.setVisible(false);
            }
        }
    }

}
