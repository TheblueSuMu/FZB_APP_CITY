package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.view.BigPhotoActivity;

import java.util.List;

public class ReviewAdapterItemAdapter extends RecyclerView.Adapter<ReviewAdapterItemAdapter.ViewHolder>{

    List<String> list;
    private Context context;
    private String imgUrl;

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ReviewAdapterItemAdapter(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_adapter_item_recycler, parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        Log.i("图片","数据长度："+list.size());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.i("图片","数据图片："+list.get(position));
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position)).into(holder.item_review_adapter_item_recycler_img);
        holder.item_review_adapter_item_recycler_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BigPhotoActivity.class);
                intent.putExtra("index",position);
                intent.putExtra("bigPhotoimg",imgUrl);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item_review_adapter_item_recycler_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_review_adapter_item_recycler_img = itemView.findViewById(R.id.item_review_adapter_item_recycler_img);
        }
    }
}
