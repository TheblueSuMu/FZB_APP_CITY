package com.xcy.fzbcity.captain_team.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.TeamLeaderAmountBean;

import java.util.List;

public class CommissionDetailsRecyclerAdapter extends RecyclerView.Adapter<CommissionDetailsRecyclerAdapter.ViewHolder>{
    private List<TeamLeaderAmountBean.DataBean.AmountBean> list;
    private View view;
    private Context context;

    public CommissionDetailsRecyclerAdapter(List<TeamLeaderAmountBean.DataBean.AmountBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commission_details_rv_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_commission_details_rv_adapter_contect.setText(list.get(position).getMainTitle()+"："+list.get(position).getCommissionFormat());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_commission_details_rv_adapter_contect;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_commission_details_rv_adapter_contect = itemView.findViewById(R.id.item_commission_details_rv_adapter_contect);
        }
    }
}
