package com.company.manager;

import com.company.establishConnection;
import com.company.widgetDesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class addCar {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JLabel  companyLabel, modelLabel, registrationNoLabel, locationLabel;
    public static JTextField  companyField, modelField, registrationField, locationField;
    public static JButton enter,cancel;
    public JLabel img1, img2;
    public static establishConnection dc = new establishConnection();

    public addCar(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("NEW CAR",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        companyLabel =design.createLabelText("Car Company",100,175,150,30,25);
        homeFrame.add(companyLabel);
        companyField =design.createTextField(250,175,250,30);
        homeFrame.add(companyField);

        modelLabel =design.createLabelText("Car Model",100,250,150,30,25);
        homeFrame.add(modelLabel);
        modelField =design.createTextField(250,250,250,30);
        homeFrame.add(modelField);

        registrationNoLabel =design.createLabelText("Car No",100,325,150,30,25);
        homeFrame.add(registrationNoLabel);
        registrationField =design.createTextField(250,325,150,30);
        homeFrame.add(registrationField);

        locationLabel =design.createLabelText("Car location",100,400,150,30,25);
        homeFrame.add(locationLabel);
        locationField =design.createTextField(250,400,150,30);
        homeFrame.add(locationField);

        enter=design.createButton("Insert",225,500,150,30,25);
        enter.addActionListener(new Action_performed());
        homeFrame.add(enter);

        cancel=design.createButton("Cancel",400,500,150,30,25);
        cancel.addActionListener(new Action_performed());
        homeFrame.add(cancel);

        img1 =design.setImageAsBackground("images/car1.jpg",800,100,500,250);
        homeFrame.add(img1);

        img2 =design.setImageAsBackground("images/car2.jpg",800,450,500,250);
        homeFrame.add(img2);

        homeFrame.add(background);
        homeFrame.setVisible(true);
    }

    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==enter){
                try{
                    String q="insert into cab_detail(car_company,car_model,registration_no,curr_location)" +
                            "values (?,?,?,?)";
                    PreparedStatement query=dc.connect.prepareStatement(q);
                    query.setString(1,companyField.getText());
                    query.setString(2,modelField.getText());
                    query.setString(3, registrationField.getText());
                    query.setString(4,locationField.getText());

                    query.execute();
                    JOptionPane.showMessageDialog(null,"Car Added Successfully");

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                homeFrame.setVisible(false);
                new managerDashBoard();
            }
            if (e.getSource()==cancel){
                new managerDashBoard();
                homeFrame.setVisible(false);
            }
        }
    }
}
