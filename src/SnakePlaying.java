import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Random;

import static java.awt.Font.ITALIC;

public class SnakePlaying  extends JPanel implements KeyListener, ActionListener{
    private int [] x_length = new int [800];
    private int [] y_length = new int [800];


    private boolean up_direction = false;
    private boolean down_direction = false;
    private boolean right_direction = false;
    private boolean left_direction = false;

    private ImageIcon head;
    private ImageIcon bodySnake;
    private ImageIcon target;
    Random random = new Random();


    private int [] target_x = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,
            500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int [] target_y = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,
            500,525,550,575,600,625};
    private int pos_x = random.nextInt(34);
    private int pos_y = random.nextInt(23);


    private int move;
    private int snakesLength = 3;

    private Timer timer;
    private int delay = 150;
    private int score;

    public SnakePlaying() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {

        if(move == 0){
            x_length[2] = 50;
            x_length[1] = 75;
            x_length [0] = 100;

            y_length[2] = 100;
            y_length[1] = 100;
            y_length[0] = 100;
        }

        //the drawing of the board's game
        g.setColor(Color.white);
        g.drawRect(24,74 ,851, 577);

        //the filling of the rect that we have already drawn
        g.setColor(Color.gray);
        g.fillRect(25, 75, 850, 575);

        for(int i = 0; i < snakesLength; i++){
            if(i == 0){
                head = new ImageIcon("head.png");
                head.paintIcon(this, g, x_length[0], y_length[0]);
            }if(i != 0){
                bodySnake = new ImageIcon("snakesBody.png");
                bodySnake.paintIcon(this, g, x_length[i], y_length[i]);
            }

        }

        target = new ImageIcon("target.png");

        if((target_x[pos_x] == x_length[0] && target_y[pos_y] == y_length[0])){
            snakesLength++;
            score++;
            pos_x = random.nextInt(34);
            pos_y = random.nextInt(23);

        }


        target.paintIcon(this, g, target_x[pos_x], target_y[pos_y]);
        Font font = new Font("DIALOG", Font.BOLD,16);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("Score: " + score ,600,15);


        for(int i = 1; i < snakesLength; i++){
            if(x_length[i] == x_length[0] && y_length[i] == y_length[0] ){
                left_direction = false;
                right_direction = false;
                up_direction = false;
                down_direction = false;


                g.setColor(Color.BLACK);
                g.drawRoundRect(300,200,350,220, 100,100);
                g.fillArc(300,200,350,220, 500,500);
                //drawing and setting the font of the String
                g.setColor(Color.white);
                g.setFont(new Font("DIALOG", Font.BOLD, 50));
                g.drawString("GAME OVER", 310,310);
                //
                g.setColor(Color.white);
                g.setFont(new Font("DIALOG", Font.BOLD, 25));
                g.drawString("Press Enter to play", 360,360);
            }


        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right_direction){
            for(int d = snakesLength - 1; d >= 0; d--){
                y_length[d+1] = y_length[d];
            }
            for(int d = snakesLength; d >= 0; d--){
                if(d == 0){
                    x_length[d] += 25;
                } else {
                    x_length[d] = x_length[d-1];
                }
                if(x_length[d] > 850){
                    x_length[d] = 25;
                }
            }
            repaint();
        }
        if(left_direction){
            for(int d = snakesLength - 1; d >= 0; d--){
                y_length[d+1] = y_length[d];
            }
            for(int d = snakesLength; d >= 0; d--){
                if(d == 0){
                    x_length[d] -= 25;
                } else {
                    x_length[d] = x_length[d-1];
                }
                if(x_length[d] < 25){
                    x_length[d] = 850;
                }
            }
            repaint();
        }
        if(down_direction){
            for(int d = snakesLength - 1; d >= 0; d--){
                x_length[d+1] = x_length[d];
            }
            for(int d = snakesLength; d >= 0; d--){
                if(d == 0){
                    y_length[d] = y_length[d] + 25;
                } else {
                    y_length[d] = y_length[d-1];
                }
                if(y_length[d] > 625){
                    y_length[d] = 75;
                }
            }
            repaint();
        }
        if(up_direction){
            for(int d = snakesLength - 1; d >= 0; d--){
                x_length[d+1] = x_length[d];
            }
            for(int d = snakesLength; d >= 0; d--){
                if(d == 0){
                    y_length[d] = y_length[d] - 25;
                } else {
                    y_length[d] = y_length[d-1];
                }
                if(y_length[d] < 75){
                    y_length[d] = 625;
                }
            }
            repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move++;
            right_direction = true;
            if(!left_direction){
                right_direction = true;
            } else {
                right_direction = false;
                left_direction = true;
            }
            down_direction = false;
            up_direction = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            move++;
            up_direction = true;
            if(!down_direction){
                up_direction = true;
            } else {
                up_direction = false;
                down_direction = true;
            }
            right_direction = false;
            left_direction = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            move++;
            left_direction = true;
            if(!right_direction){
                left_direction = true;
            } else {
                left_direction = false;
                right_direction = true;
            }
            down_direction = false;
            up_direction = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            move++;
            down_direction = true;
            if(!up_direction){
                down_direction = true;
            } else {
                down_direction = false;
                up_direction = true;
            }
            right_direction = false;
            left_direction = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            move = 0;
            snakesLength = 3;
            score = 0;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
