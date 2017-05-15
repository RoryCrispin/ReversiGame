package com.rozzles.logic;

import com.rozzles.board.Board;
import java.util.ArrayList;

/**
 * Created by rozz on 06/04/2016.
 * All rights reserved
 */
public class Search {
    private boolean blackPlayer;

    public Search(boolean blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public int getCaptures(SearchObject so) {
        so.setHorizontal(setCaptureFlagList(so.getHorizontal()));
        so.setVertical(setCaptureFlagList(so.getVertical()));
        so.setBackslash(setCaptureFlagList(so.getBackslash()));
        so.setForwardslash(setCaptureFlagList(so.getForwardslash()));

        int totalCaptures = 0;
        for (PointList pl : so.getSearchLists()) {
            totalCaptures = totalCaptures + countCapturesInList(pl);
        }
//        System.out.printf("Captures == %d\n", totalCaptures);

        return totalCaptures;
    }


    private PointList setCaptureFlagList(PointList list) {
        ArrayList<TilePoint> up;
        ArrayList<TilePoint> low;
        try {
            up = setCaptureFlagLine(list.getUpper());
            list.setUpper(up);

        } catch (Exception e) {
            //e.printStackTrace();
        }
        try {
            low = setCaptureFlagLine(list.getLower());
            list.setLower(low);

        } catch (Exception e) {
           // e.printStackTrace();
        }
        return list;
    }

    private ArrayList<TilePoint> setCaptureFlagLine(ArrayList<TilePoint> tp) {
        int i = 0;
        try {
            while (tp.size() != 0 && tp.get(i).getState() == Board.bint(!blackPlayer)) {
                tp.get(i).setCaptured(true);
                i++;
            }


            if (tp.get(i).getState() == Board.bint(blackPlayer) && tp.get(i).getState() != 2) {
                return tp;
            } else {
                for (TilePoint t : tp) {
                    t.setCaptured(false);
                }
                return tp;
            }
        } catch (Exception e) {
            return null;
        }

    }

    private int countCapturesInList(PointList pl) {
        int count = 0;
        try {
            count = count + countCapturesInLine(pl.getUpper());
        } catch (Exception e) {
           // e.printStackTrace();
        }
        try {
            count = count + countCapturesInLine(pl.getLower());
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return count;
    }

    private int countCapturesInLine(ArrayList<TilePoint> tp) {
        if (tp == null) {
            return 0;
        }
        int i = 0;

        for (TilePoint p : tp) {

            if (p.isCaptured()) {
                i++;
            }
        }
        return i;
    }
}
