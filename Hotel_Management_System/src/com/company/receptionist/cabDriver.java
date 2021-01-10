package com.company.receptionist;

import com.company.establishConnection;
import com.company.widgetDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class cabDriver {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JScrollPane scrollPane1;
    public static JTextField carIdField, newLocationField;
    public static JLabel carIdLabel, carCompanyLabel, carCompanyField, modelNameLabel, modelNameField, currLocationLabel, currLocationField, newLocationLael;
    public static JButton enter,search,cancel;
    public static establishConnection dc = new establishConnection();
    public static widgetDesign design = new widgetDesign();

    public cabDriver(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("CAB LIST",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

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
        scrollPane1 =design.createTable(model,100,50,600,650);
        homeFrame.add(scrollPane1);

        carIdLabel =design.createLabelText("Car ID",800,150,220,30,25);
        homeFrame.add(carIdLabel);
        carIdField =design.createTextField(1000,150,100,30);
        homeFrame.add(carIdField);

        carCompanyLabel =design.createLabelText("Car Company",800,225,220,30,25);
        homeFrame.add(carCompanyLabel);
        carCompanyField =design.createLabelText("",1000,225,200,30,25);
        homeFrame.add(carCompanyField);

        modelNameLabel =design.createLabelText("Car Model",800,300,220,30,25);
        homeFrame.add(modelNameLabel);
        modelNameField =design.createLabelText("",1000,300,200,30,25);
        homeFrame.add(modelNameField);

        currLocationLabel =design.createLabelText("Current Location",800,375,220,30,25);
        homeFrame.add(currLocationLabel);
        currLocationField =design.createLabelText("",1000,375,200,30,25);
        homeFrame.add(currLocationField);

        newLocationLael =design.createLabelText("New Location",800,450,220,30,25);
        homeFrame.add(newLocationLael);
        newLocationField =design.createTextField(1000,450,200,30);
        homeFrame.add(newLocationField);

        search=design.createImageButton("images/search.png",1150,150,30,30);
        search.addActionListener(new Action_performed());
        homeFrame.add(search);

        enter =design.createButton("Update",900,525,150,40,25);
        enter.addActionListener(new Action_performed());
        homeFrame.add(enter);

        cancel=design.createButton("Cancel",1100,525,150,40,25);
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
