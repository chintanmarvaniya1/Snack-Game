package com.company.driver;

import com.company.establishConnection;
import com.company.receptionist.receptionistLogin;
import com.company.widgetDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class driverDashBoard {

    protected static JFrame homeFrame;
    protected static JPanel headingPanel;
    protected static JLabel backGround,hotelLogo;
    protected static JLabel headingText;
    protected static JButton logOut;
    public static JLabel carIdLabel, carCompanyLabel, carCompanyField, modelNameLabel, modelNameField, currLocationLabel, currLocationField, newLocationLael;
    public static JButton enter,search;
    public static JScrollPane scrollPane1;
    public static JTextField carIdField, newLocationField;
    public static establishConnection dc = new establishConnection();
    public static widgetDesign design = new widgetDesign();


    public driverDashBoard(){
        homeFrame= design.createFrame("MANAGER|HOME",75, 20, 1500, 800);
        backGround= design.setImageAsBackground("images/driverDashBoardBackground.jpg",0,0,1500,800);

        headingPanel =design.colorPanel(0,50,1500,75);

        hotelLogo=design.setImageAsBackground("images/driverLogo.png",50,32,150,150);
        homeFrame.add(hotelLogo);

        headingText=design.createLabelText("DRIVER",250,60,250,75,35);
        homeFrame.add(headingText);

        logOut=design.createImageButton("images/logOut.png",1350,72,40,40);
        logOut.addActionListener(new Action_performed());
        homeFrame.add(logOut);

        DefaultTableModel model=new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Cab Id","Company Name","Model Name","Location"});
        try {
            String q="select * from cab_Detail";
            ResultSet rs=dc.sta.executeQuery(q);
            while (rs.next()){
                String s1=rs.getString("cab_id");
                String s2=rs.getString("car_company");
                String s3=rs.getString("car_model");
                String s4=rs.getString("curr_location");
                model.addRow(new Object[]{s1,s2,s3,s4});
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        scrollPane1 =design.createTable(model,100,200,600,550);
        homeFrame.add(scrollPane1);

        carIdLabel =design.createLabelText("Car ID",800,200,220,30,25);
        homeFrame.add(carIdLabel);
        carIdField =design.createTextField(1000,200,100,30);
        homeFrame.add(carIdField);

        carCompanyLabel =design.createLabelText("Car Company",800,275,220,30,25);
        homeFrame.add(carCompanyLabel);
        carCompanyField =design.createLabelText("",1000,275,200,30,25);
        homeFrame.add(carCompanyField);

        modelNameLabel =design.createLabelText("Car Model",800,350,220,30,25);
        homeFrame.add(modelNameLabel);
        modelNameField =design.createLabelText("",1000,350,200,30,25);
        homeFrame.add(modelNameField);

        currLocationLabel =design.createLabelText("Current Location",800,425,220,30,25);
        homeFrame.add(currLocationLabel);
        currLocationField =design.createLabelText("",1000,425,200,30,25);
        homeFrame.add(currLocationField);

        newLocationLael =design.createLabelText("New Location",800,500,220,30,25);
        homeFrame.add(newLocationLael);
        newLocationField =design.createTextField(1000,500,200,30);
        homeFrame.add(newLocationField);

        search=design.createImageButton("images/search.png",1150,200,30,30);
        search.addActionListener(new Action_performed());
        homeFrame.add(search);

        enter =design.createButton("Update",900,575,150,40,25);
        enter.addActionListener(new Action_performed());
        homeFrame.add(enter);

        homeFrame.add(headingPanel);
        homeFrame.add(backGround);
        homeFrame.setVisible(true);
    }
    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==logOut){
                new driverLogin();
                homeFrame.setVisible(false);
            }else if (e.getSource()==search){
                try {
                    String q = "select * from cab_detail where cab_id='" + carIdField.getText() + "'";
                    ResultSet result = dc.sta.executeQuery(q);
                    while (result.next()) {
                        carCompanyField.setText(result.getString("car_company"));
                        modelNameField.setText(result.getString("car_model"));
                        currLocationField.setText(result.getString("curr_location"));
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }else if (e.getSource()==enter){
                try{
                    String q = "update cab_detail set curr_location=? where cab_id='"+carIdField.getText()+"'";
                    PreparedStatement query = dc.connect.prepareStatement(q);
                    query.setString(1,  newLocationField.getText());
                    query.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Location Changed Successfully");

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                DefaultTableModel model=new DefaultTableModel();
                model.setColumnIdentifiers(new Object[]{"Cab Id","Company Name","Model Name","Location"});
                try {
                    String q="select * from cab_Detail";
                    ResultSet rs=dc.sta.executeQuery(q);
                    while (rs.next()){
                        String s1=rs.getString("cab_id");
                        String s2=rs.getString("car_company");
                        String s3=rs.getString("car_model");
                        String s4=rs.getString("curr_location");
                        model.addRow(new Object[]{s1,s2,s3,s4});
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                scrollPane1 =design.createTable(model,100,200,600,550);
                homeFrame.add(scrollPane1);
            }

        }
    }
}
