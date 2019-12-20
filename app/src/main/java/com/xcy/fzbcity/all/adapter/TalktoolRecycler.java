package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.xcy.fzbcity.all.modle.ProjectDetailsBean;
import com.xcy.fzbcity.all.view.ProjectPointsActivity;
import com.xcy.fzbcity.all.view.ProjectSellingPointsnActivity;
import com.xcy.fzbcity.all.view.ProjectVideoActivity;
import com.xcy.fzbcity.all.view.PurchaseNoticeActivity;

import java.util.List;

public class TalktoolRecycler extends RecyclerView.Adapter<TalktoolRecycler.ViewHolder>{
    private Context context;
    private List<ProjectDetailsBean.DataBean.TalkToolDataBean> commissionlist;

    public TalktoolRecycler(List<ProjectDetailsBean.DataBean.TalkToolDataBean> commissionlist) {
        this.commissionlist = commissionlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_talktool,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(FinalContents.getImageUrl()+commissionlist.get(position).getImg()).into(holder.imagepic);
        holder.nameText.setText(commissionlist.get(position).getTypeName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (commissionlist.get(position).getType().equals("0")) {
//                    Toast.makeText(context, "跳转到项目卖点", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ProjectSellingPointsnActivity.class);
                    context.startActivity(intent);
                }else if (commissionlist.get(position).getType().equals("1")) {
//                    Toast.makeText(context, "跳转到项目海报", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ProjectPointsActivity.class);
                    context.startActivity(intent);
                }else if (commissionlist.get(position).getType().equals("2")) {
//                    Toast.makeText(context, "跳转到项目视频", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ProjectVideoActivity.class);
                    context.startActivity(intent);
                }else if (commissionlist.get(position).getType().equals("3")) {
//                    Toast.makeText(context, "跳转到购房需知", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, PurchaseNoticeActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return commissionlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAvatar;
        ImageView imagepic;
        TextView nameText;


        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            imageAvatar = (ImageView)itemView.findViewById(R.id.talktool_intent);
            imagepic = (ImageView)itemView.findViewById(R.id.talktool_pic);
            nameText =(TextView) itemView.findViewById(R.id.talktool_typename);

        }
    }
}
