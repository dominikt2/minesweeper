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
        frame.getContentPane().setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton button = new JButton("Restart");
        button.addActionListener(e ->
                Game.resetGame()
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(button, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(board, gbc);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
