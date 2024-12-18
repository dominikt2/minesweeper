package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Board extends JPanel{

    Game game;
    public int tileSize = 40;
    static int cols = 10;
    static int rows = 10;

    static char[] board = new char[Main.boardSize*Main.boardSize];

    static char[] gamingBoard = new char[Main.boardSize*Main.boardSize];

    public Board(){
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        loadSpriteSheet();

    }
    BufferedImage sheet;
    protected int sheetScale;

    private void loadSpriteSheet() {
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/main/resources/tileimage.png"));
            sheetScale = sheet.getWidth() / 12;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Image sprite;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++) {
                char tileType = board[r * cols + c];
                if(tileType == '\0'){
                    sprite = sheet.getSubimage(0, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == '0' || tileType == 'E'){
                    sprite = sheet.getSubimage(2 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == 'B') {
                    sprite = sheet.getSubimage(1 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == '1') {
                    sprite = sheet.getSubimage(3 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == '2') {
                    sprite = sheet.getSubimage(4 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == '3') {
                    sprite = sheet.getSubimage(5 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == '4') {
                    sprite = sheet.getSubimage(6 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == '5') {
                    sprite = sheet.getSubimage(7 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == '6') {
                    sprite = sheet.getSubimage(8 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == '7') {
                    sprite = sheet.getSubimage(9 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == '8') {
                    sprite = sheet.getSubimage(10 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }else if(tileType == 'F') {
                    sprite = sheet.getSubimage(11 * sheetScale, 0, tileSize, tileSize);
                    g2d.drawImage(sprite, c * tileSize, r * tileSize, null);
                }
            }
        }
    }


}
