package com.xcy.fzbcity.captain_team.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.TeamLeaderAmountBean;
import com.xcy.fzbcity.captain_team.view.CommissionDetailsActivity;

import java.util.List;

public class Captain_Team_YongAdapter extends RecyclerView.Adapter<Captain_Team_YongAdapter.ViewHolder>{
    private List<TeamLeaderAmountBean.DataBean> list;
    private View view;
    private Context context;

    public Captain_Team_YongAdapter(List<TeamLeaderAmountBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sales_details_details_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_sales_details_details_relative_title.setText(list.get(position).getProjectName());
        if (list.get(position).getAmount().size() != 0) {
            holder.item_sales_details_details_relative_content1.setVisibility(View.VISIBLE);
            holder.item_sales_details_details_relative_content1.setText(list.get(position).getAmount().get(0).getMainTitle()+"："+list.get(position).getAmount().get(0).getCommissionFormat());
            if (list.get(position).getAmount().size() >= 2) {
                holder.item_sales_details_details_relative_content2.setVisibility(View.VISIBLE);
                holder.item_sales_details_details_relative_content2.setText(list.get(position).getAmount().get(1).getMainTitle()+"："+list.get(position).getAmount().get(1).getCommissionFormat());
                if (list.get(position).getAmount().size() >= 3) {
                    holder.item_sales_details_details_relative_content3.setVisibility(View.VISIBLE);
                    holder.item_sales_details_details_relative_content3.setText(list.get(position).getAmount().get(2).getMainTitle()+"："+list.get(position).getAmount().get(2).getCommissionFormat());
                }else {
                    holder.item_sales_details_details_relative_content3.setVisibility(View.GONE);
                }
            }else {
                holder.item_sales_details_details_relative_content2.setVisibility(View.GONE);
                holder.item_sales_details_details_relative_content3.setVisibility(View.GONE);
            }
        }else {
            holder.item_sales_details_details_relative_content1.setVisibility(View.GONE);
            holder.item_sales_details_details_relative_content2.setVisibility(View.GONE);
            holder.item_sales_details_details_relative_content3.setVisibility(View.GONE);
        }
        holder.item_sales_details_details_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CommissionDetailsActivity.class);
                context.startActivity(intent);
            }
        });

        holder.item_sales_details_details_relative_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CommissionDetailsActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item_sales_details_details_relative_btn;
        ImageView item_sales_details_details_money_img;
        ImageView item_sales_details_details_back_img;
        TextView item_sales_details_details_relative_title;
        TextView item_sales_details_details_relative_content1;
        TextView item_sales_details_details_relative_content2;
        TextView item_sales_details_details_relative_content3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_sales_details_details_relative_btn = itemView.findViewById(R.id.item_sales_details_details_relative_btn);
            item_sales_details_details_money_img = itemView.findViewById(R.id.item_sales_details_details_money_img);
            item_sales_details_details_back_img = itemView.findViewById(R.id.item_sales_details_details_back_img);
            item_sales_details_details_relative_title = itemView.findViewById(R.id.item_sales_details_details_relative_title);
            item_sales_details_details_relative_content1 = itemView.findViewById(R.id.item_sales_details_details_relative_content1);
            item_sales_details_details_relative_content2 = itemView.findViewById(R.id.item_sales_details_details_relative_content2);
            item_sales_details_details_relative_content3 = itemView.findViewById(R.id.item_sales_details_details_relative_content3);

        }
    }
}
