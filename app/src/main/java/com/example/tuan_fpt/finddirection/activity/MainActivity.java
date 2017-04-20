package com.example.tuan_fpt.finddirection.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import com.example.tuan_fpt.finddirection.OnMoveCharacter;
import com.example.tuan_fpt.finddirection.R;
import com.example.tuan_fpt.finddirection.UpdateRecyclerViewListener;
import com.example.tuan_fpt.finddirection.adapter.ItemAdapter;
import com.example.tuan_fpt.finddirection.logic.Map;
import com.example.tuan_fpt.finddirection.model.Cell;
import com.example.tuan_fpt.finddirection.model.Character;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements UpdateRecyclerViewListener,OnMoveCharacter{

    RecyclerView recyclerView;
    Character character = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        Map.init(this);
        character = (Character) findViewById(R.id.charac);
        float screenWidth = getDisplayMetrics().widthPixels;
        int cellWith = (int) (screenWidth / Map.getInstance().matrix[0].length - 1);
        Map.getInstance().setCellWidth(cellWith);
        RelativeLayout.LayoutParams ivRocketParams = (RelativeLayout.LayoutParams) character.getLayoutParams();
        ivRocketParams.width = cellWith;
        ivRocketParams.height = cellWith;
        character.setLayoutParams(ivRocketParams);
        character.setLocation(Map.getInstance().getMyCell());

    }

    private void setupUI() {
        recyclerView = (RecyclerView) findViewById(R.id.rvRecycler);
        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setUpdateRecyclerViewListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 8);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void updateRecyclerView() {
        setupUI();
    }

    // get screen metrics
    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    @Override
    public void onMove(ArrayList<Cell> path) {
        character.move(path);
    }
}
