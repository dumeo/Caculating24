package com.example.recyclerviewformal;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MyDecoration extends RecyclerView.ItemDecoration {
    private int interval = 0;
    public MyDecoration(int interval){
        this.interval = interval;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = interval;
    }


}



