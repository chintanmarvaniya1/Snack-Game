package com.company.manager;

import com.company.establishConnection;
import com.company.widgetDesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class updateEmployee {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JLabel passLabel, nameLabel, moNoLabel, emailLabel, genderLabel, ageLabel, dobLabel, jobLabel, qualificationLabel, idProofLabel, idNoLabel, addressLabel;
    public static JTextField passField, nameField, moNoField, emailField, ageField, dobField, qualificationField, idNoField, addressField;
    public static JRadioButton maleBtn, femaleBtn;
    public static JComboBox<String> jobField, idProofField;
    public static JButton enter,cancel,delete;
    public static JLabel img1;
    public static String empId;
    public static establishConnection dc = new establishConnection();


    public updateEmployee(String empId){
        updateEmployee.empId=empId;
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("UPDATE EMPLOYEE",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        passLabel =design.createLabelText("Password",100,100,100,30,25);
        homeFrame.add(passLabel);
        passField =design.createTextField(220,100,200,30);
        homeFrame.add(passField);

        nameLabel =design.createLabelText("Name",100,150,100,30,25);
        homeFrame.add(nameLabel);
        nameField =design.createTextField(220,150,500,30);
        homeFrame.add(nameField);

        moNoLabel =design.createLabelText("Mo No.",100,200,100,30,25);
        homeFrame.add(moNoLabel);
        moNoField =design.createTextField(220,200,150,30);
        homeFrame.add(moNoField);

        emailLabel =design.createLabelText("Email",500,200,100,30,25);
        homeFrame.add(emailLabel);
        emailField =design.createTextField(620,200,250,30 );
        homeFrame.add(emailField);

        dobLabel =design.createLabelText("DOB",100,250,100,30,25);
        homeFrame.add(dobLabel);
        dobField =design.createTextField(220,250,150,30);
        homeFrame.add(dobField);

        genderLabel =design.createLabelText("Gender",100,300,100,30,25);
        homeFrame.add(genderLabel);
        maleBtn =design.createRadioButton("Male",220,300,80,30,20);
        homeFrame.add(maleBtn);
        femaleBtn =design.createRadioButton("Female",325,300,100,30,20);
        homeFrame.add(femaleBtn);

        ageLabel =design.createLabelText("Age",500,300,100,30,25);
        homeFrame.add(ageLabel);
        ageField =design.createTextField(620,300,100,30);
        homeFrame.add(ageField);

        jobLabel =design.createLabelText("Job",100,350,100,30,25);
        homeFrame.add(jobLabel);
        String[] jobs={"Manager","Driver","Cleaner","Receptionist"};
        jobField =design.createList(jobs,230,350,150,30);
        homeFrame.add(jobField);

        qualificationLabel =design.createLabelText("Qualification",100,400,200,30,25);
        homeFrame.add(qualificationLabel);
        qualificationField =design.createTextField(250,400,200,30);
        homeFrame.add(qualificationField);

        idProofLabel =design.createLabelText("Id Type",100,450,200,30,25);
        homeFrame.add(idProofLabel);
        String[] ids={"Passport","Aadhaar Card","Pan card","Driving License"};
        idProofField =design.createList(ids,250,450,150,30);
        homeFrame.add(idProofField);


        idNoLabel =design.createLabelText("Id No.",500,450,200,30,25);
        homeFrame.add(idNoLabel);
        idNoField =design.createTextField(620,450,200,30);
        homeFrame.add(idNoField);

        addressLabel =design.createLabelText("Address",100,500,200,30,25);
        homeFrame.add(addressLabel);
        addressField =design.createTextField(250,500,600,30);
        homeFrame.add(addressField);

        enter=design.createButton("OK",250,600,150,40,30);
        enter.addActionListener(new Action_performed());
        homeFrame.add(enter);

        cancel=design.createButton("Cancel",450,600,150,40,25);
        cancel.addActionListener(new Action_performed());
        homeFrame.add(cancel);

        delete=design.createButton("Delete",650,600,150,40,25);
        delete.addActionListener(new Action_performed());
        homeFrame.add(delete);

        img1 =design.setImageAsBackground("Resources/Manager_Login_Backgound.jpg",900,100,500,600);
        homeFrame.add(img1);

        try {

            String q = "select * from employee_detail where emp_id='" + empId + "'";
            ResultSet result = dc.sta.executeQuery(q);
            if (result.next()){
                passField.setText(result.getString(2));
                nameField.setText(result.getString(3));
                moNoField.setText(result.getString(4));
                emailField.setText(result.getString(5));
                dobField.setText(result.getString(6));
                ageField.setText(result.getString(8));
                jobField.setSelectedItem(result.getString(9));
                qualificationField.setText(result.getString(10));
                idProofField.setSelectedItem(result.getString(11));
                idNoField.setText(result.getString(12));
                addressField.setText(result.getString(13));


            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Check the Details");
        }


        homeFrame.add(background);
        homeFrame.setVisible(true);
    }

    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==enter){
                try {
                    String gen=null;
                    if (maleBtn.isSelected())
                        gen="Male";
                    else if (femaleBtn.isSelected())
                        gen="Female";

                    String q = "update employee_detail set pass=?,emp_name=?,mobile_no=?,emil_add=?,dob=?,gender=?,age=?," +
                            "designation=?,qualification=?,id_proof=?,id_number=?,address=? where emp_id='"+empId+"' ";

                    PreparedStatement query = dc.connect.prepareStatement(q);
                    query.setString(1,passField.getText());
                    query.setString(2,nameField.getText());
                    query.setString(3,moNoField.getText());
                    query.setString(4,emailField.getText());
                    query.setString(5,dobField.getText());
                    query.setString(6,gen);
                    query.setString(7,ageField.getText());
                    query.setString(8, Objects.requireNonNull(jobField.getSelectedItem()).toString());
                    query.setString(9,qualificationField.getText());
                    query.setString(10, Objects.requireNonNull(idProofField.getSelectedItem()).toString());
                    query.setString(11,idNoField.getText());
                    query.setString(12,addressField.getText());

                    query.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    homeFrame.setVisible(false);
                    new employeeList();

                }catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Check the Details");
                }
            }
            else if(e.getSource()==delete){
                try {
                    String q = "delete from employee_detail where emp_id='" + empId + "' ";
                    PreparedStatement query = dc.connect.prepareStatement(q);

                    query.execute();

                    JOptionPane.showMessageDialog(null, "Deleted Successfully");
                    homeFrame.setVisible(false);
                    new employeeList();

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else if (e.getSource()==cancel){
                homeFrame.setVisible(false);
                new employeeList();
            }
        }
    }
}
