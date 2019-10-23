package com.xcy.fzb.captain_team.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.database.TeamMemberBean;

import java.util.List;

public class BatchModifyingAdapter extends RecyclerView.Adapter<BatchModifyingAdapter.BatchModifyingViewHolder> {

    List<TeamMemberBean.DataBean.RowsBean> list;

    public BatchModifyingAdapter(List<TeamMemberBean.DataBean.RowsBean> list) {
        this.list = list;
    }
    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    @NonNull
    @Override
    public BatchModifyingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_batch_modifying, parent, false);
        return new BatchModifyingViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchModifyingViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext()).load("http://39.98.173.250:8080" + list.get(position).getPhoto()).into(holder.item_batch_modifying_img);
        holder.item_batch_modifying_tv1.setText(list.get(position).getName()+"("+list.get(position).getRatioName()+")");
        holder.item_batch_modifying_tv2.setText(list.get(position).getPhone());
        holder.item_batch_modifying_tv3.setText("最后上线时间："+list.get(position).getLoginDate());

        holder.item_batch_modifying_rb.setOnClickListener(new View.OnClickListener() {
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

    class BatchModifyingViewHolder extends RecyclerView.ViewHolder {

        ImageView item_batch_modifying_img;

        TextView item_batch_modifying_tv1;
        TextView item_batch_modifying_tv2;
        TextView item_batch_modifying_tv3;

        CheckBox item_batch_modifying_rb;

        public BatchModifyingViewHolder(@NonNull View itemView) {
            super(itemView);

            item_batch_modifying_img = itemView.findViewById(R.id.item_batch_modifying_img);
            item_batch_modifying_tv1 = itemView.findViewById(R.id.item_batch_modifying_tv1);
            item_batch_modifying_tv2 = itemView.findViewById(R.id.item_batch_modifying_tv2);
            item_batch_modifying_tv3 = itemView.findViewById(R.id.item_batch_modifying_tv3);
            item_batch_modifying_rb = itemView.findViewById(R.id.item_batch_modifying_rb);

        }
    }

}
