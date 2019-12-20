package com.xcy.fzbcity.project_side.adapter;

import android.graphics.Color;
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
import com.xcy.fzbcity.all.modle.InitiatedBean;

import java.util.List;

public class InitiatedAdapter extends RecyclerView.Adapter<InitiatedAdapter.CheckPendingTheProjectEndViewHolder> {

    List<InitiatedBean.DataBean.RowsBean> rows;
    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setRows(List<InitiatedBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public CheckPendingTheProjectEndViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_side_item_refuse_the_project_end, parent, false);

        return new CheckPendingTheProjectEndViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckPendingTheProjectEndViewHolder holder, final int position) {

        Glide.with(holder.itemView.getContext()).load(FinalContents.getImageUrl() + rows.get(position).getCustomerImg()).into(holder.item_refuse_the_project_end_img);
        holder.item_refuse_the_project_end_tv1.setText(rows.get(position).getCustomerName());
        holder.item_refuse_the_project_end_tv2.setText(rows.get(position).getProjectName());
        holder.item_refuse_the_project_end_tv3.setText(rows.get(position).getDate());
        holder.item_refuse_the_project_end_tv4.setText(rows.get(position).getRelatedData());

        if (rows.get(position).getIsRead().equals("0")){
            holder.item_refuse_the_project_end_unread.setVisibility(View.VISIBLE);
        }else if(rows.get(position).getIsRead().equals("1")){
            holder.item_refuse_the_project_end_unread.setVisibility(View.GONE);
        }

        holder.item_refuse_the_project_end_tv4.setTextColor(Color.parseColor("#AC1E26"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.Item(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class CheckPendingTheProjectEndViewHolder extends RecyclerView.ViewHolder {

        ImageView item_refuse_the_project_end_img;
        TextView item_refuse_the_project_end_tv1;
        TextView item_refuse_the_project_end_tv2;
        TextView item_refuse_the_project_end_tv3;
        TextView item_refuse_the_project_end_tv4;
        ImageView item_refuse_the_project_end_unread;

        public CheckPendingTheProjectEndViewHolder(@NonNull View itemView) {
            super(itemView);

            item_refuse_the_project_end_img = itemView.findViewById(R.id.item_refuse_the_project_end_img);
            item_refuse_the_project_end_tv1 = itemView.findViewById(R.id.item_refuse_the_project_end_tv1);
            item_refuse_the_project_end_tv2 = itemView.findViewById(R.id.item_refuse_the_project_end_tv2);
            item_refuse_the_project_end_tv3 = itemView.findViewById(R.id.item_refuse_the_project_end_tv3);
            item_refuse_the_project_end_tv4 = itemView.findViewById(R.id.item_refuse_the_project_end_tv4);
            item_refuse_the_project_end_unread = itemView.findViewById(R.id.item_refuse_the_project_end_unread);
        }
    }

    public interface OnItemClick {
        void Item(int position);
    }
}
