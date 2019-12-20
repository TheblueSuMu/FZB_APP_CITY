package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ReportBean;

import java.util.List;

public class ReportItemAdapter extends RecyclerView.Adapter<ReportItemAdapter.ViewHolder>{
    List<ReportBean.DataBean.ProjectLabelListBean> list;

    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(CheckBox checkBox,int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    public ReportItemAdapter(List<ReportBean.DataBean.ProjectLabelListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report_adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.item_report_feature.setText(list.get(position).getLable());
        holder.item_report_feature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickLisenter != null){
                    onItemClickLisenter.onItemClick(holder.item_report_feature,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox item_report_feature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_report_feature = itemView.findViewById(R.id.item_report_feature);
        }
    }
}
