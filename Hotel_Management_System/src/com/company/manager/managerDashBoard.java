package com.company.manager;

import javax.swing.*;
import com.company.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class managerDashBoard {
    protected static JFrame homeFrame;
    protected static JPanel headingPanel;
    protected static JLabel backGround,hotelLogo;
    protected static JLabel headingText;
    protected static JButton logOut,addEmp,addRoom,addCar,empList,roomList, custList;


    public managerDashBoard(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("MANAGER|HOME",75, 20, 1500, 800);
        backGround= design.setImageAsBackground("images/managerDashBoardBackground.jpg",0,0,1500,800);

        headingPanel =design.colorPanel(0,50,1500,75);

        hotelLogo=design.setImageAsBackground("images/managerLogo.png",50,32,150,150);
        homeFrame.add(hotelLogo);

        headingText=design.createLabelText("MANAGER",250,60,250,75,48);
        homeFrame.add(headingText);

        logOut=design.createImageButton("images/logOut.png",1350,70,40,40);
        logOut.addActionListener(new action_Performed());
        homeFrame.add(logOut);

        addEmp=design.createImageButton("images/addEmployee.png",150,200,300,200);
        addEmp.addActionListener(new action_Performed());
        homeFrame.add(addEmp);

        addRoom=design.createImageButton("images/addRoom.png",600,200,300,200);
        addRoom.addActionListener(new action_Performed());
        homeFrame.add(addRoom);

        addCar=design.createImageButton("images/addCar.png",1050,200,300,200);
        addCar.addActionListener(new action_Performed());
        homeFrame.add(addCar);

        empList=design.createImageButton("images/employeeDetail.png",150,500,300,200);
        empList.addActionListener(new action_Performed());
        homeFrame.add(empList);

        roomList=design.createImageButton("images/roomDeatils.png",600,500,300,200);
        roomList.addActionListener(new action_Performed());
        homeFrame.add(roomList);

        custList =design.createImageButton("images/customerDetail.png",1050,500,300,200);
        custList.addActionListener(new action_Performed());
        homeFrame.add(custList);

        homeFrame.add(headingPanel);
        homeFrame.add(backGround);
        homeFrame.setVisible(true);

    }

    private static class action_Performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == logOut) {
                new managerLogin();
                homeFrame.setVisible(false);
            } else if (e.getSource() == addEmp) {
                new addEmployee();
                homeFrame.setVisible(false);
            } else if (e.getSource() == addRoom) {
                new addRoom();
                homeFrame.setVisible(false);
            } else if (e.getSource() == addCar) {
                new addCar();
                homeFrame.setVisible(false);
            } else if (e.getSource() == empList) {
                new employeeList();
                homeFrame.setVisible(false);
            } else if (e.getSource() == roomList) {
                new roomList();
                homeFrame.setVisible(false);
            } else if (e.getSource() == custList) {
                new customerList();
                homeFrame.setVisible(false);
            }
        }
    }
}
