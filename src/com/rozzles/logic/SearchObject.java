package com.rozzles.logic;

import com.rozzles.board.Board;

import java.util.ArrayList;

/**
 * Created by rozz on 06/04/2016.
 * All rights reserved
 */
public class SearchObject {
    private PointList horizontal = new PointList();
    private PointList vertical= new PointList();
    private PointList forwardslash= new PointList();
    private PointList backslash;
    private Board board;

    public SearchObject(Board board){
        this.board = board;
    }

    public void populateSearch(int x, int y){
        if (!board.inBoard(x,y)) {return;}
        horizontal   = fillList(x,y,1,0);
        vertical     = fillList(x,y,0,1);
        backslash    = fillList(x,y,1,1);
        forwardslash = fillList(x,y,-1,1);
    }

    public PointList getHorizontal() {
        return horizontal;
    }

    public PointList getVertical() {
        return vertical;
    }

    public PointList getForwardslash() {
        return forwardslash;
    }
    public ArrayList<PointList> getSearchLists(){
        ArrayList<PointList> lists = new ArrayList<>();
        lists.add(horizontal);
        lists.add(vertical);
        lists.add(backslash);
        lists.add(forwardslash);
        return lists;
    }

    public PointList getBackslash() {
        return backslash;
    }

    private PointList fillList(int x, int y, int x_delta, int y_delta) {
        PointList list = new PointList();
        return fillList(x,y,x_delta,y_delta, true, list);
    }

    public void setHorizontal(PointList horizontal) {
        this.horizontal = horizontal;
    }

    public void setVertical(PointList vertical) {
        this.vertical = vertical;
    }

    public void setForwardslash(PointList forwardslash) {
        this.forwardslash = forwardslash;
    }

    public void setBackslash(PointList backslash) {
        this.backslash = backslash;
    }

    private PointList fillList(int x, int y, int x_delta, int y_delta, boolean firstList, PointList list){
        int tx = x;
        int ty = y;
       // if (board.getPos(x,y) != 2) return null;
        for (int i = 0; i<8; i++){
            x = x+x_delta;
            y = y+y_delta;
            PointXY point = new PointXY();
            point.setLocation(x,y);

            if (board.inBoard(x,y)) {
                TilePoint tp = new TilePoint(point,board.getPos(x, y) );
                list.addPoint(tp, firstList);
            }
        }
        if (firstList) {
            return fillList(tx, ty, (-x_delta), (-y_delta), false, list);
        }
        return list;

    }
}
