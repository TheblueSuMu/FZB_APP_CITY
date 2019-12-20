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
import com.xcy.fzbcity.all.modle.PhotoBean;
import com.xcy.fzbcity.all.view.BannerPhotoActivity;

import java.util.ArrayList;
import java.util.List;

public class PhotoTileGridAdapter extends RecyclerView.Adapter<PhotoTileGridAdapter.ViewHolder>{

    private List<PhotoBean.DataBean.DataListBean> list;
    private Context context;
    private List<String> array = new ArrayList<>();
    private String url = "";
    private int index = -1;

    public void setArray(List<String> array) {
        this.array = array;
    }

    public PhotoTileGridAdapter(List<PhotoBean.DataBean.DataListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_tile_item_grid,
                viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        context = viewGroup.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(i).getImgPath()).into(viewHolder.imageAvatar);
        viewHolder.imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = FinalContents.getImageUrl() + list.get(i).getImgPath();
                for (int posotion = 0;posotion < array.size();posotion++){
                    if (array.get(posotion).equals(url)) {
                        index = posotion;
                        Log.i("下标","index￥："+index);
                        Log.i("下标","图片地址："+url);
                        Log.i("下标","列表图片地址："+array.get(posotion));
                    }
                }
                Intent intent = new Intent(context, BannerPhotoActivity.class);
                intent.putExtra("index",index);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            imageAvatar = (ImageView)itemView.findViewById(R.id.photo_tile_item_grid_pic);
        }
    }
}
