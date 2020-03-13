package com.xcy.fzbcity.all.adapter;

import android.content.Context;
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
import com.xcy.fzbcity.all.modle.CustomerVisitorStatisticsBean;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.List;

public class VisitorsToRecordAdapter extends RecyclerView.Adapter<VisitorsToRecordAdapter.VisitorsToRecordViewHolder>{
    private List<CustomerVisitorStatisticsBean.DataBean.RowsBean> list;
    private View view;
    private Context context;

    public VisitorsToRecordAdapter(List<CustomerVisitorStatisticsBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public VisitorsToRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_visitors_to_record, parent, false);
        context = parent.getContext();
        return new VisitorsToRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorsToRecordViewHolder holder, int position) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getCustomerImg()).into(holder.adapter_visitors_to_record_image);
        holder.adapter_visitors_to_record_visitor_name.setText(list.get(position).getVisitorName());
        holder.adapter_visitors_to_record_address.setText(list.get(position).getVisitorAddress());
        holder.adapter_visitors_to_record_project_name.setText(list.get(position).getProjectName());
        holder.adapter_visitors_to_record_time.setText("访问时间："+list.get(position).getVisitorStart()+" "+list.get(position).getVisitorDuration()+"."+ list.get(position).getNetworkType()+".浏览"+list.get(position).getVisitTimes()+"次项目"+"\n"+"结束时间："+list.get(position).getVisitorEnd());
        if (list.get(position).getBrowseMode().equals("1")) {
            holder.adapter_visitors_to_record_hint.setText("通过红包推广在小程序中浏览");
        } else if (list.get(position).getBrowseMode().equals("2")) {
            holder.adapter_visitors_to_record_hint.setText("通过小程序分享在小程序中浏览");
        } else if (list.get(position).getBrowseMode().equals("3")) {
            holder.adapter_visitors_to_record_hint.setText("通过官方引流在小程序中浏览");
        }

        holder.adapter_visitors_to_record_hint.setText("通过"+list.get(position).getBrowseMode()+"在小程序中浏览");
        if (list.get(position).getGender().equals("男")) {
            holder.adapter_visitors_to_record_gender.setImageResource(R.mipmap.adapter_visitors_to_record_gender_select_nan_image);
        } else if (list.get(position).getGender().equals("女")) {
            holder.adapter_visitors_to_record_gender.setImageResource(R.mipmap.adapter_visitors_to_record_gender_select_nv_image);
        }
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getEstateImg()).into(holder.adapter_visitors_to_record_project_image);
        holder.adapter_visitors_to_record_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showLongToast(context,"即将进入聊天界面!");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VisitorsToRecordViewHolder extends RecyclerView.ViewHolder{
        ImageView adapter_visitors_to_record_image;
        TextView adapter_visitors_to_record_visitor_name;
        ImageView adapter_visitors_to_record_gender;
        TextView adapter_visitors_to_record_address;
        ImageView adapter_visitors_to_record_chat;
        ImageView adapter_visitors_to_record_project_image;
        TextView adapter_visitors_to_record_project_name;
        TextView adapter_visitors_to_record_time;
        TextView adapter_visitors_to_record_hint;

        public VisitorsToRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_visitors_to_record_image = itemView.findViewById(R.id.adapter_visitors_to_record_image);
            adapter_visitors_to_record_visitor_name = itemView.findViewById(R.id.adapter_visitors_to_record_visitor_name);
            adapter_visitors_to_record_gender = itemView.findViewById(R.id.adapter_visitors_to_record_gender);
            adapter_visitors_to_record_address = itemView.findViewById(R.id.adapter_visitors_to_record_address);
            adapter_visitors_to_record_chat = itemView.findViewById(R.id.adapter_visitors_to_record_chat);
            adapter_visitors_to_record_project_image = itemView.findViewById(R.id.adapter_visitors_to_record_project_image);
            adapter_visitors_to_record_project_name = itemView.findViewById(R.id.adapter_visitors_to_record_project_name);
            adapter_visitors_to_record_time = itemView.findViewById(R.id.adapter_visitors_to_record_time);
            adapter_visitors_to_record_hint = itemView.findViewById(R.id.adapter_visitors_to_record_hint);
        }
    }

}
