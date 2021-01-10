package com.company.manager;

import com.company.establishConnection;
import com.company.widgetDesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class addManager {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JLabel passLabel,nameLabel,moNoLabel,emailLabel,genderLabel,ageLabel,dobLabel,jobLabel,qualificationLabel,idProofLabel,idNoLabel,addressLabel;
    public static JTextField passField,nameField,moNoField,emailField,ageField,dobField,qualificationField,idNoField,addressField;
    public static JRadioButton maleBtn,femaleBtn;
    public static JComboBox<String> jobField,idProofField;
    public static JButton submit,cancel;
    public static JLabel img1;
    public static establishConnection dc = new establishConnection();

    public addManager(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("NEW MANAGER",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        passLabel =design.createLabelText("Password",100,100,100,30,25);
        homeFrame.add(passLabel);
        passField=design.createTextField(220,100,200,30);
        homeFrame.add(passField);

        nameLabel=design.createLabelText("Name",100,150,100,30,25);
        homeFrame.add(nameLabel);
        nameField=design.createTextField(220,150,500,30);
        homeFrame.add(nameField);

        moNoLabel=design.createLabelText("Mo No.",100,200,100,30,25);
        homeFrame.add(moNoLabel);
        moNoField=design.createTextField(220,200,150,30);
        homeFrame.add(moNoField);

        emailLabel=design.createLabelText("Email",500,200,100,30,25);
        homeFrame.add(emailLabel);
        emailField=design.createTextField(620,200,250,30 );
        homeFrame.add(emailField);

        dobLabel=design.createLabelText("DOB",100,250,100,30,25);
        homeFrame.add(dobLabel);
        dobField=design.createTextField(220,250,150,30);
        homeFrame.add(dobField);

        genderLabel=design.createLabelText("Gender",100,300,100,30,25);
        homeFrame.add(genderLabel);
        maleBtn=design.createRadioButton("Male",220,300,80,30,20);
        homeFrame.add(maleBtn);
        femaleBtn=design.createRadioButton("Female",325,300,100,30,20);
        homeFrame.add(femaleBtn);

        ageLabel=design.createLabelText("Age",500,300,100,30,25);
        homeFrame.add(ageLabel);
        ageField=design.createTextField(620,300,100,30);
        homeFrame.add(ageField);

        jobLabel=design.createLabelText("Job",100,350,100,30,25);
        homeFrame.add(jobLabel);
        String[] jobs={"Manager"};
        jobField=design.createList(jobs,230,350,150,30);
        homeFrame.add(jobField);

        qualificationLabel=design.createLabelText("Qualification",100,400,200,30,25);
        homeFrame.add(qualificationLabel);
        qualificationField=design.createTextField(250,400,200,30);
        homeFrame.add(qualificationField);

        idProofLabel=design.createLabelText("Id Proof",100,450,200,30,25);
        homeFrame.add(idProofLabel);
        String[] ids={"Passport","Aadhaar Card","Pan Card","Driving License"};
        idProofField=design.createList(ids,250,450,150,30);
        homeFrame.add(idProofField);


        idNoLabel=design.createLabelText("Id No.",500,450,200,30,25);
        homeFrame.add(idNoLabel);
        idNoField=design.createTextField(620,450,200,30);
        homeFrame.add(idNoField);

        addressLabel=design.createLabelText("Address",100,500,200,30,25);
        homeFrame.add(addressLabel);
        addressField=design.createTextField(250,500,600,30);
        homeFrame.add(addressField);

        submit=design.createButton("OK",300,600,150,40,30);
        submit.addActionListener(new action_Performed());
        homeFrame.add(submit);

        cancel=design.createButton("Cancel",550,600,150,40,25);
        cancel.addActionListener(new action_Performed());
        homeFrame.add(cancel);

        img1=design.setImageAsBackground("images/manager.jpeg",900,100,500,600);
        homeFrame.add(img1);


        homeFrame.add(background);
        homeFrame.setVisible(true);
    }

    private static class action_Performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==submit){
                String id=null;
                try {
                    String gen=null;
                    if (maleBtn.isSelected())
                        gen="Male";
                    else if (femaleBtn.isSelected())
                        gen="Female";

                    String q = "insert into employee_detail(pass,emp_name,mobile_no,emil_add,dob,gender,age,designation,qualification,id_proof,id_number,address)" +
                            "values(?,?,?,?,?,?,?,?,?,?,?,?) ";

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

                    query.execute();

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                try {
                    String q1 = "select emp_id from employee_detail where id_proof='" + idProofField.getSelectedItem() + "' and id_number='" + idNoField.getText() + "'";
                    ResultSet rs = dc.sta.executeQuery(q1);
                    while (rs.next()) {
                        id = rs.getString(1);
                    }
                    JOptionPane.showMessageDialog(null, "Guest Added Successfully \n Guest Id is : " + id);
                    new managerLogin();
                    homeFrame.setVisible(false);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            if (e.getSource()==cancel){
                new managerLogin();
                homeFrame.setVisible(false);
            }
        }
    }
}
