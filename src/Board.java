import javax.swing.*;
import java.awt.*;

public class Board {
    public static void main(String[] args) {
        JFrame board = new JFrame();
        SnakePlaying playSnake = new SnakePlaying();
        board.setBounds(10, 10, 910, 700);
        board.setBackground(Color.white);
        board.setVisible(true);
        board.setResizable(false);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.add(playSnake);
    }


}

