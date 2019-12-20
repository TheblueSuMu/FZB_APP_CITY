package com.xcy.fzbcity.project_side.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;

public class TheProjectEndRefuseAdapter extends RecyclerView.Adapter<TheProjectEndRefuseAdapter.TheProjectEndRefuseViewHolder> {


    @NonNull
    @Override
    public TheProjectEndRefuseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_side_item_refuse_the_project_end, parent, false);
        return new TheProjectEndRefuseViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TheProjectEndRefuseViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext()).load("").into(holder.item_refuse_the_project_end_img);

        holder.item_refuse_the_project_end_tv1.setText("");
        holder.item_refuse_the_project_end_tv2.setText("");
        holder.item_refuse_the_project_end_tv3.setText("");
        holder.item_refuse_the_project_end_tv4.setText("");

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TheProjectEndRefuseViewHolder extends RecyclerView.ViewHolder {

        ImageView item_refuse_the_project_end_img;
        TextView item_refuse_the_project_end_tv1;
        TextView item_refuse_the_project_end_tv2;
        TextView item_refuse_the_project_end_tv3;
        TextView item_refuse_the_project_end_tv4;

        public TheProjectEndRefuseViewHolder(@NonNull View itemView) {
            super(itemView);

            item_refuse_the_project_end_img = itemView.findViewById(R.id.item_refuse_the_project_end_img);
            item_refuse_the_project_end_tv1 = itemView.findViewById(R.id.item_refuse_the_project_end_tv1);
            item_refuse_the_project_end_tv2 = itemView.findViewById(R.id.item_refuse_the_project_end_tv2);
            item_refuse_the_project_end_tv3 = itemView.findViewById(R.id.item_refuse_the_project_end_tv3);
            item_refuse_the_project_end_tv4 = itemView.findViewById(R.id.item_refuse_the_project_end_tv4);

        }
    }

}
