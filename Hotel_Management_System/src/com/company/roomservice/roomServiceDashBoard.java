package com.company.roomservice;

import com.company.establishConnection;
import com.company.widgetDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class roomServiceDashBoard {
    protected static JFrame homeFrame;
    protected static JPanel headingPanel;
    protected static JLabel backGround,hotelLogo;
    protected static JLabel headingText;
    protected static JButton logOut;
    public static JScrollPane scrollPanel1;
    public static JTextField roomNoField;
    public static JLabel roomNoLabel, currStatusLabel, currStatusField, newStatusLabel;
    public static JComboBox<String> newStatusField;
    public static JButton enter,search;

    public static establishConnection dc = new establishConnection();
    public static widgetDesign design = new widgetDesign();

    public roomServiceDashBoard(){
        homeFrame= design.createFrame("ROOM SERVICE|HOME",75, 20, 1500, 800);
        backGround= design.setImageAsBackground("images/roomServiceBg.jpg",0,0,1500,800);

        headingPanel =design.colorPanel(0,50,1500,75);

        hotelLogo=design.setImageAsBackground("images/roomServiceLogo.png",50,32,150,150);
        homeFrame.add(hotelLogo);

        headingText=design.createLabelText("ROOM SERVICE",250,60,250,75,25);
        homeFrame.add(headingText);

        logOut=design.createImageButton("images/logOut.png",1350,72,40,40);
        logOut.addActionListener(new Action_performed());
        homeFrame.add(logOut);

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
            ex.printStackTrace();
        }
        scrollPanel1 =design.createTable(model,100,200,400,500);
        homeFrame.add(scrollPanel1);

        roomNoLabel =design.createLabelText("Room No",700,200,220,30,25);
        homeFrame.add(roomNoLabel);
        roomNoField =design.createTextField(900,200,220,30);
        homeFrame.add(roomNoField);

        currStatusLabel =design.createLabelText("Current Status",700,300,220,30,25);
        homeFrame.add(currStatusLabel);
        currStatusField =design.createLabelText("",900,300,220,30,25);
        homeFrame.add(currStatusField);

        newStatusLabel =design.createLabelText("Update to",700,400,220,30,25);
        homeFrame.add(newStatusLabel);
        String[] x={"Clean","Not Clean"};
        newStatusField =design.createList(x,900,400,220,30);
        homeFrame.add(newStatusField);

        search=design.createImageButton("images/search.png",1150,200,30,30);
        search.addActionListener(new Action_performed());
        homeFrame.add(search);

        enter =design.createButton("Update",800,450,150,40,25);
        enter.addActionListener(new Action_performed());
        homeFrame.add(enter);

        homeFrame.add(headingPanel);
        homeFrame.add(backGround);
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
                }catch (Exception ex){
                    ex.printStackTrace();
                }

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
                    ex.printStackTrace();
                }
                scrollPanel1 =design.createTable(model,100,200,400,500);
                homeFrame.add(scrollPanel1);
            }else if (e.getSource()==logOut){
                new roomServiceLogin();
                homeFrame.setVisible(false);
            }
        }
    }

}
