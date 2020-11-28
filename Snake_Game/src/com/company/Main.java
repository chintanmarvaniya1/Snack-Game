package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame frame=new JFrame();
        Game_Play gamePlay =new Game_Play();


        frame.setBounds(10,10,905,700);
        frame.setBackground(Color.DARK_GRAY);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePlay);
    }
}
