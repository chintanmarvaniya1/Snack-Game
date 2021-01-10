package com.company.manager;

import com.company.establishConnection;
import com.company.widgetDesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class addRoom {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JPanel bg_colour;
    public static JLabel roomNoLabel, roomTypeLabel, bedLabel, occupancyLabel, cleanStatusLabel, priceLabel;
    public static JTextField roomNoField, priceField;
    public static JCheckBox occupancyField;
    public static JComboBox<String> roomTypeField, bedField;
    public static JRadioButton cleanBtn, notCleanBtn;
    public static JButton submit,cancel;
    public static JLabel img1, img2, img3;

    public static establishConnection dc = new establishConnection();

    public addRoom(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("NEW ROOM",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        roomNoLabel =design.createLabelText("Room No",100,100,200,30,25);
        homeFrame.add(roomNoLabel);
        roomNoField =design.createTextField(250,100,100,30);
        homeFrame.add(roomNoField);

        roomTypeLabel =design.createLabelText("Room Type",100,175,200,30,25);
        homeFrame.add(roomTypeLabel);
        String[] room_type={"NON AC","AC","Luxurious","Presidential Suit"};
        roomTypeField =design.createList(room_type,250,175,150,30);
        homeFrame.add(roomTypeField);

        bedLabel =design.createLabelText("No of Bed",100,250,150,30,25);
        homeFrame.add(bedLabel);
        String[] no_bed={"1","2","3","4"};
        bedField =design.createList(no_bed,250,250,150,30);
        homeFrame.add(bedField);

        occupancyLabel =design.createLabelText("Occupancy",100,325,150,30,25);
        homeFrame.add(occupancyLabel);
        occupancyField =design.createCheckBox("Available",250,325,150,30);
        homeFrame.add(occupancyField);

        cleanStatusLabel =design.createLabelText("Cleaning Status",100,400,200,30,25);
        homeFrame.add(cleanStatusLabel);
        cleanBtn =design.createRadioButton("Clean",300,400,100,30,20);
        homeFrame.add(cleanBtn);
        notCleanBtn =design.createRadioButton("Not Clean",400,400,100,30,20);
        homeFrame.add(notCleanBtn);

        priceLabel =design.createLabelText("Price",100,475,150,30,25);
        homeFrame.add(priceLabel);
        priceField =design.createTextField(250,475,150,30);
        homeFrame.add(priceField);

        submit =design.createButton("Create",250,600,150,30,25);
        submit.addActionListener(new Action_performed());
        homeFrame.add(submit);

        cancel=design.createButton("Cancel",450,600,150,30,25);
        cancel.addActionListener(new Action_performed());
        homeFrame.add(cancel);

        img1 =design.setImageAsBackground("images/hotelRoom2.jpg",800,50,400,200);
        homeFrame.add(img1);

        img2 =design.setImageAsBackground("images/hotelRoom1.jpg",1100,275,300,200);
        homeFrame.add(img2);

        img3 =design.setImageAsBackground("images/hotelRoom3.jpg",800,500,400,200);
        homeFrame.add(img3);

        homeFrame.add(background);
        homeFrame.setVisible(true);
    }

    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==submit) {
                try {
                    String occupancy, clean = "Clean";
                    if (occupancyField.isSelected())
                        occupancy = "Available";
                    else
                        occupancy = "Not Available";

                    if (cleanBtn.isSelected())
                        clean = "Clean";
                    else if (notCleanBtn.isSelected())
                        clean = "Not Clean";


                    String q = "insert into room_detail(room_no,no_of_bed,room_type,occupancy,clean_status,price)" +
                            "values(?,?,?,?,?,?)";
                    PreparedStatement query = dc.connect.prepareStatement(q);
                    query.setString(1, roomNoField.getText());
                    query.setString(2, (String) bedField.getSelectedItem());
                    query.setString(3, (String) roomTypeField.getSelectedItem());
                    query.setString(4, occupancy);
                    query.setString(5, clean);
                    query.setString(6, priceField.getText());

                    query.execute();
                    JOptionPane.showMessageDialog(null, "Room Added Successfully");
                    new managerDashBoard();
                    homeFrame.setVisible(false);


                } catch (Exception ex) {
                    ex.printStackTrace();
                    //JOptionPane.showMessageDialog(null,"Something is Wrong");
                }
            }else if (e.getSource()==cancel){
                new managerDashBoard();
                homeFrame.setVisible(false);
            }
        }
    }
}
