package com.xcy.fzbcity.captain_team.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.database.CommissionLevelSelectBean;

import java.util.List;

public class Captain_Team_CommissionLevelAdapter extends RecyclerView.Adapter<Captain_Team_CommissionLevelAdapter.CommissionLevelViewHolder> {

    List<CommissionLevelSelectBean.DataBean> data;
    onBtnDelete onBtnDelete;

    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    public void setOnBtnDelete(Captain_Team_CommissionLevelAdapter.onBtnDelete onBtnDelete) {
        this.onBtnDelete = onBtnDelete;
    }

    public void setList(List<CommissionLevelSelectBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CommissionLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.captain_team_item_commission_level, parent, false);
        return new CommissionLevelViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommissionLevelViewHolder holder, final int position) {

        holder.item_commission_level_tv1.setText(data.get(position).getName());

        holder.item_commission_level_tv2.setText(data.get(position).getPercent()+"");

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
        return data.size();
    }



    class CommissionLevelViewHolder extends RecyclerView.ViewHolder {

        TextView item_commission_level_tv1;
        TextView item_commission_level_tv2;

        public CommissionLevelViewHolder(@NonNull View itemView) {
            super(itemView);
            item_commission_level_tv1 = itemView.findViewById(R.id.item_commission_level_tv1);
            item_commission_level_tv2 = itemView.findViewById(R.id.item_commission_level_tv2);
        }

    }

    public interface onBtnDelete {
        void onDelete(String position);
    }

}
