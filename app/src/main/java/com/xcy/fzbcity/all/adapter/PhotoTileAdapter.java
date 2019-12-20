package com.xcy.fzbcity.all.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.PhotoBean;

import java.util.ArrayList;
import java.util.List;

public class PhotoTileAdapter extends RecyclerView.Adapter<PhotoTileAdapter.ViewHolder>{
    private Context context;
    private List<PhotoBean.DataBean> list;
    private List<String> array = new ArrayList<>();

    public void setArray(List<String> array) {
        this.array = array;
    }

    public PhotoTileAdapter(List<PhotoBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_tile_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @SuppressLint({"NewApi", "WrongConstant"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameText.setText(list.get(position).getTypeName());
        PhotoTileGridAdapter photoTileGridAdapter = new PhotoTileGridAdapter(list.get(position).getDataList(), context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        photoTileGridAdapter.setArray(array);
        holder.gridView.setLayoutManager(gridLayoutManager);
        holder.gridView.setNestedScrollingEnabled(false);
        holder.gridView.setAdapter(photoTileGridAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerView gridView;
        TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);
            gridView = itemView.findViewById(R.id.photo_tile_gv);
            nameText = itemView.findViewById(R.id.photo_tile_title);
        }
    }
}
