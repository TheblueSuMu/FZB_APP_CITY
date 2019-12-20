package com.xcy.fzbcity.all.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.SpellingDataBean;

import java.util.List;

public class SpellingMassAdapter extends RecyclerView.Adapter<SpellingMassAdapter.SpellingMassViewHolder> {

    private List<SpellingDataBean.DataBean.PlanningBean> listData;

    public void setPlanning(List<SpellingDataBean.DataBean.PlanningBean> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public SpellingMassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        return new SpellingMassViewHolder(view);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull final SpellingMassViewHolder holder, final int position) {
        holder.item_SpellingMass_tv1.setText(listData.get(position).getPlanningTime());
        holder.item_SpellingMass_tv3.setVisibility(View.GONE);
        holder.item_SpellingMass_tv4.setVisibility(View.GONE);

        if (listData.get(position).getProject().getProjectName().equals("")) {
            holder.item_SpellingMass_tv3.setVisibility(View.GONE);
        }else {
            holder.item_SpellingMass_tv3.setVisibility(View.VISIBLE);
            holder.item_SpellingMass_tv3.setText(listData.get(position).getProject().getProjectName());
        }

        if (listData.get(position).getPlanningType().equals("1")) {
            holder.item_SpellingMass_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("2")) {
            holder.item_SpellingMass_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("3")) {
            holder.item_SpellingMass_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("4")) {
            holder.item_SpellingMass_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("5")) {
            holder.item_SpellingMass_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("6")) {
            holder.item_SpellingMass_tv2.setText(listData.get(position).getPlanningName());
        } else if (listData.get(position).getPlanningType().equals("7")) {
            holder.item_SpellingMass_tv2.setText(listData.get(position).getPlanningName());
            holder.item_SpellingMass_tv3.setVisibility(View.VISIBLE);
            holder.item_SpellingMass_tv3.setText(listData.get(position).getRemarks());
            holder.item_SpellingMass_tv4.setVisibility(View.VISIBLE);
        } else {
            holder.item_SpellingMass_tv2.setText(listData.get(position).getPlanningName());
        }

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class SpellingMassViewHolder extends RecyclerView.ViewHolder {

        TextView item_SpellingMass_tv1;
        TextView item_SpellingMass_tv2;
        TextView item_SpellingMass_tv3;
        TextView item_SpellingMass_tv4;

        LinearLayout item_SpellingMass_lls;

        public SpellingMassViewHolder(@NonNull View itemView) {
            super(itemView);

            item_SpellingMass_tv1 = itemView.findViewById(R.id.item_SpellingMass_tv1);
            item_SpellingMass_tv2 = itemView.findViewById(R.id.item_SpellingMass_tv2);
            item_SpellingMass_tv3 = itemView.findViewById(R.id.item_SpellingMass_tv3);
            item_SpellingMass_tv4 = itemView.findViewById(R.id.item_SpellingMass_tv4);
            item_SpellingMass_lls = itemView.findViewById(R.id.item_SpellingMass_lls);

        }
    }
}
