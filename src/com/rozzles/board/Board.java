package com.rozzles.board;

import com.rozzles.logic.Move;
import com.rozzles.logic.PointXY;
import com.rozzles.logic.ai.IndexCaptures;
import com.rozzles.view.Window;

import javax.swing.*;

/**
 * Created by rozz on 03/04/2016.
 * All rights reserved
 */
public class Board {
    public int board[] = new int[65];
    private boolean blackTurn = false;
    private boolean skippedLastTurn;
    private Window windows[] = new Window[2];

    public Board(){
        for (int i = 0; i<64; i++){
            board[i] = 2;
        }
        setPos(3,3,0);
        setPos(4,4,0);
        setPos(3,4,1);
        setPos(4,3,1);

    }

    public boolean inBoard(int x, int y){
        return (x<=7 && x>=0 && y<=7 && y>=0);
    }

    public int[] iToXY(int i){
        int y = i/8;
        int x = i-y*8;
        return new int[]{x,y};
    }
    public int xyToi(int x, int y){
        return 8*y+x;
    }

    public int getPos(int x, int y){
        return board[xyToi(x,y)];
    }

    public void setPos(int x, int y, int state){
        board[xyToi(x,y)] = state;
    }

    public void nextTurn(){
        blackTurn = !blackTurn;
        newTurn();

    }
    private void newTurn(){
        IndexCaptures ic = new IndexCaptures(this, blackTurn);
        ic.createIndex();
        if (!ic.areThereCaptures()) {
            if(skippedLastTurn) endGame();
            skippedLastTurn = true;
            //JOptionPane.showMessageDialog(null, "Skipping turn, no captures available");
            nextTurn();
        } else {
            skippedLastTurn = false;
        }
    }
    private void endGame(){
        int[] score = getScore();
        JOptionPane.showMessageDialog(null, Window.getColouruc(getWinner(score)) + " wins! \nScore: White: " + score[0] + " Black: " + score[1]);
        System.exit(0);
    }

    private int[] getScore(){
        int black, white;
        black = white = 0;
        for (int i = 0; i<board.length; i++) {
            if (board[i] == 0) white++;
            if (board[i] == 1) black++;
        }
        return new int[]{white, black};
    }
    private boolean getWinner(int[] scoreArray){
        return scoreArray[0] < scoreArray[1];
    }

    public void tileClicked(int x, int y, boolean blackBoard){
        Move move = new Move(this);
        if(blackBoard == blackTurn) move.makeMove(x,y, blackTurn);
    }
    public boolean isBlackTurn(){
        return blackTurn;
    }
    public static int bint(boolean bool){
        return (bool) ? 1 : 0;

    }
    public void addWindow(Window window,int i){
        windows[i] = window;
    }
    public void refreshWindows(){
        windows[0].repaint();
        windows[1].repaint();
    }
    public void makeAIMove(boolean blackPlayer){
        if (blackPlayer == blackTurn) {
            IndexCaptures ic = new IndexCaptures(this, blackPlayer);
            PointXY p = ic.getNextMove();
            Move m = new Move(this);
            m.makeMove(p.getX(), p.getY(), blackPlayer);
        }
    }
}
