package com.xcy.fzbcity.project_side.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.BrokerBean;

import java.util.ArrayList;
import java.util.List;

public class TimeRangeAdapter extends RecyclerView.Adapter<TimeRangeAdapter.ViewHolder>{

    private List<BrokerBean.DataBean> list = new ArrayList<>();
    private Context context;
    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    public TimeRangeAdapter(List<BrokerBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_side_item_timerange,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.commission_item_title_activity.setText(list.get(position).getMainTitle());
        holder.commission_item_money_activity.setText(list.get(position).getCommissionFormat());
        holder.commission_item_time_activity.setText(list.get(position).getSecondsFormat());

        if (list.get(position).getIsSeconds().equals("0")) {
            holder.commission_item_time_activity.setTextColor(Color.parseColor("#31CA98"));
        }else {
            holder.commission_item_time_activity.setTextColor(Color.parseColor("#999999"));
        }

        if (FinalContents.getProjectType().equals("1")) {
            if (list.get(position).getSecondsFormat().equals("")) {
                holder.commission_item_time_activity.setVisibility(View.INVISIBLE);
            }else {
                holder.commission_item_time_activity.setVisibility(View.VISIBLE);
            }
        }else {
            holder.commission_item_time_activity.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickLisenter != null){
                    onItemClickLisenter.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView commission_item_title_activity;
        TextView commission_item_time_activity;
        TextView commission_item_money_activity;
        ImageView commission_item_image;

        public ViewHolder(View itemView) {
            super(itemView);
            commission_item_title_activity =(TextView) itemView.findViewById(R.id.commission_item_title_activity);
            commission_item_time_activity =(TextView) itemView.findViewById(R.id.commission_item_time_activity);
            commission_item_money_activity =(TextView) itemView.findViewById(R.id.commission_item_money_activity);
            commission_item_image = itemView.findViewById(R.id.commission_item_image);
        }
    }
}
