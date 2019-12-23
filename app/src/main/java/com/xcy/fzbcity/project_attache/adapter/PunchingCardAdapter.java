package com.xcy.fzbcity.project_attache.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.RecordBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PunchingCardAdapter extends RecyclerView.Adapter<PunchingCardAdapter.PunchingCardViewHolder> {

    List<RecordBean.DataBean.RowsBean> rows;

    public void setRows(List<RecordBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public PunchingCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_clock_history, viewGroup, false);
        return new PunchingCardViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PunchingCardViewHolder punchingCardViewHolder, int i) {

        if (rows.get(i).getEffective().equals("0")) {
            Glide.with(punchingCardViewHolder.itemView.getContext()).load(R.mipmap.wuxiaodaka).into(punchingCardViewHolder.punching_img);
        } else if (rows.get(i).getEffective().equals("1")) {
            Glide.with(punchingCardViewHolder.itemView.getContext()).load(R.mipmap.youxiaodaka).into(punchingCardViewHolder.punching_img);
        }
        punchingCardViewHolder.punching_tv1.setText(rows.get(i).getStoreName());
        if(rows.get(i).getIsMy().equals("0")){
            punchingCardViewHolder.punching_tv2.setVisibility(View.VISIBLE);
            if(rows.get(i).getCreateBy().getIdentity().equals("5")){
                punchingCardViewHolder.punching_tv2.setText("负责专员：" + rows.get(i).getCreateBy().getName());
            }else if(rows.get(i).getCreateBy().getIdentity().equals("8")){
                punchingCardViewHolder.punching_tv2.setText("负责经理：" + rows.get(i).getCreateBy().getName());
            }else if(rows.get(i).getCreateBy().getIdentity().equals("9")){
                punchingCardViewHolder.punching_tv2.setText("负责总监：" + rows.get(i).getCreateBy().getName());
            }
        }else if (rows.get(i).getIsMy().equals("1")){
            punchingCardViewHolder.punching_tv2.setVisibility(View.GONE);
        }

        punchingCardViewHolder.punching_tv3.setText(rows.get(i).getCreateDate());
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class PunchingCardViewHolder extends RecyclerView.ViewHolder {

        ImageView punching_img;

        TextView punching_tv1;
        TextView punching_tv2;
        TextView punching_tv3;

        public PunchingCardViewHolder(@NonNull View itemView) {
            super(itemView);


            punching_img = itemView.findViewById(R.id.punching_img);
            punching_tv1 = itemView.findViewById(R.id.punching_tv1);
            punching_tv2 = itemView.findViewById(R.id.punching_tv2);
            punching_tv3 = itemView.findViewById(R.id.punching_tv3);

        }
    }

}
