package com.example.tuan_fpt.finddirection.logic;

import com.example.tuan_fpt.finddirection.OnMoveCharacter;
import com.example.tuan_fpt.finddirection.model.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Tuan-FPT on 13/04/2017.
 */

public class Map {

    private boolean canRun;

    public boolean isCanRun() {
        return canRun;
    }

    public void setCanRun(boolean canRun) {
        this.canRun = canRun;
    }

    private Cell myCell = new Cell(0,7);
    private int cellWidth;

    public Cell getMyCell() {
        return myCell;
    }

    public void setMyCell(Cell myCell) {
        this.myCell = myCell;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int[][] matrix = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,1,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,1,0,0,0,0},
            {1,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };

    public void drawLine(int position){
        Map.getInstance().setCanRun(false);
        for(int i = 0; i < matrix[0].length; i++){
            for(int j = 0; j < matrix.length ; j++){
                if(matrix[i][j] == 2){
                    matrix[i][j] = 0;
                }
            }
        }
        ArrayList<Cell> path = findLine(position);
        if(path == null) return;
        for(Cell cell : path){
            matrix[cell.getY()][cell.getX()] = 2;
        }
        onMoveCharacter.onMove(path);
    }
    public ArrayList<Cell> findLine(int position){

        Queue<Cell> queue = new LinkedList<>();
        int[][] distance = new int[matrix[0].length][matrix.length];
        Cell[][] parent = new Cell[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix[0].length; i++){
            for(int j = 0; j < matrix.length ; j++){
                distance[i][j] = 1000;
            }
        }

        Cell endCell = convertCell(position);
        Cell startCell = new Cell(myCell.getX(),myCell.getY());
        distance[startCell.getX()][startCell.getY()] = 0;
        queue.add(startCell);

        while (!queue.isEmpty()){
            Cell currentCell = queue.poll();
            int cx = currentCell.getX();
            int cy = currentCell.getY();

            if(cx == endCell.getX() && cy == endCell.getY()){
                ArrayList<Cell> path = new ArrayList<>();
                path.add(currentCell);
                while(currentCell.getX() != startCell.getX() || currentCell.getY() != startCell.getY()){
                    Cell parentCell = parent[currentCell.getX()][currentCell.getY()];
                    path.add(parentCell);
                    currentCell = parentCell;
                }
                Collections.reverse(path);
                return path;
            }

            Cell right = new Cell(cx + 1, cy);
            Cell left = new Cell(cx - 1, cy);
            Cell up = new Cell(cx, cy - 1);
            Cell down = new Cell(cx, cy + 1);
            if(checkCell(right) && distance[right.getX()][right.getY()] == 1000){
                distance[right.getX()][right.getY()] = distance[cx][cy] + 1;
                parent[right.getX()][right.getY()] = currentCell;
                queue.add(right);
            }
            if(checkCell(left) && distance[left.getX()][left.getY()] == 1000){
                distance[left.getX()][left.getY()] = distance[cx][cy] + 1;
                parent[left.getX()][left.getY()] = currentCell;
                queue.add(left);
            }
            if(checkCell(up) && distance[up.getX()][up.getY()] == 1000){
                distance[up.getX()][up.getY()] = distance[cx][cy] + 1;
                parent[up.getX()][up.getY()] = currentCell;
                queue.add(up);
            }
            if(checkCell(down) && distance[down.getX()][down.getY()] == 1000){
                distance[down.getX()][down.getY()] = distance[cx][cy] + 1;
                parent[down.getX()][down.getY()] = currentCell;
                queue.add(down);
            }
        }
        return null;
    }

    private boolean checkCell(Cell cell){ // check cell is inside map and cell is not Wall
        if(cell.getX() < 0 || cell.getX() >= matrix[0].length || cell.getY() < 0 || cell.getY() >= matrix.length){
           return false;
        }
        for(int i = 0; i < matrix[0].length; i++){
           for(int j = 0; j < matrix.length; j++){
               if(matrix[cell.getY()][cell.getX()] == 1){
                   return false;
               }
           }
       }
        return true;
    }

    public Cell convertCell(int position){
        int x = position % (matrix.length);
        int y = position / matrix[0].length;
        return new Cell(x,y);
    }

    private OnMoveCharacter onMoveCharacter;
    public static Map instance = new Map();
    public static void init(OnMoveCharacter onMoveCharacter){
        instance.onMoveCharacter = onMoveCharacter;
        instance.canRun = true;
    }
    public static Map getInstance(){
        return instance;
    }

}
