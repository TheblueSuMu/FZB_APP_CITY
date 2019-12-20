package com.xcy.fzbcity.project_side.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
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
        holder.timerange_title.setText(list.get(position).getMainTitle());
        holder.timerange_content.setText(list.get(position).getCommissionFormat());
        holder.timerange_miao.setText(list.get(position).getSecondsFormat());
        holder.timerange_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickLisenter != null){
                    onItemClickLisenter.onItemClick(position);
                }
            }
        });
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
        TextView timerange_title;
        TextView timerange_content;
        RadioButton timerange_select;
        TextView timerange_miao;

        public ViewHolder(View itemView) {
            super(itemView);
            timerange_title =(TextView) itemView.findViewById(R.id.timerange_title);
            timerange_content =(TextView) itemView.findViewById(R.id.timerange_content);
            timerange_select = itemView.findViewById(R.id.timerange_select);
            timerange_miao =(TextView) itemView.findViewById(R.id.timerange_miao);
        }
    }
}
