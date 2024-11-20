package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
public class Game extends MouseAdapter {

    public static int tileCol;
    public static int tileRow;
    public static int tileIndex;

    private static boolean firstClick = true;

    public static boolean placeFlag = false;

    private static boolean gameOver = false;
    private static boolean playerWon = false;

    static Board board = new Board();

    public Game(Board board) {
        this.board = board;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        tileCol = e.getX() / board.tileSize;
        tileRow = e.getY() / board.tileSize;

        tileIndex = tileRow * 10 + tileCol;


        if (!gameOver) {
            if(playerWon()){
                playerWon = true;
                gameOver = true;
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                if(firstClick){
                    placeBombs(Board.gamingBoard, tileIndex);
                    firstClick = false;
                }
                if(board.board[tileIndex] == 'F'){
                    return;
                }
                redrawBoard(tileRow, tileCol);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if(board.board[tileIndex] == 'F'){
                    board.board[tileIndex] = ' ';
                }else if(board.board[tileIndex] == ' '){
                    board.board[tileIndex] = 'F';
                }
            }
        } else {
            if (playerWon) {
                System.out.println("You won!");
            } else {
                System.out.println("You lost!");
            }
        }
        
        board.repaint();
    }

    public static boolean playerWon(){
        int count = 0;
        for(int i=0; i<Board.board.length; i++){
            if(Board.board[i] != 'B' && Board.board[i] != ' '){
                count++;
            }
        }
        if(count == 99){
            return true;
        }
        return false;
    }


    public void redrawBoard(int tileRow, int tileCol){
        int tileIndex = tileRow * 10 + tileCol;
        if(Board.gamingBoard[tileIndex] =='0'){
            floodFill(tileRow, tileCol);
        }
        Board.board[tileIndex] = Board.gamingBoard[tileIndex];
        if(Board.board[tileIndex] == 'B'){
            gameOver = true;
        }
    }

    public void floodFill(int tileRow, int tileCol) {
        int tileIndex = tileRow * 10 + tileCol;
        int width = 10;
        int height = 10;

        if (tileRow < 0 || tileCol < 0 || tileRow >= height || tileCol >= width || Board.gamingBoard[tileIndex] == 'B' || Board.board[tileIndex] == 'E') {
            return;
        }

        Board.board[tileIndex] = Board.gamingBoard[tileIndex];

        if (Board.gamingBoard[tileIndex] != '0') {
            return;
        }

        Board.board[tileIndex] = 'E';

        floodFill(tileRow - 1, tileCol);
        floodFill(tileRow + 1, tileCol);
        floodFill(tileRow, tileCol - 1);
        floodFill(tileRow, tileCol + 1);
    }
    public static void placeBombs(char[] board, int tileIndex) {
        int bombs = 10;
        Set<Integer> bombPositions = new HashSet<>();

        while (bombPositions.size() < bombs) {
            int random = (int) (Math.random() * 100);
            if (random != tileIndex && !bombPositions.contains(random)) {
                bombPositions.add(random);
            }
        }

        for (int position : bombPositions) {
            board[position] = 'B';
        }

        placeTilesValues(board);
    }

    public static void placeTilesValues(char[] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 'B') continue;

            int count = 0;
            int width = 10;

            if (i >= width) {
                if (i % width != 0 && board[i - width - 1] == 'B') count++;
                if (board[i - width] == 'B') count++;
                if (i % width != width - 1 && board[i - width + 1] == 'B') count++;
            }

            if (i % width != 0 && board[i - 1] == 'B') count++;
            if (i % width != width - 1 && board[i + 1] == 'B') count++;
            if (i < board.length - width) {
                if (i % width != 0 && board[i + width - 1] == 'B') count++;
                if (board[i + width] == 'B') count++;
                if (i % width != width - 1 && board[i + width + 1] == 'B') count++;
            }


            board[i] = (char) (count + '0');

        }
    }

    public static void resetGame(){
        gameOver = false;
        playerWon = false;
        firstClick = true;
        Board.gamingBoard = new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
        Board.board = new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
                ' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
        board.repaint();
    }

}
