package com.rozzles.logic;

import java.util.ArrayList;

/**
 * Created by rozz on 06/04/2016.
 * All rights reserved
 */
public class PointList {
    //private int upper[];
//    private TilePoint lower[];
  //  private TilePoint upper[];

    private ArrayList<TilePoint> upper;
    private  ArrayList<TilePoint> lower;

    public PointList() {
        upper = new ArrayList<TilePoint>();
        lower = new ArrayList<TilePoint>();
//       PointXY pp =  new PointXY();
//        pp.setLocation(0,0);
//        TilePoint tp = new TilePoint(pp, 4 );
//        upper.add(tp);
//        lower.add(tp); //TODO this pads out the edges of the lists to they are never null
    }

    public void setUpper(ArrayList<TilePoint> upper) {
        this.upper = upper;
    }

    public void setLower(ArrayList<TilePoint> lower) {
        this.lower = lower;
    }

    public TilePoint getUpper(int pos) {
        return upper.get(pos);
    }

    public TilePoint getLower(int pos) {
        return lower.get(pos);
    }
    public ArrayList<TilePoint> getUpper() {
        return upper;
    }

    public ArrayList<TilePoint> getLower() {
        return lower;
    }

    public void addPoint(TilePoint tp, boolean upperList){
        if (upperList) {upper.add(tp); } else {lower.add(tp);}

    }

//    public int sum(){
//        int sum;
//        for (TilePoint t : upper){
//            sum = sum
//        }
//        return 0;
//    }


//    public void printList(){
//        System.out.printf("Lower: ");
//        for (TilePoint i: lower){
//            System.out.printf("%d, ", i);
//        }
//        System.out.printf("\nUpper: ");
//        for (TilePoint i: upper){
//            System.out.printf("%d, ", i);
//        }
//    }

}
