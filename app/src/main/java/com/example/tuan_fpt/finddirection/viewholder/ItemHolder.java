package com.example.tuan_fpt.finddirection.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuan_fpt.finddirection.model.Cell;
import com.example.tuan_fpt.finddirection.R;
import com.example.tuan_fpt.finddirection.logic.Map;
import com.example.tuan_fpt.finddirection.model.Item;

/**
 * Created by Tuan-FPT on 12/04/2017.
 */

public class ItemHolder extends RecyclerView.ViewHolder {

    Item item;
    public ItemHolder(View itemView) {
        super(itemView);
        item = (Item) itemView.findViewById(R.id.item);
    }

    public void changeColor(int position) {
        Cell cell = Map.getInstance().convertCell(position);
        if(Map.getInstance().matrix[cell.getY()][cell.getX()] == 1){
            item.setBackgroundColor(itemView.getResources().getColor(R.color.colorRed));
        }else if(Map.getInstance().matrix[cell.getY()][cell.getX()] == 2){
            item.setBackgroundColor(itemView.getResources().getColor(R.color.colorYellow));
        }
    }
}
