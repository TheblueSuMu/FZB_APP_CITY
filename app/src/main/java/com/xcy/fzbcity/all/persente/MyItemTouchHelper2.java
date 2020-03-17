package com.xcy.fzbcity.all.persente;


import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.adapter.HousingSupermarketHotListAdapter;

import java.util.Collections;

/**
 * Created by francisbingo on 2019-08-22 10:34
 * <p>
 * 实现RecyclerView 上线拖拽工具类
 */
public class MyItemTouchHelper2 extends ItemTouchHelper.Callback {
    private HousingSupermarketHotListAdapter adapter;

    public MyItemTouchHelper2(HousingSupermarketHotListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(ItemTouchHelper.DOWN | ItemTouchHelper.UP, 0);
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(adapter.getList(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(adapter.getList(), i, i - 1);
            }
        }
        recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
        }

    }



}
