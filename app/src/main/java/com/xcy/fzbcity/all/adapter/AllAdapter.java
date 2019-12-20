package com.xcy.fzbcity.all.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.AllViewHolder> {


    @NonNull
    @Override
    public AllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AllViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class AllViewHolder extends RecyclerView.ViewHolder {


        public AllViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
