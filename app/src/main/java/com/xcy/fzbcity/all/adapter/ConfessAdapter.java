package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.FindTradeBean;

import java.util.List;

public class ConfessAdapter extends RecyclerView.Adapter<ConfessAdapter.ConfessViewHolder>{
    List<FindTradeBean.DataBean.ShowDataBean> list;

    public ConfessAdapter(List<FindTradeBean.DataBean.ShowDataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ConfessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_confess, null);
        return new ConfessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfessViewHolder holder, int position) {
        holder.item_confess_title.setText(list.get(position).getKey());
        holder.item_confess_content.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ConfessViewHolder extends RecyclerView.ViewHolder{
        TextView item_confess_title;
        TextView item_confess_content;

        public ConfessViewHolder(@NonNull View itemView) {
            super(itemView);
            item_confess_title = itemView.findViewById(R.id.item_confess_title);
            item_confess_content = itemView.findViewById(R.id.item_confess_content);
        }
    }
}