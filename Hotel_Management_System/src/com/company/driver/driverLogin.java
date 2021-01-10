package com.company.driver;

import com.company.establishConnection;
import com.company.receptionist.receptionistDashBoard;
import com.company.receptionist.receptionistLogin;
import com.company.welcomePage;
import com.company.widgetDesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class driverLogin {
    public static JFrame homeFrame;
    public static JPanel headingPanel;
    public static JLabel background, userIcon, passIcon, userLogo;
    public static  JTextField userField, passField;
    public static JButton login,cancel;
    public static JLabel receptionistImg;
    public static establishConnection dc = new establishConnection();

    public driverLogin(){
        widgetDesign design = new widgetDesign();
        homeFrame =design.createFrame("DRIVER|LOGIN",75, 20, 1500, 800);
        background =design.setImageAsBackground("images/managerLoginBackground.jpg",0,0,1500,800);

        headingPanel =design.createPanel(550,200,400,500);

        receptionistImg =design.setImageAsBackground("images/driverLogo.png",690,220,150,150);
        homeFrame.add(receptionistImg);

        userLogo =design.setImageAsBackground("Resources/profile.png",700,250,100,100 );
        homeFrame.add(userLogo);

        userIcon =design.setImageAsBackground("images/userIcon.png",650,405,30,30);
        homeFrame.add(userIcon);

        userField =design.createTextField(700,400,200,40);
        homeFrame.add(userField);

        passIcon =design.setImageAsBackground("images/key.png",650,480,30,30);
        homeFrame.add(passIcon);

        passField =design.createTextField(700,475,200,40);
        homeFrame.add(passField);

        login=design.createButton("Login",600,575,150,40,25);
        login.addActionListener(new Action_performed());
        homeFrame.add(login);

        cancel=design.createButton("Cancel",775,575,150,40,25);
        cancel.addActionListener(new Action_performed());
        homeFrame.add(cancel);

        homeFrame.add(headingPanel);
        homeFrame.add(background);
        homeFrame.setVisible(true);
    }

    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==login){
                try{
                    String q="select * from employee_detail where designation='Driver' and emp_id='"+userField.getText()+"' and pass ='"+passField.getText()+"'";
                    ResultSet res=dc.sta.executeQuery(q);
                    if (res.next()){
                        JOptionPane.showMessageDialog(null,"Login Successfully");
                        new driverDashBoard();
                        homeFrame.setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Incorrect Detail");
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
            else if (e.getSource()==cancel){
                new welcomePage();
                homeFrame.setVisible(false);
            }
        }
    }
}
