package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.company.manager.*;
import com.company.receptionist.*;
import com.company.roomservice.*;
import com.company.driver.*;

public class welcomePage {

    protected static JFrame homeFrame;
    protected static JPanel headingPanel;
    protected static JLabel backGround,hotelLogo;
    protected static JLabel headingText;
    public static JLabel managerLogoImg,receptionistLogoImg,roomServiceLogoImg,driverLogoImg;
    public static JButton manager,receptionist,roomService,driver;


    public welcomePage(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("MANAGER|HOME",75, 20, 1500, 800);
        backGround= design.setImageAsBackground("images/startPageBg.jpg",0,0,1500,800);

        headingPanel =design.colorPanel(0,50,1500,75);

        hotelLogo=design.setImageAsBackground("images/hotelLogo1.png",50,32,200,145);
        homeFrame.add(hotelLogo);

        headingText=design.createLabelText("Hotel Taj",700,60,250,70,50);
        homeFrame.add(headingText);

        managerLogoImg =design.setImageAsBackground("images/managerLogo.png",100,300,250,250);
        homeFrame.add(managerLogoImg);
        manager =design.createButton("Manager",100,600,240,50,25);
        manager.setForeground(Color.WHITE);
        manager.setBackground(new Color(47, 47, 47));
        manager.addActionListener(new action_Performed());
        homeFrame.add(manager);

        receptionistLogoImg =design.setImageAsBackground("images/receptionistLogo.png",450,300,250,250);
        homeFrame.add(receptionistLogoImg);
        receptionist =design.createButton("Receptionist",450,600,240,50,25);
        receptionist.setForeground(Color.WHITE);
        receptionist.setBackground(new Color(47, 47, 47));
        receptionist.addActionListener(new action_Performed());
        homeFrame.add(receptionist);

        roomServiceLogoImg =design.setImageAsBackground("images/roomServiceLogo.png",800,300,250,250);
        homeFrame.add(roomServiceLogoImg);
        roomService =design.createButton("Room Service",800,600,240,50,25);
        roomService.setForeground(Color.WHITE);
        roomService.setBackground(new Color(47, 47, 47));
        roomService.addActionListener(new action_Performed());
        homeFrame.add(roomService);

        driverLogoImg =design.setImageAsBackground("images/driverLogo.png",1150,300,250,250);
        homeFrame.add(driverLogoImg);
        driver =design.createButton("Driver",1155,600,240,50,25);
        driver.setForeground(Color.WHITE);
        driver.setBackground(new Color(47, 47, 47));
        driver.addActionListener(new action_Performed());
        homeFrame.add(driver);


        homeFrame.add(headingPanel);
        homeFrame.add(backGround);
        homeFrame.setVisible(true);
    }

    private static class action_Performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==manager){
                new managerLogin();
                homeFrame.setVisible(false);
            }else if (e.getSource()==receptionist){
                new receptionistLogin();
                homeFrame.setVisible(false);
            }else if (e.getSource()==roomService){
                new roomServiceLogin();
                homeFrame.setVisible(false);
            }else if (e.getSource()==driver){
                new driverLogin();
                homeFrame.setVisible(false);
            }
        }
    }
        public static void main(String[] args) {
        new welcomePage();
    }
}
