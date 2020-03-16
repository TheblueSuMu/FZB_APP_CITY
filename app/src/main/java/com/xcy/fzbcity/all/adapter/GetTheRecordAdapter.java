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
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.RedbagReceiveRecordBean;
import com.xcy.fzbcity.all.view.CirclOfFriendsAssistantAppletActivity;
import com.xcy.fzbcity.all.view.GetTheRecordParticularsActivity;

import java.util.List;

public class GetTheRecordAdapter extends RecyclerView.Adapter<GetTheRecordAdapter.GetTheRecordViewHolder>{
    private List<RedbagReceiveRecordBean.DataBean.RowsBean> list;
    private View view;
    private Context context;

    public GetTheRecordAdapter(List<RedbagReceiveRecordBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GetTheRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_get_the_record, parent, false);
        context = parent.getContext();
        return new GetTheRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetTheRecordViewHolder holder, int position) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getCustomerImg()).into(holder.adapter_get_the_record_image);


        holder.adapter_get_the_record_title.setText(list.get(position).getCustomerName());
        holder.adapter_get_the_record_price.setText(list.get(position).getDenomination());
        holder.adapter_get_the_record_time.setText(list.get(position).getClaimDate());
        holder.adapter_get_the_record_title.setText(list.get(position).getCustomerName());
        holder.adapter_get_the_record_name.setText(list.get(position).getProjectName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedEnvelopesAllTalk.setRedbagReceiveRecordList(list);
                RedEnvelopesAllTalk.setIndex(position);
                Intent intent = new Intent(context, GetTheRecordParticularsActivity.class);
                context.startActivity(intent);
            }
        });

        if (list.get(position).getAssistList().size() != 0) {
            if (list.get(position).getAssistList().size() == 1) {
                //  只有一人
                Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getAssistList().get(0).getCustomerImg()).into(holder.adapter_get_the_record_icon1);
                holder.adapter_get_the_record_icon1.setVisibility(View.VISIBLE);
                holder.adapter_get_the_record_icon2.setVisibility(View.GONE);
                holder.adapter_get_the_record_icon3.setVisibility(View.GONE);
                holder.adapter_get_the_record_icon4.setVisibility(View.GONE);
            } else if (list.get(position).getAssistList().size() == 2) {
                //  只有二人
                Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getAssistList().get(0).getCustomerImg()).into(holder.adapter_get_the_record_icon1);
                Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getAssistList().get(1).getCustomerImg()).into(holder.adapter_get_the_record_icon2);
                holder.adapter_get_the_record_icon1.setVisibility(View.VISIBLE);
                holder.adapter_get_the_record_icon2.setVisibility(View.VISIBLE);
                holder.adapter_get_the_record_icon3.setVisibility(View.GONE);
                holder.adapter_get_the_record_icon4.setVisibility(View.GONE);
            } else if (list.get(position).getAssistList().size() >= 3) {
                //  有三人或大于
                holder.adapter_get_the_record_icon1.setVisibility(View.VISIBLE);
                holder.adapter_get_the_record_icon2.setVisibility(View.VISIBLE);
                holder.adapter_get_the_record_icon3.setVisibility(View.VISIBLE);
                holder.adapter_get_the_record_icon4.setVisibility(View.VISIBLE);
                holder.adapter_get_the_record_icon1.setImageResource(R.mipmap.adapter_get_the_record_icon);
                Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getAssistList().get(0).getCustomerImg()).into(holder.adapter_get_the_record_icon2);
                Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getAssistList().get(1).getCustomerImg()).into(holder.adapter_get_the_record_icon3);
                Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getAssistList().get(2).getCustomerImg()).into(holder.adapter_get_the_record_icon4);
            }
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GetTheRecordViewHolder extends RecyclerView.ViewHolder{
        ImageView adapter_get_the_record_image;
        TextView adapter_get_the_record_title;
        TextView adapter_get_the_record_price;
        TextView adapter_get_the_record_time;
        ImageView adapter_get_the_record_icon1;
        ImageView adapter_get_the_record_icon2;
        ImageView adapter_get_the_record_icon3;
        ImageView adapter_get_the_record_icon4;
        TextView adapter_get_the_record_name;

        public GetTheRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_get_the_record_image = itemView.findViewById(R.id.adapter_get_the_record_image);
            adapter_get_the_record_title = itemView.findViewById(R.id.adapter_get_the_record_title);
            adapter_get_the_record_price = itemView.findViewById(R.id.adapter_get_the_record_price);
            adapter_get_the_record_time = itemView.findViewById(R.id.adapter_get_the_record_time);
            adapter_get_the_record_icon1 = itemView.findViewById(R.id.adapter_get_the_record_icon1);
            adapter_get_the_record_icon2 = itemView.findViewById(R.id.adapter_get_the_record_icon2);
            adapter_get_the_record_icon3 = itemView.findViewById(R.id.adapter_get_the_record_icon3);
            adapter_get_the_record_icon4 = itemView.findViewById(R.id.adapter_get_the_record_icon4);
            adapter_get_the_record_name = itemView.findViewById(R.id.adapter_get_the_record_name);
        }
    }
}
