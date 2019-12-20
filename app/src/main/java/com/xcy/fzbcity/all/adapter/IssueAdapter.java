package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.xcy.fzbcity.all.modle.ImgData;
import com.xcy.fzbcity.all.view.WebViewActivity;

import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder>{
    private Context context;
    private List<ImgData.DataBean> beanList;

    public IssueAdapter(List<ImgData.DataBean> beanList) {
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oversea_zhuanchang,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(FinalContents.getImageUrl()+beanList.get(position).getCoverImg()).into(holder.imageAvatar);
        holder.nameText.setText(beanList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setProjectID(beanList.get(position).getProject().getId());
                FinalContents.setNewID(beanList.get(position).getId());
                Log.i("详情","项目ID"+FinalContents.getProjectID());
                Log.i("详情","用户ID"+FinalContents.getUserID());
                Log.i("详情","用户ID"+FinalContents.getNewID());
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("webview",beanList.get(position).getContent());
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
            imageAvatar = (ImageView)itemView.findViewById(R.id.item_oversea_image);
            nameText =(TextView) itemView.findViewById(R.id.item_oversea_name);

        }
    }

}
