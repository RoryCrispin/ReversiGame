package com.rozzles.view;


import com.rozzles.board.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by rozz on 03/04/2016.
 * All rights reserved
 */
public class ReversiTile extends ColorButton implements MouseListener{
    private int state;
    private int x, y;
    BufferedImage image[] = new BufferedImage[3];
    private Board board;
    private boolean blackBoard;


    public ReversiTile(Board board, int x, int y, boolean blackBoard){
        super(50,50, Color.GREEN);

        this.blackBoard = blackBoard;
        addMouseListener(this);
         this.board = board;
        this.x = x; this.y=y;
        try {
            image[0] = ImageIO.read(new FileInputStream("res/white.png") );
            image[1] = ImageIO.read(new FileInputStream("res/black.png") );
            image[2] = ImageIO.read(new FileInputStream("res/empty.png") );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchState(){
        this.state = board.getPos(x,y);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        fetchState();
        g.drawImage(image[state],0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        handleMoseClick();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void handleMoseClick(){
        board.tileClicked(x,y, blackBoard);
    }
}

