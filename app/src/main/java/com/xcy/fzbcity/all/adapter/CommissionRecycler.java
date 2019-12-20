package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ProjectDetailsBean;

public class CommissionRecycler extends RecyclerView.Adapter<CommissionRecycler.ViewHolder>{

    private ProjectDetailsBean.DataBean dataBean;

    public CommissionRecycler(ProjectDetailsBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commission_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.commission_item_title.setText(dataBean.getAmountIncentiveList().get(position).getMainTitle());
        holder.commission_item_price.setText(dataBean.getAmountIncentiveList().get(position).getCommissionFormat());
        if (dataBean.getAmountIncentiveList().get(position).getSecondsFormat().equals("") || dataBean.getAmountIncentiveList().get(position).getSecondsFormat().equals("0")) {
            holder.commission_item_time.setText("无秒结");
        }else {
            holder.commission_item_time.setText("秒结："+dataBean.getAmountIncentiveList().get(position).getSecondsFormat());
        }
    }

    @Override
    public int getItemCount() {
        return dataBean.getAmountIncentiveList().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView commission_item_title;
        TextView commission_item_price;
        TextView commission_item_time;


        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            commission_item_title =(TextView) itemView.findViewById(R.id.commission_item_title);
            commission_item_price =(TextView) itemView.findViewById(R.id.commission_item_price);
            commission_item_time =(TextView) itemView.findViewById(R.id.commission_item_time);

        }
    }
}
