package com.xcy.fzbcity.captain_counselor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.database.CommissionListBean;

import java.util.List;

public class TheProjectEndCommissionAdapter extends RecyclerView.Adapter<TheProjectEndCommissionAdapter.TheProjectEndCommissionViewHolder> {

    private List<CommissionListBean.DataBean.RowsBean> rowsBeanList;
    private Context context;

    public TheProjectEndCommissionAdapter(List<CommissionListBean.DataBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
    }

    @NonNull
    @Override
    public TheProjectEndCommissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.captain_counselor_item_commission_the_project_end, parent, false);
        context = parent.getContext();
        return new TheProjectEndCommissionViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TheProjectEndCommissionViewHolder holder, int position) {

        holder.the_project_end_title.setText(rowsBeanList.get(position).getCustomerName() + "(" + rowsBeanList.get(position).getCustomerPhone() + ")");
        holder.the_project_end_name.setText(rowsBeanList.get(position).getProjectName());
        holder.the_project_end_time.setText(rowsBeanList.get(position).getRoomNumber());
        holder.the_project_end_dong.setText("成交时间："+rowsBeanList.get(position).getTradeDate());

        if (rowsBeanList.get(position).getIsMy().equals("1")) {
            holder.the_project_end_people.setText("自己");
        }else {
            holder.the_project_end_people.setText(rowsBeanList.get(position).getAgentName() + "(" + rowsBeanList.get(position).getAgentPhone() + ")");
        }


        if (rowsBeanList.get(position).getStatus().equals("0")) {       //  TODO    未结清
            holder.the_project_end_tv6.setVisibility(View.GONE);
            holder.the_project_end_tv7.setVisibility(View.GONE);
        } else if (rowsBeanList.get(position).getStatus().equals("1")) {       //  TODO    已结清
            holder.the_project_end_tv6.setVisibility(View.VISIBLE);
            holder.the_project_end_tv7.setVisibility(View.VISIBLE);
            holder.the_project_end_tv7.setText(rowsBeanList.get(position).getUpdateDate());
        }

        if (rowsBeanList.get(position).getTotalAmount().equals("") || rowsBeanList.get(position).getTotalAmount().equals("0") || rowsBeanList.get(position).getTotalAmount().equals("0.00")) {
            holder.the_project_end_tv1.setVisibility(View.GONE);
        }else {
            holder.the_project_end_tv1.setVisibility(View.VISIBLE);
            holder.the_project_end_tv1.setText("总佣金：￥" + rowsBeanList.get(position).getTotalAmount());
        }

        if (rowsBeanList.get(position).getMoneyStatus() == 0) {
            holder.the_project_end_img.setVisibility(View.GONE);
        } else if (rowsBeanList.get(position).getMoneyStatus() == 1) {
            holder.the_project_end_img.setVisibility(View.VISIBLE);
            holder.the_project_end_img.setBackgroundResource(R.mipmap.tdg);
        } else if (rowsBeanList.get(position).getMoneyStatus() == 2) {
            holder.the_project_end_img.setVisibility(View.VISIBLE);
            holder.the_project_end_img.setBackgroundResource(R.mipmap.tdr);
        }

    }

    @Override
    public int getItemCount() {
        return rowsBeanList.size();
    }

    class TheProjectEndCommissionViewHolder extends RecyclerView.ViewHolder {

        ImageView the_project_end_img;
        ImageView the_project_end_img1;

        TextView the_project_end_title;
        TextView the_project_end_name;
        TextView the_project_end_time;
        TextView the_project_end_tv1;
        TextView the_project_end_tv6;
        TextView the_project_end_tv7;
        TextView the_project_end_dong;
        TextView the_project_end_people;

        public TheProjectEndCommissionViewHolder(@NonNull View itemView) {
            super(itemView);

            the_project_end_img = itemView.findViewById(R.id.the_project_end_img);
            the_project_end_img1 = itemView.findViewById(R.id.the_project_end_img1);
            the_project_end_title = itemView.findViewById(R.id.the_project_end_title);
            the_project_end_name = itemView.findViewById(R.id.the_project_end_name);
            the_project_end_time = itemView.findViewById(R.id.the_project_end_time);
            the_project_end_tv1 = itemView.findViewById(R.id.the_project_end_tv1);
            the_project_end_tv6 = itemView.findViewById(R.id.the_project_end_tv6);
            the_project_end_tv7 = itemView.findViewById(R.id.the_project_end_tv7);
            the_project_end_dong = itemView.findViewById(R.id.the_project_end_dong);
            the_project_end_people = itemView.findViewById(R.id.the_project_end_people);

        }
    }

}
