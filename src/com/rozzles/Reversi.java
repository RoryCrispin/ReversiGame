package com.rozzles;

import com.rozzles.board.Board;
import com.rozzles.view.Window;


public class Reversi {

    public static void main (String args[]){
        Reversi main = new Reversi();
        main.run();
    }

    private  void run(){
        Board board = new Board();
        Window black = new Window(board, true);
        black.displayView();
        Window white = new Window(board, false);
        white.displayView();
        board.addWindow(white,0);
        board.addWindow(black,1);

    }
}

