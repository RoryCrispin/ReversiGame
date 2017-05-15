package com.rozzles.logic.ai;

import com.rozzles.board.Board;
import com.rozzles.logic.Move;
import com.rozzles.logic.PointXY;

/**
 * Created by rozz on 10/04/2016.
 * All rights reserved
 */
public class IndexCaptures {
    private Board board;
    private boolean blackTurn;
    private int[] index = new int[65];
    public IndexCaptures(Board board, boolean blackTurn){
        this.board = board;
        this.blackTurn = blackTurn;
        createIndex();
    }

    public void createIndex(){
        for(int i=0;i<index.length;i++){
            Move move = new Move(board);
            if (board.getPos(board.iToXY(i)[0],board.iToXY(i)[1]) == 2){ //TODO workaround
            index[i] = move.getCapturesFromMove(board.iToXY(i)[0],board.iToXY(i)[1],blackTurn,false );
        }
    }
    }
    public boolean areThereCaptures(){
        return getMax() > 0;
    }

    private int getMax(){
        int g = 0;
        for (int anIndex : index) {
            if (anIndex > g) {
                g = anIndex;
            }
        }
        return g;
    }
    public PointXY getNextMove(){
        PointXY p = new PointXY();
        int g = 0;
        for (int i = 0; i<index.length; i++){
            if (index[i] > g) {
                g = index[i];
                p.setLocation(board.iToXY(i)[0],board.iToXY(i)[1] );
            }
        }
        return p;
    }

}
