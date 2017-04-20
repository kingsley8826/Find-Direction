package com.example.tuan_fpt.finddirection.model;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.tuan_fpt.finddirection.logic.Map;

import java.util.ArrayList;

/**
 * Created by Tuan-FPT on 19/04/2017.
 */

public class Character extends ImageView {

    private Direction straightDirection = null;
    private Direction newDirection = null;
    private ObjectAnimator moveAnimator = null;

    public Character(Context context) {
        super(context);
    }

    public Character(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Character(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLocation(Cell cell){
        int x = cell.getX() * (Map.getInstance().getCellWidth() + 1);
        int y = cell.getY() * (Map.getInstance().getCellWidth() + 1);
        this.setX(x);
        this.setY(y);
    }

    private void startAnimation(Direction newDirection, int numberCell, final ArrayList<Cell> newPath){
        if(newDirection == Direction.UP) {
            moveAnimator = ObjectAnimator.ofFloat(this, "y", this.getY(), this.getY() - Map.getInstance().getCellWidth() * numberCell);
        }else if(newDirection == Direction.DOWN) {
            moveAnimator = ObjectAnimator.ofFloat(this, "y", this.getY(), this.getY() + Map.getInstance().getCellWidth() * numberCell);
        }else if(newDirection == Direction.LEFT) {
            moveAnimator = ObjectAnimator.ofFloat(this, "x", this.getX(), this.getX() - Map.getInstance().getCellWidth() * numberCell);
        }else if(newDirection == Direction.RIGHT) {
            moveAnimator = ObjectAnimator.ofFloat(this, "x", this.getX(), this.getX() + Map.getInstance().getCellWidth() * numberCell);
        }
        moveAnimator.setDuration(500 * numberCell);
        moveAnimator.start();
        moveAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                move(newPath);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    public void move(ArrayList<Cell> path){

        if(path.size() <= 1){
            Map.getInstance().setMyCell(path.get(0));
            Map.getInstance().setCanRun(true);
            return;
        }

        int numberCell = 1;
        Cell currentCell = path.get(0);
        Cell nextCell = path.get(1);
        newDirection = Cell.getDirection(currentCell,nextCell);
        straightDirection = newDirection;

        while (newDirection == straightDirection) {
            path.remove(0);
            if(path.size() <= 1){
                numberCell++;
                break;
            }
            currentCell = path.get(0);
            nextCell = path.get(1);
            newDirection = Cell.getDirection(currentCell, nextCell);
            numberCell++;
        }
        numberCell--;
        startAnimation(straightDirection,numberCell, path);
    }
}
