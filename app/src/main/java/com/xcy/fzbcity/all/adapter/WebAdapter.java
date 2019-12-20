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

public class WebAdapter extends RecyclerView.Adapter<WebAdapter.ViewHolder>{
    private List<String> list;
    private Context context;
    private String ImageUrl = "";

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public WebAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_web_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position)).into(holder.item_web_adapter_img);
        Log.i("图片加载",FinalContents.getImageUrl()+ list.get(position));
        if (ImageUrl.equals("")) {
        }else {
            holder.item_web_adapter_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, BigPhotoActivity.class);
                    intent.putExtra("index",position);
                    intent.putExtra("bigPhotoimg", ImageUrl);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_web_adapter_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_web_adapter_img = itemView.findViewById(R.id.item_web_adapter_img);
        }
    }
}
