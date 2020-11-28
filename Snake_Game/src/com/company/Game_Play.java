package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game_Play extends JPanel implements ActionListener, KeyListener {

    private int[] snake_X_length =new int[750];
    private int[] snake_Y_length =new int[750];
    private int move=0;

    private int snake_length=3;
    private  int score=0;

    private boolean left=false;
    private boolean right=false;
    private boolean down=false;
    private boolean up=false;

    public int[] Food_X_location={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    public int[] Food_Y_location={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    private Random rnd=new Random();
    private int X_location_index =rnd.nextInt(34);
    private int Y_location_index =rnd.nextInt(23);

    private Timer time;

    public Game_Play(){

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        int delay = 100;
        time=new Timer(delay,this);
        time.start();
    }

    public void paint(Graphics g){

        g.setColor(Color.WHITE);                                                                          //Title Bar Boarder
        g.drawRect(24,10,852,55);

        ImageIcon title_img = new ImageIcon("Graphics_image/snaketitle.jpg");                                //Title Image
        title_img.paintIcon(this,g,25,11);

        g.setColor(Color.WHITE);                                                                          //Dash board Rectangle Boarder
        g.drawRect(24,74,851,577);

        g.setColor(Color.BLACK);                                                                         //Dash Board Fill Rectangle
        g.fillRect(25,75,850,575);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Score :"+score, 780,30);

        if (move==0){                                                        //index of snake at start
            snake_X_length[0]=100;
            snake_X_length[1]=75;
            snake_X_length[2]=50;

            snake_Y_length[0]=100;
            snake_Y_length[1]=100;
            snake_Y_length[2]=100;
        }

        ImageIcon right_mouth = new ImageIcon("Graphics_image/rightmouth.png");
        right_mouth.paintIcon(this,g,snake_X_length[0],snake_Y_length[0]);

        for (int i=0;i<snake_length;i++){
            if (i==0 && right){                                                              //Starting position of Snake if move ==0
                right_mouth = new ImageIcon("Graphics_image/rightmouth.png");
                right_mouth.paintIcon(this,g,snake_X_length[i],snake_Y_length[i]);
            }


            if (i==0 && left){
                ImageIcon left_mouth = new ImageIcon("Graphics_image/leftmouth.png");
                left_mouth.paintIcon(this,g,snake_X_length[i],snake_Y_length[i]);
            }
            if (i==0 && up){
                ImageIcon up_mouth = new ImageIcon("Graphics_image/upmouth.png");
                up_mouth.paintIcon(this,g,snake_X_length[i],snake_Y_length[i]);
            }
            if (i==0 && down){
                ImageIcon down_mouth = new ImageIcon("Graphics_image/downmouth.png");
                down_mouth.paintIcon(this,g,snake_X_length[i],snake_Y_length[i]);
            }
            if (i!= 0){
                ImageIcon snake_img = new ImageIcon("Graphics_image/snakeimage.png");
                snake_img.paintIcon(this,g,snake_X_length[i],snake_Y_length[i]);
            }

        }

        ImageIcon food_Image=new ImageIcon("Graphics_image/food.png");

        if (Food_X_location[X_location_index]==snake_X_length[0] && Food_Y_location[Y_location_index]==snake_Y_length[0]){
            score++;
            snake_length++;
            X_location_index =rnd.nextInt(34);
            Y_location_index =rnd.nextInt(23);
        }
        food_Image.paintIcon(this,g, Food_X_location[X_location_index], Food_Y_location[Y_location_index]);

        for (int i=1;i<snake_length;i++){
            if (snake_X_length[i]==snake_X_length[0] && snake_Y_length[i]==snake_Y_length[0]){
                right=false;
                left=false;
                up=false;
                down=false;

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("GAME OVER !!!!!!!!",300,300);

                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("Press SPACE to Restart",340,330);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if (right){
            for (int i=snake_length-1;i>=0;i--)
                snake_Y_length[i+1]=snake_Y_length[i];
            for (int i=snake_length;i>=0;i--){
                if (i==0)
                    snake_X_length[i]=snake_X_length[i]+25;
                else
                    snake_X_length[i]=snake_X_length[i-1];
                if (snake_X_length[i]>850)
                    snake_X_length[i]=25;


            }
            repaint();
        }else if(left){
            for (int i=snake_length-1;i>=0;i--)
                snake_Y_length[i+1]=snake_Y_length[i];
            for (int i=snake_length;i>=0;i--){
                if (i==0)
                    snake_X_length[i]=snake_X_length[i]-25;
                else
                    snake_X_length[i]=snake_X_length[i-1];
                if (snake_X_length[i]<25)
                    snake_X_length[i]=850;


            }
            repaint();

        }else if(up){
            for (int i=snake_length-1;i>=0;i--)
                snake_X_length[i+1]=snake_X_length[i];
            for (int i=snake_length;i>=0;i--){
                if (i==0)
                    snake_Y_length[i]=snake_Y_length[i]-25;
                else
                    snake_Y_length[i]=snake_Y_length[i-1];
                if (snake_Y_length[i]<75)
                    snake_Y_length[i]=625;


            }
            repaint();
        }else if(down){
            for (int i=snake_length-1;i>=0;i--)
                snake_X_length[i+1]=snake_X_length[i];
            for (int i=snake_length;i>=0;i--){
                if (i==0)
                    snake_Y_length[i]=snake_Y_length[i]+25;
                else
                    snake_Y_length[i]=snake_Y_length[i-1];
                if (snake_Y_length[i]>625)
                    snake_Y_length[i]=75;


            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            move=0;
            score=0;
            snake_length=3;
            repaint();
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            move++;
            right=true;
            if(!left)
                right=true;
            else
            {
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }else if (e.getKeyCode()==KeyEvent.VK_LEFT){
            move++;
            left=true;
            if(!right)
                left=true;
            else
            {
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }else if (e.getKeyCode()==KeyEvent.VK_UP){
            move++;
            up=true;
            if(!down)
                up=true;
            else
            {
                up=false;
                down=true;
            }
            left=false;
            right=false;
        }else if (e.getKeyCode()==KeyEvent.VK_DOWN){
            move++;
            down=true;
            if(!up)
                down=true;
            else
            {
                down=false;
                up=true;
            }
            left=false;
            right=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
