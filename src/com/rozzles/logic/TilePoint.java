package com.rozzles.logic;


/**
 * Created by rozz on 08/04/2016.
 * All rights reserved
 */
public class TilePoint extends PointXY {
    private PointXY xy = new PointXY();
    private int state;
    private boolean captured;

    public TilePoint(PointXY xy, int state) {
        this.xy = xy;
        this.state = state;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public PointXY getXy() {
        return xy;
    }

    public int getState() {
        return state;
    }

}
