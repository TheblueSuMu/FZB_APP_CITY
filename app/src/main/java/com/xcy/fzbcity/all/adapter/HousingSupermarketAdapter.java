package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.util.Log;
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
import com.xcy.fzbcity.all.modle.SupermarketBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

public class HousingSupermarketAdapter extends RecyclerView.Adapter<HousingSupermarketAdapter.HousingSupermarketViewHolder>{
    private List<SupermarketBean.DataBean.RowsBean> list;
    private Context context;
    private View view;

    public HousingSupermarketAdapter(List<SupermarketBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HousingSupermarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_housing_supermarket, null);
        context = parent.getContext();
        return new HousingSupermarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HousingSupermarketViewHolder holder, int position) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getProjectListVo().getProjectImg()).into(holder.adapter_housing_supermarket_image);

        holder.adapter_housing_supermarket_title.setText("[" + list.get(position).getProjectListVo().getArea() + "]" + list.get(position).getProjectListVo().getProjectName());
        String ids = list.get(position).getProjectListVo().getProductFeature();//从pd里取出字符串
        List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list
        List tag = new ArrayList();
        if (tags.size() > 4) {
            for (int i = 0; i < 4; i++) {
                tag.add(tags.get(i));
            }
        } else {
            for (int i = 0; i < tags.size(); i++) {
                tag.add(tags.get(i));
            }
        }

        Log.i("2222", "标签" + list.get(position).getProjectListVo().getProductFeature());

        if (list.get(position).getProjectListVo().getProductFeature().equals("")) {
            holder.adapter_housing_supermarket_tagView.setVisibility(View.GONE);
        } else {
            holder.adapter_housing_supermarket_tagView.setVisibility(View.VISIBLE);
            holder.adapter_housing_supermarket_tagView.setTheme(ColorFactory.NONE);
            holder.adapter_housing_supermarket_tagView.setTags(tag);
        }

        holder.adapter_housing_supermarket_area.setText(list.get(position).getProjectListVo().getAreaInterval());

        if (list.get(position).getProjectListVo().getProjectType().equals("2")) {
            holder.adapter_housing_supermarket_price.setText(list.get(position).getProjectListVo().getReferenceToatlPrice());
            holder.adapter_housing_supermarket_unit.setText(list.get(position).getProjectListVo().getReferenceToatlUnit());
            Log.i("列表", "海外数据1" + list.get(position).getProjectListVo().getReferenceToatlPrice());
            Log.i("列表", "海外数据2" + list.get(position).getProjectListVo().getReferenceToatlUnit());
            if (list.get(position).getProjectListVo().getReferenceToatlPrice().equals("") || list.get(position).getProjectListVo().getReferenceToatlPrice().equals("0")) {
                holder.adapter_housing_supermarket_price.setVisibility(View.GONE);
                holder.adapter_housing_supermarket_unit.setVisibility(View.GONE);
            } else {
                holder.adapter_housing_supermarket_price.setVisibility(View.VISIBLE);
                holder.adapter_housing_supermarket_unit.setVisibility(View.VISIBLE);
            }
        } else if (list.get(position).getProjectListVo().getProjectType().equals("3")) {
            holder.adapter_housing_supermarket_price.setText(list.get(position).getProjectListVo().getProductUnitPrice());
            holder.adapter_housing_supermarket_unit.setText(list.get(position).getProjectListVo().getMonetaryUnit());
            Log.i("列表", "旅居数据1" + list.get(position).getProjectListVo().getProductUnitPrice());
            Log.i("列表", "旅居数据2" + list.get(position).getProjectListVo().getMonetaryUnit());
            if (list.get(position).getProjectListVo().getProductUnitPrice().equals("") || list.get(position).getProjectListVo().getProductUnitPrice().equals("0")) {
                holder.adapter_housing_supermarket_price.setVisibility(View.GONE);
                holder.adapter_housing_supermarket_unit.setVisibility(View.GONE);
            } else {
                holder.adapter_housing_supermarket_price.setVisibility(View.VISIBLE);
                holder.adapter_housing_supermarket_unit.setVisibility(View.VISIBLE);
            }
        } else if (list.get(position).getProjectListVo().getProjectType().equals("1")) {
            holder.adapter_housing_supermarket_price.setText(list.get(position).getProjectListVo().getProductUnitPrice());
            holder.adapter_housing_supermarket_unit.setText(list.get(position).getProjectListVo().getMonetaryUnit());
            Log.i("列表", "城市数据1" + list.get(position).getProjectListVo().getProductUnitPrice());
            Log.i("列表", "城市数据2" + list.get(position).getProjectListVo().getMonetaryUnit());
            if (list.get(position).getProjectListVo().getProductUnitPrice().equals("") || list.get(position).getProjectListVo().getProductUnitPrice().equals("0")) {
                holder.adapter_housing_supermarket_price.setVisibility(View.GONE);
                holder.adapter_housing_supermarket_unit.setVisibility(View.GONE);
            } else {
                holder.adapter_housing_supermarket_price.setVisibility(View.VISIBLE);
                holder.adapter_housing_supermarket_unit.setVisibility(View.VISIBLE);
            }
        }
    }

    public void move(int fromPosition, int toPosition) {
        SupermarketBean.DataBean.RowsBean prev = list.remove(fromPosition);
        list.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HousingSupermarketViewHolder extends RecyclerView.ViewHolder{
        ImageView adapter_housing_supermarket_image;
        ImageView adapter_housing_supermarket_icon;
        TextView adapter_housing_supermarket_title;
        TagContainerLayout adapter_housing_supermarket_tagView;
        TextView adapter_housing_supermarket_area;
        TextView adapter_housing_supermarket_price;
        TextView adapter_housing_supermarket_unit;

        public HousingSupermarketViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_housing_supermarket_image = itemView.findViewById(R.id.adapter_housing_supermarket_image);
            adapter_housing_supermarket_icon = itemView.findViewById(R.id.adapter_housing_supermarket_icon);
            adapter_housing_supermarket_title = itemView.findViewById(R.id.adapter_housing_supermarket_title);
            adapter_housing_supermarket_tagView = itemView.findViewById(R.id.adapter_housing_supermarket_tagView);
            adapter_housing_supermarket_area = itemView.findViewById(R.id.adapter_housing_supermarket_area);
            adapter_housing_supermarket_price = itemView.findViewById(R.id.adapter_housing_supermarket_price);
            adapter_housing_supermarket_unit = itemView.findViewById(R.id.adapter_housing_supermarket_unit);
        }
    }
}
