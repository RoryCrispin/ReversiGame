package com.rozzles.logic;

import com.rozzles.board.Board;

import java.util.ArrayList;

/**
 * Created by rozz on 06/04/2016.
 * All rights reserved
 */
public class Move {
    private Board board;
    public Move(Board board){
        this.board = board;
    }
    public void makeMove(int x, int y, boolean blackTurn){

        if (isMoveValid(x,y,blackTurn, true)){
            board.setPos(x,y, Board.bint(blackTurn));
            board.nextTurn();
            board.refreshWindows();
        }
    }

    public int getCapturesFromMove(int x, int y, boolean blackTurn, boolean doMakeMove){
        SearchObject so = new SearchObject(board);
        so.populateSearch(x,y);
        Search search = new Search(blackTurn);
        int captures  = search.getCaptures(so);
        if (doMakeMove){
            makeCapturesFromGroup(so.getSearchLists(),blackTurn);
        }
        return captures;
    }

    private void makeCapturesFromGroup(ArrayList<PointList> al, boolean blackTurn){
        for (PointList p : al){
            makeCapturesFromList(p,blackTurn);
        }
    }
    private void makeCapturesFromList(PointList pl, boolean blackTurn){
        makeCapturesFromHalfList(pl.getLower(), blackTurn);
        makeCapturesFromHalfList(pl.getUpper(), blackTurn);
    }
    private void makeCapturesFromHalfList(ArrayList<TilePoint> halfList, boolean blackTurn){
        if (halfList == null) return;
        halfList.stream().filter(TilePoint::isCaptured).forEach(tp -> board.setPos(tp.getXy().getX(), tp.getXy().getY(), Board.bint(blackTurn)));
    }

    public boolean isMoveValid(int x, int y, boolean blackTurn, boolean doMakeMove){

        return (board.getPos(x,y) == 2) && (getCapturesFromMove(x,y,blackTurn, doMakeMove) > 0);
    }


}
