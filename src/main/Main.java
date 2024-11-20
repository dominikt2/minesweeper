package main;
import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {



        Board board = new Board();
        Game game = new Game(board);



        board.addMouseListener(game);


        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());

        frame.setMinimumSize(new Dimension(600, 600));
        frame.add(board);

        frame.getContentPane().setBackground(Color.BLACK);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}