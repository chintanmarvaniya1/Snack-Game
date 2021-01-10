package com.company.receptionist;

import com.company.establishConnection;
import com.company.widgetDesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class paymentDetail {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static String custId;
    public static JLabel customerIdLabel,totalBillLabel,advanceBillLabel,remainingBillLabel,totalBillField,advanceBillField,remainingBillField,paymentLabel;
    public static JTextField customerIdField,paymentField;
    public static JButton search,enter,checkOut,cancel;
    public static JLabel img1;
    public static establishConnection dc = new establishConnection();


    public paymentDetail(String custId){
        paymentDetail.custId=custId;
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("ROOM LIST",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        customerIdLabel=design.createLabelText("Customer Id",400,400,250,30,25);
        homeFrame.add(customerIdLabel);
        customerIdField=design.createTextField(650,400,100,30);
        customerIdField.setText(custId);
        homeFrame.add(customerIdField);

        totalBillLabel=design.createLabelText("Total Bill",400,450,250,30,25);
        homeFrame.add(totalBillLabel);
        totalBillField=design.createLabelText("",650,450,100,30,25);
        homeFrame.add(totalBillField);

        advanceBillLabel=design.createLabelText("Advance Payment",400,500,250,30,25);
        homeFrame.add(advanceBillLabel);
        advanceBillField=design.createLabelText("",650,500,100,30,25);
        homeFrame.add(advanceBillField);

        remainingBillLabel=design.createLabelText("Remaining Payment",400,550,250,30,25);
        homeFrame.add(remainingBillLabel);
        remainingBillField=design.createLabelText("",650,550,100,30,25);
        homeFrame.add(remainingBillField);

        paymentLabel=design.createLabelText("Amount ",400,600,250,30,25);
        homeFrame.add(paymentLabel);
        paymentField=design.createTextField(650,600,100,30);
        homeFrame.add(paymentField);

        img1 =design.setImageAsBackground("images/paymentimg.jpg",350,50,800,300);
        homeFrame.add(img1);

        search=design.createImageButton("images/search.png",800,400,30,30);
        search.addActionListener(new Action_performed());
        homeFrame.add(search);

        enter=design.createButton("Pay Bill",1000,400,150,50,25);
        enter.addActionListener(new Action_performed());
        homeFrame.add(enter);

        checkOut=design.createButton("Check Out",1000,500,150,50,25);
        checkOut.addActionListener(new Action_performed());
        homeFrame.add(checkOut);

        cancel=design.createButton("Cancel",1000,600,150,50,25);
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
                    String q = "select * from customer_detail where cust_id='" + customerIdField.getText() + "'";
                    ResultSet result = dc.sta.executeQuery(q);
                    if (result.next()) {

                            totalBillField.setText(result.getString("total_bill"));
                            advanceBillField.setText(result.getString("advance"));
                            remainingBillField.setText(result.getString("pending"));

                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            } else if (e.getSource()==enter) {

                int totalPayment=0,advancePayment=0,remainingPayment;
                try {
                    String q = "select * from customer_detail where cust_id='" + customerIdField.getText() + "'";
                    ResultSet result = dc.sta.executeQuery(q);
                    if (result.next()) {
                        totalPayment=Integer.parseInt(result.getString("total_bill"));
                        advancePayment=Integer.parseInt(result.getString("advance"));
                        remainingPayment=Integer.parseInt(result.getString("pending"));
                    }
                    advancePayment=advancePayment+Integer.parseInt(paymentField.getText());
                    remainingPayment=totalPayment-advancePayment;

                    String q1 = "update customer_detail set advance=?,pending=? where cust_id='"+customerIdField.getText()+"'";
                    PreparedStatement query = dc.connect.prepareStatement(q1);
                    query.setString(1,String.valueOf(advancePayment));
                    query.setString(2,String.valueOf(remainingPayment));
                    query.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Payment Done Successfully");

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }else if (e.getSource()==checkOut){
                String rooNo="";
                try {
                    String q = "select * from customer_detail where cust_id='" + customerIdField.getText() + "'";
                    ResultSet result = dc.sta.executeQuery(q);
                    if (result.next()) {
                       rooNo=result.getString("room_number");
                    }

                    String q1 = "update room_detail set occupancy=? where room_no='"+rooNo+"'";
                    PreparedStatement query = dc.connect.prepareStatement(q1);
                    query.setString(1,"Available");
                    query.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Check Out Successfully");
                    new receptionistDashBoard();
                    homeFrame.setVisible(false);

                }catch (Exception ex){
                    ex.printStackTrace();
                }


            } else if (e.getSource()==cancel){
                new receptionistDashBoard();
                homeFrame.setVisible(false);
            }

        }
    }

}
