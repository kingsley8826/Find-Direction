package com.example.tuan_fpt.finddirection.model;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Tuan-FPT on 19/04/2017.
 */

public class Item extends TextView{
    public Item(Context context) {
        super(context);
    }

    public Item(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Item(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = widthSize;
        int heightMode = MeasureSpec.EXACTLY;
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,heightMode);
        super.onMeasure(widthMeasureSpec,newHeightMeasureSpec);
    }
}
