package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.NationBean;
import com.xcy.fzbcity.all.view.RecyclerViewActivity;

import java.util.List;

public class OverseaCityAdapter extends RecyclerView.Adapter<OverseaCityAdapter.ViewHolder>{
    private Context context;
    private List<NationBean.DataBean> beanList;

    public OverseaCityAdapter(List<NationBean.DataBean> beanList) {
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oversea_city_item,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.i("wds","这里有数据"+beanList.get(position).getNationImg());
        Glide.with(context).load(FinalContents.getImageUrl()+beanList.get(position).getNationImg()).into(holder.imageAvatar);
        holder.nameText.setText(beanList.get(position).getNationName());
        holder.nameText.setGravity(Gravity.CENTER);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecyclerViewActivity.class);
                intent.putExtra("nation",beanList.get(position).getNationName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAvatar;
        TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);
            imageAvatar = (ImageView)itemView.findViewById(R.id.oversea_image);
            nameText =(TextView) itemView.findViewById(R.id.oversea_name);

        }
    }

}
