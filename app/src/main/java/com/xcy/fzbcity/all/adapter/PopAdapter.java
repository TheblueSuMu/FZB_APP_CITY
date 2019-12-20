package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.utils.DataBase;

import java.util.List;

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.ViewHolder> {
    private List<DataBase> list;
    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    public PopAdapter(List<DataBase> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_item_user, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.cityName.setText(list.get(position).getUserName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickLisenter != null){
                    FinalContents.setDelete("删除");
                    onItemClickLisenter.onItemClick(position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLisenter != null){
                    FinalContents.setDelete("填充");
                    onItemClickLisenter.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView cityName;
        ImageView delete;
        public ViewHolder(View itemView) {
            super(itemView);
            cityName =(TextView) itemView.findViewById(R.id.pop_item_cityname);
            delete = itemView.findViewById(R.id.user_delete);
        }
    }
}