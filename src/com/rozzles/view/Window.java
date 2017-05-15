package com.rozzles.view;

import com.rozzles.board.Board;

import javax.swing.*;
import java.awt.*;


public class Window {

    private Board board;
    private JFrame frame;
    private boolean blackWindow;
    private JLabel label;


    public Window(Board board, boolean blackWindow){
        this.blackWindow = blackWindow;
        this.board = board;
    }
    ReversiTile tiles[];
    public void displayView() {
        tiles = new ReversiTile[65];
        frame = new JFrame(windowTitle(blackWindow));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        label = new JLabel(labelText(blackWindow));

        frame.add(label, BorderLayout.NORTH);
        JButton button = new JButton(buttonText(blackWindow));

        button.addActionListener(e -> board.makeAIMove(blackWindow));


        frame.add(button, BorderLayout.SOUTH);

        JPanel gd = new JPanel(new GridLayout(8, 8, 1, 1));

        int p=0;
        if (blackWindow) {
            frame.setLocation(450,0);
            for (int y = 0; y<8; y++){
                for (int x=0;x<8; x++){
                    p++;
                    tiles[p] = new ReversiTile(board, x,y, blackWindow);
                    //tiles[p].setState(0);
                    gd.add(tiles[p]);
                }
            }
        } else {
            for (int y = 7; y>=0; y--){
                for (int x=7;x>=0; x--){
                    p++;
                    tiles[p] = new ReversiTile(board, x,y, blackWindow);
                    //tiles[p].setState(0);
                    gd.add(tiles[p]);
                }
            }
        }


        frame.add(gd, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }
    public void repaint(){
        frame.repaint();
        label.setText(labelText(blackWindow));
    }
    private String windowTitle(boolean blackWindow){
        return "Reversi - "+ getColourlc(blackWindow) + " player";
    }
    private String getColourlc(boolean blackWindow){
       return (blackWindow) ?  "black" : "white";
    }
    public static String getColouruc(boolean blackWindow){
        return (blackWindow) ?  "Black" : "White";
    }

    private String buttonText(boolean blackWindow){
        return "Greedy AI (play " + getColourlc(blackWindow) + ")";
    }
    private String labelText(boolean blackWindow) {
        return getColouruc(blackWindow) + " Player " + turnText();
    }
    private String turnText(){
        if (board.isBlackTurn() == blackWindow){
            return "- Click to put piece ";
        } else return "- Not your turn";
    }



}