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
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;

import java.util.List;

public class CommissionDetailsAdapter extends RecyclerView.Adapter<CommissionDetailsAdapter.ViewHolder>{
    private List<TeamLeaderAmountBean.DataBean> list;
    private View view;
    private Context context;

    public CommissionDetailsAdapter(List<TeamLeaderAmountBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commission_details_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_commission_details_rv_title.setText(list.get(position).getProjectName());
        if (list.get(position).getAmount().size() != 0) {
            holder.item_commission_details_rv_rv.setVisibility(View.VISIBLE);
            MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(context);
            linearLayoutManager.setOrientation(MyLinearLayoutManager.VERTICAL);
            linearLayoutManager.setScrollEnabled(false);
            holder.item_commission_details_rv_rv.setLayoutManager(linearLayoutManager);
            CommissionDetailsRecyclerAdapter commissionDetailsRecyclerAdapter = new CommissionDetailsRecyclerAdapter(list.get(position).getAmount());
            holder.item_commission_details_rv_rv.setNestedScrollingEnabled(false);
            holder.item_commission_details_rv_rv.setAdapter(commissionDetailsRecyclerAdapter);
            commissionDetailsRecyclerAdapter.notifyDataSetChanged();
        }else {
            holder.item_commission_details_rv_rv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_commission_details_rv_title;
        RecyclerView item_commission_details_rv_rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_commission_details_rv_title = itemView.findViewById(R.id.item_commission_details_rv_title);
            item_commission_details_rv_rv = itemView.findViewById(R.id.item_commission_details_rv_rv);
        }
    }
}
