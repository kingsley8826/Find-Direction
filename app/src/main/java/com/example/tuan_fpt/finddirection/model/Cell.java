package com.example.tuan_fpt.finddirection.model;

/**
 * Created by Tuan-FPT on 18/04/2017.
 */

public class Cell {
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equal(Cell cell){
        if(this.x == cell.getX() && this.y == cell.getY()){
            return true;
        }
        return false;
    }
    public static Direction getDirection(Cell currentCell, Cell nextCell){
        if(currentCell.getX() == nextCell.getX()){
            if(nextCell.getY() > currentCell.getY()){
                return Direction.DOWN;
            }else {
                return Direction.UP;
            }
        }else if(currentCell.getY() == nextCell.getY()){
            if(nextCell.getX() > currentCell.getX()){
                return Direction.RIGHT;
            }else {
                return Direction.LEFT;
            }
        }else {
            return null;
        }
    }
}
