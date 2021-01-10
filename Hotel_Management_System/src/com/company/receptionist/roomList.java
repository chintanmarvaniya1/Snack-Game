package com.company.receptionist;

import com.company.establishConnection;
import com.company.manager.managerDashBoard;
import com.company.widgetDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class roomList {
    protected static JFrame homeFrame;
    protected static JPanel background;
    public static JCheckBox occupancyField;
    public static JScrollPane tableScrollPane1;
    public static JComboBox<String> roomTypeField;
    public static JButton search,cancel;
    public static DefaultTableModel table;
    public static establishConnection dc = new establishConnection();
    public static widgetDesign design = new widgetDesign();


    public roomList(){
        widgetDesign design = new widgetDesign();
        homeFrame= design.createFrame("ROOM LIST",75, 20, 1500, 800);
        background =design.colorPanel(0,50,1500,75);

        String[] room_type={"NON AC","AC","Luxurious","Presidential Suit"};
        roomTypeField =design.createList(room_type,500,100,200,30);
        homeFrame.add(roomTypeField);

        occupancyField =design.createCheckBox("Available",750,100,150,30);
        homeFrame.add(occupancyField);

        search=design.createImageButton("images/search.png",900,100,30,30);
        search.addActionListener(new Action_performed());
        homeFrame.add(search);

        table =new DefaultTableModel();
        table.setColumnIdentifiers(new Object[]{"Room Id","Room No","Bed No","Room Type","Occupancy","Clean Status","Price"});
        try {
            String q="select * from room_detail";
            ResultSet rs=dc.sta.executeQuery(q);
            while (rs.next()){
                String s1=rs.getString("room_id");
                String s2=rs.getString("room_no");
                String s3=rs.getString("no_of_bed");
                String s4=rs.getString("room_type");
                String s5=rs.getString("occupancy");
                String s6=rs.getString("clean_status");
                String s7=rs.getString("price");
                table.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7});
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        tableScrollPane1 =design.createTable(table,200,200,1100,450);
        homeFrame.add(tableScrollPane1);

        cancel=design.createButton("Cancel",650,700,150,30,25);
        cancel.addActionListener(new Action_performed());
        homeFrame.add(cancel);

        homeFrame.add(background);
        homeFrame.setVisible(true);
    }

    private static class Action_performed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String condition=null;
            if (e.getSource()==search){
                if(occupancyField.isSelected())
                    condition="Available";
                else if (!occupancyField.isSelected())
                    condition="Not Available";

                String type= (String) roomTypeField.getSelectedItem();

                table=new DefaultTableModel();
                table.setColumnIdentifiers(new Object[]{"Room Id","Room No","Bed No","Room Type","Occupancy","Clean Status","Price"});
                try {
                    String q="select * from room_detail where room_type='"+type+"' and occupancy='"+condition+"'";
                    ResultSet rs=dc.sta.executeQuery(q);
                    while (rs.next()){
                        String s1=rs.getString("room_id");
                        String s2=rs.getString("room_no");
                        String s3=rs.getString("no_of_bed");
                        String s4=rs.getString("room_type");
                        String s5=rs.getString("occupancy");
                        String s6=rs.getString("clean_status");
                        String s7=rs.getString("price");
                        table.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7});
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                tableScrollPane1=design.createTable(table,200,200,1100,450);
                homeFrame.add(tableScrollPane1);
            }
            else if (e.getSource()==cancel){
                new receptionistDashBoard();
                homeFrame.setVisible(false);
            }
        }
    }

}
