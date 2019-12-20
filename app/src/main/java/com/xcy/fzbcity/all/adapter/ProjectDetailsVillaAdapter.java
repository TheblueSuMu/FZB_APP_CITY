package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ProjectHousesTrendListBean;

import java.util.List;

public class ProjectDetailsVillaAdapter extends RecyclerView.Adapter<ProjectDetailsVillaAdapter.ViewHolder>{

    List<ProjectHousesTrendListBean.DataBean.HouseTrendResultBean.HouseTrendRatioVoListBean> list;
    private View view;
    private Context context;

    public ProjectDetailsVillaAdapter(List<ProjectHousesTrendListBean.DataBean.HouseTrendResultBean.HouseTrendRatioVoListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_details_villa, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (list.get(position).getProcuctType()) {
            case "1":
                holder.project_details_villa.setText("住宅均价");
                break;
            case "2":
                holder.project_details_villa.setText("公寓均价");
                break;
            case "3":
                holder.project_details_villa.setText("写字间均价");
                break;
            case "4":
                holder.project_details_villa.setText("商铺均价");
                break;
            case "5":
                holder.project_details_villa.setText("别墅均价");
                break;
        }

        if (list.get(position).getUpLow().equals("up")) {
            holder.project_details_decline_img.setVisibility(View.VISIBLE);
            holder.project_details_decline_img.setImageResource(R.mipmap.project_details_rise);
        } else if (list.get(position).getUpLow().equals("low")) {
            holder.project_details_decline_img.setVisibility(View.VISIBLE);
            holder.project_details_decline_img.setImageResource(R.mipmap.project_details_decline);
        }else {
            holder.project_details_decline_img.setVisibility(View.GONE);
        }

        holder.project_details_villa_price.setText("￥"+list.get(position).getAveragePrice()+"/m²");
        holder.project_details_villa_price_compare.setText(list.get(position).getCompareLastMonth()+"％");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView project_details_villa;
        TextView project_details_villa_price;
        TextView project_details_villa_compare;
        ImageView project_details_decline_img;
        TextView project_details_villa_price_compare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            project_details_villa = itemView.findViewById(R.id.project_details_villa);
            project_details_villa_price = itemView.findViewById(R.id.project_details_villa_price);
            project_details_villa_compare = itemView.findViewById(R.id.project_details_villa_compare);
            project_details_decline_img = itemView.findViewById(R.id.project_details_decline_img);
            project_details_villa_price_compare = itemView.findViewById(R.id.project_details_villa_price_compare);
        }
    }
}
