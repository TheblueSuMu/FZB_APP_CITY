package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ReportProcessDetailsBean;

import java.util.List;

public class ReviewTheSuccessPhoneAdapter extends RecyclerView.Adapter<ReviewTheSuccessPhoneAdapter.ViewHolder>{
    private List<ReportProcessDetailsBean.DataBean.AttacheListBean> list;
    private Context context;

    public ReviewTheSuccessPhoneAdapter(List<ReportProcessDetailsBean.DataBean.AttacheListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reviewthesuccess_phone, parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.item_reviewthesuccess_phone_title.setText(list.get(position).getName());
        holder.item_reviewthesuccess_phone_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(position).getPhone()));//跳转到拨号界面，同时传递电话号码
                context.startActivity(dialIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_reviewthesuccess_phone_title;
        ImageView item_reviewthesuccess_phone_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_reviewthesuccess_phone_img = itemView.findViewById(R.id.item_reviewthesuccess_phone_img);
            item_reviewthesuccess_phone_title = itemView.findViewById(R.id.item_reviewthesuccess_phone_title);
        }
    }
}