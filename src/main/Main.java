package main;

import java.awt.*;
import javax.swing.*;

import static main.Game.board;

public class Main {

    public static int boardSize = 10;
    static JLayeredPane layeredPane;
    static JFrame frame = new JFrame();
    static JPanel backgroundPanel = new JPanel();
    static JButton button = new JButton("RESET");

    static JComboBox<String> comboBox;
    public static void main(String[] args) {
        Game.bombs = 10;
        Board board = new Board();
        Game game = new Game(board);
        board.addMouseListener(game);

        layeredPane = new JLayeredPane(); // Inicjalizacja layeredPane
        layeredPane.setLayout(null);
        layeredPane.setPreferredSize(new Dimension(800, 800));

        // PANEL TŁA
        backgroundPanel.setBounds(0, 0, 800, 800);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

        // USTAWIENIA PLANSZY
        board.setBounds(200, 200, 1500, 1500);
        layeredPane.add(board, JLayeredPane.MODAL_LAYER);

        // USTAWIENIA PRZYCISKU RESET
        button.addActionListener(e -> Game.resetGame());
        button.setBounds(350, 100, 100, 30);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // LISTA ROZWIJANA
        String[] options = {"Easy - 10x10 grid - 10 bombs", "Medium - 16x16 grid - 40 bombs", "Hard - 20x20 grid - 75 bombs"};
        comboBox = new JComboBox<>(options);
        comboBox.setBounds(350, 150, 100, 30);
        comboBox.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            if (selectedOption.equals("Easy - 10x10 grid - 10 bombs")) {
                boardSize = 10;
                Game.bombs = 10;
                Board.cols = boardSize;
                Board.rows = boardSize;
            } else if (selectedOption.equals("Medium - 16x16 grid - 40 bombs")) {
                boardSize = 16;
                Game.bombs = 40;
                Board.cols = boardSize;
                Board.rows = boardSize;
            } else if (selectedOption.equals("Hard - 20x20 grid - 75 bombs")) {
                boardSize = 20;
                Game.bombs = 75;
                Board.cols = boardSize;
                Board.rows = boardSize;
            }
            setWindowSize();
            Game.resetGame();
        });
        layeredPane.add(comboBox, JLayeredPane.PALETTE_LAYER);

        // DODAJEMY PANEL DO RAMKI
        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);

        setWindowSize(); // Wywołanie przeniesione na koniec
    }
    public static void setWindowSize(){
        if(boardSize == 10) {
            board.setBounds(100, 100, 1500, 1500);
            layeredPane.add(board, JLayeredPane.MODAL_LAYER);

            frame.setSize(new Dimension(600, 600)); // ROZMIAR OKNA
            frame.setMinimumSize(new Dimension(600, 600)); // MINIMALNY ROZMIAR

            button.setBounds(250, 20, 100, 30);
            comboBox.setBounds(200, 70, 200, 30);

        } else if(boardSize == 16) {

            board.setBounds(70, 100, 1500, 1500);
            layeredPane.add(board, JLayeredPane.MODAL_LAYER);

            frame.setSize(new Dimension(100, 100)); // ROZMIAR OKNA
            frame.setMinimumSize(new Dimension(800, 800)); // MINIMALNY ROZMIAR

            button.setBounds(350, 10, 100, 30);
            comboBox.setBounds(300, 60, 200, 30); // Pozycja i rozmiar

        } else if(boardSize == 20) {

            board.setBounds(70, 100, 1500, 1500);
            layeredPane.add(board, JLayeredPane.MODAL_LAYER);

            frame.setSize(new Dimension(1000, 1000)); // ROZMIAR OKNA
            frame.setMinimumSize(new Dimension(1000, 1000)); // MINIMALNY ROZMIAR

            button.setBounds(400, 10, 100, 30);
            comboBox.setBounds(350, 60, 200, 30); // Pozycja i rozmiar

        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.revalidate();
        frame.repaint();
    }



}
