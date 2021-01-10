package com.company.receptionist;

import com.company.establishConnection;
import com.company.widgetDesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class addCustomer {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JLabel nameLabel, moNoLabel, mailLabel, genderLabel, ageLabel, nationalityLabel, roomNoLabel, nightLabel, idProofLabel, idNumberLabel;
    public static JTextField nameField, moNoField, mailField, ageField, nationalityField, roomNoField, nightField, idNumberField;
    public static JRadioButton maleBtn, femaleBtn;
    public static JComboBox<String> idProofField;
    public static JButton enter,cancel;
    public static JLabel img1,img2;
    public static establishConnection dc = new establishConnection();


    public addCustomer(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("ADD CUSTOMER",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        nameLabel =design.createLabelText("Name ",100,150,200,30,25);
        homeFrame.add(nameLabel);
        nameField =design.createTextField(250,150,300,30);
        homeFrame.add(nameField);

        moNoLabel =design.createLabelText("Mo No.",100,200,200,30,25);
        homeFrame.add(moNoLabel);
        moNoField =design.createTextField(250,200,250,30);
        homeFrame.add(moNoField);

        mailLabel =design.createLabelText("Email",550,200,200,30,25);
        homeFrame.add(mailLabel);
        mailField =design.createTextField(650,200,300,30);
        homeFrame.add(mailField);

        nationalityLabel =design.createLabelText("Nationality",100,250,200,30,25);
        homeFrame.add(nationalityLabel);
        nationalityField =design.createTextField(250,250,250,30);
        homeFrame.add(nationalityField);

        genderLabel =design.createLabelText("Gender",100,300,100,30,25);
        homeFrame.add(genderLabel);
        maleBtn =design.createRadioButton("Male",250,300,80,30,20);
        homeFrame.add(maleBtn);
        femaleBtn =design.createRadioButton("Female",350,300,100,30,20);
        homeFrame.add(femaleBtn);

        ageLabel =design.createLabelText("Age",550,300,200,30,25);
        homeFrame.add(ageLabel);
        ageField =design.createTextField(650,300,100,30);
        homeFrame.add(ageField);

        idProofLabel =design.createLabelText("Id Type",100,350,200,30,25);
        homeFrame.add(idProofLabel);
        String[] ids={"Passport","Aaddhar Card","Pancard","Driving License"};
        idProofField =design.createList(ids,250,350,150,30);
        homeFrame.add(idProofField);


        idNumberLabel =design.createLabelText("Id No.",100,400,200,30,25);
        homeFrame.add(idNumberLabel);
        idNumberField =design.createTextField(250,400,200,30);
        homeFrame.add(idNumberField);

        roomNoLabel =design.createLabelText("Room_No",100,450,250,30,25);
        homeFrame.add(roomNoLabel);
        roomNoField =design.createTextField(250,450,100,30);
        homeFrame.add(roomNoField);

        nightLabel =design.createLabelText("No Of Night",100,500,200,30,25);
        homeFrame.add(nightLabel);
        nightField =design.createTextField(250,500,100,30);
        homeFrame.add(nightField);

        enter =design.createButton("Check In",300,600,150,40,25);
        enter.addActionListener(new Action_performed());
        homeFrame.add(enter);

        cancel=design.createButton("Cancel",550,600,150,40,25);
        cancel.addActionListener(new Action_performed());
        homeFrame.add(cancel);

        img1 =design.setImageAsBackground("images/addGuest1.jpg",1000,100,400,300);
        homeFrame.add(img1);

        img2 =design.setImageAsBackground("images/addGuest2.jpg",1050,450,300,200);
        homeFrame.add(img2);


        homeFrame.add(background);
        homeFrame.setVisible(true);
    }

    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enter) {
                String id = null;
                String gen = null;
                String price=null;
                if (maleBtn.isSelected())
                    gen = "Male";
                else if (femaleBtn.isSelected())
                    gen = "Female";
                try {
                    String q="select price from room_detail where room_no='"+roomNoField.getText()+"'";
                    ResultSet rs = dc.sta.executeQuery(q);
                    while (rs.next()) {
                        price = rs.getString("price");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                int rate=Integer.parseInt(Objects.requireNonNull(price));
                int nights=Integer.parseInt(nightField.getText());

                int total=rate*nights;
                int advance=0,remaining=0;
                String totalBill=String.valueOf(total);
                String advPayment=String.valueOf(advance);
                String remainingPayment=String.valueOf(remaining);

                try{
                    String q = "insert into customer_detail(cust_name,mobile_no,email,nationality,gender,id_proof,id_number,room_number,stay_night,total_bill,advance,pending) " +
                            "values(?,?,?,?,?,?,?,?,?,?,?,?)";

                    PreparedStatement query = dc.connect.prepareStatement(q);

                    query.setString(1, nameField.getText());
                    query.setString(2, moNoField.getText());
                    query.setString(3, mailField.getText());
                    query.setString(4, nationalityField.getText());
                    query.setString(5, gen);
                    query.setString(6, (String) idProofField.getSelectedItem());
                    query.setString(7, idNumberField.getText());
                    query.setString(8, roomNoField.getText());
                    query.setString(9, nightField.getText());
                    query.setString(10,totalBill);
                    query.setString(11,advPayment);
                    query.setString(12,remainingPayment);

                    query.execute();

                    String q1 = "select cust_id from customer_detail where id_proof='" + idProofField.getSelectedItem() + "' and id_number='" + idNumberField.getText() + "'";
                    ResultSet rs = dc.sta.executeQuery(q1);
                    while (rs.next()) {
                        id = rs.getString(1);
                    }
                    JOptionPane.showMessageDialog(null, "Guest Added Successfully \n Guest Id is : " + id);
                    new paymentDetail(id);
                    homeFrame.setVisible(false);
                }catch(Exception ex){
                    ex.printStackTrace();
                }

                try {
                    String q="update room_detail set occupancy=? where room_no ='"+roomNoField.getText()+"'";
                    PreparedStatement query = dc.connect.prepareStatement(q);
                    query.setString(1,"Not Available");

                    query.executeUpdate();

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
