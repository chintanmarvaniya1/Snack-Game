package com.company.receptionist;

import com.company.widgetDesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class receptionistDashBoard {
    protected static JFrame homeFrame;
    protected static JPanel headingPanel;
    protected static JLabel backGround,hotelLogo;
    protected static JLabel headingText;
    protected static JButton logOut,roomList,addCustomer, paymentDetail,checkOut,customerList,roomCleaning, cabDriver;

    public receptionistDashBoard(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("MANAGER|HOME",75, 20, 1500, 800);
        backGround= design.setImageAsBackground("images/receptionDashBoardBackground.jpg",0,0,1500,800);

        headingPanel =design.colorPanel(0,50,1500,75);

        hotelLogo=design.setImageAsBackground("images/roomServiceLogo.png",50,32,150,150);
        homeFrame.add(hotelLogo);

        headingText=design.createLabelText("RECEPTIONIST",250,60,250,75,35);
        homeFrame.add(headingText);

        logOut=design.createImageButton("images/logOut.png",1350,72,40,40);
        logOut.addActionListener(new Action_performed());
        homeFrame.add(logOut);

        roomList=design.createImageButton("images/roomDeatils.png",150,200,300,200);
        roomList.addActionListener(new Action_performed());
        homeFrame.add(roomList);


        addCustomer=design.createImageButton("images/addCustomer.png",600,200,300,200);
        addCustomer.addActionListener(new Action_performed());
        homeFrame.add(addCustomer);

        paymentDetail =design.createImageButton("images/paymentDetail.png",1050,200,300,200);
        paymentDetail.addActionListener(new Action_performed());
        homeFrame.add(paymentDetail);

        customerList=design.createImageButton("images/customerDetail.png",150,500,300,200);
        customerList.addActionListener(new Action_performed());
        homeFrame.add(customerList);

        roomCleaning=design.createImageButton("images/roomCleaner.png",600,500,300,200);
        roomCleaning.addActionListener(new Action_performed());
        homeFrame.add(roomCleaning);

        cabDriver =design.createImageButton("images/carlist.png",1050,500,300,200);
        cabDriver.addActionListener(new Action_performed());
        homeFrame.add(cabDriver);


        homeFrame.add(headingPanel);
        homeFrame.add(backGround);
        homeFrame.setVisible(true);
    }

    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==roomList){
                new roomList();
                homeFrame.setVisible(false);
            }else if (e.getSource()==addCustomer){
                new addCustomer();
                homeFrame.setVisible(false);
            }else if (e.getSource()== paymentDetail){
                new paymentDetail(null);
                homeFrame.setVisible(false);
            }else if (e.getSource()==customerList){
                new customerList();
                homeFrame.setVisible(false);
            }else if (e.getSource()==roomCleaning){
                new roomCleaning();
                homeFrame.setVisible(false);
            }else if (e.getSource()== cabDriver){
                new cabDriver();
                homeFrame.setVisible(false);
            }else if (e.getSource()==logOut){
                new receptionistLogin();
                homeFrame.setVisible(false);
            }
        }
    }


}
