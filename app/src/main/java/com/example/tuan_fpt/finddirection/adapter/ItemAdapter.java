package com.example.tuan_fpt.finddirection.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tuan_fpt.finddirection.R;
import com.example.tuan_fpt.finddirection.UpdateRecyclerViewListener;
import com.example.tuan_fpt.finddirection.logic.Map;
import com.example.tuan_fpt.finddirection.viewholder.ItemHolder;


/**
 * Created by Tuan-FPT on 12/04/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder>{
    UpdateRecyclerViewListener updateRecyclerViewListener;

    public void setUpdateRecyclerViewListener(UpdateRecyclerViewListener updateRecyclerViewListener) {
        this.updateRecyclerViewListener = updateRecyclerViewListener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item,parent, false);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        holder.changeColor(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Map.getInstance().isCanRun()) {
                    Map.getInstance().drawLine(position);
                    if (updateRecyclerViewListener != null) {
                        updateRecyclerViewListener.updateRecyclerView();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Map.getInstance().matrix.length * Map.getInstance().matrix[0].length;
    }
}
