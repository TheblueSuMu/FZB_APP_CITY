package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.HotBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

public class HousingSupermarketAddHousingAdapter extends RecyclerView.Adapter<HousingSupermarketAddHousingAdapter.HousingSupermarketAddHousingViewHolder>{
    private View view;
    private List<HotBean.DataBean.RowsBean> list;
    private List<String> arrayList = new ArrayList<>();
    private Context context;

    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    public HousingSupermarketAddHousingAdapter(List<HotBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    public List<String> getArrayList() {
        return arrayList;
    }

    @NonNull
    @Override
    public HousingSupermarketAddHousingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_housing_supermarket_add_housing, null);
        context = parent.getContext();
        return new HousingSupermarketAddHousingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HousingSupermarketAddHousingViewHolder holder, int position) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getListPageCover()).into(holder.adapter_housing_supermarket_add_housing_image);
        holder.adapter_housing_supermarket_add_housing_title.setText("[" + list.get(position).getArea() + "]" + list.get(position).getProjectName());
        String ids = list.get(position).getProductFeature();//从pd里取出字符串
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

        Log.i("2222", "标签" + list.get(position).getProductFeature());

        if (list.get(position).getProductFeature().equals("")) {
            holder.adapter_housing_supermarket_add_housing_tagView.setVisibility(View.GONE);
        } else {
            holder.adapter_housing_supermarket_add_housing_tagView.setVisibility(View.VISIBLE);
            holder.adapter_housing_supermarket_add_housing_tagView.setTheme(ColorFactory.NONE);
            holder.adapter_housing_supermarket_add_housing_tagView.setTags(tag);
        }

        holder.adapter_housing_supermarket_add_housing_area.setText(list.get(position).getAreaInterval());

        if (list.get(position).getProjectType().equals("2")) {
            holder.adapter_housing_supermarket_add_housing_price.setText(list.get(position).getReferenceToatlPrice());
            holder.adapter_housing_supermarket_add_housing_unit.setText(list.get(position).getReferenceToatlUnit());
            Log.i("列表", "海外数据1" + list.get(position).getReferenceToatlPrice());
            Log.i("列表", "海外数据2" + list.get(position).getReferenceToatlUnit());
            if (list.get(position).getReferenceToatlPrice().equals("") || list.get(position).getReferenceToatlPrice().equals("0")) {
                holder.adapter_housing_supermarket_add_housing_price.setVisibility(View.GONE);
                holder.adapter_housing_supermarket_add_housing_unit.setVisibility(View.GONE);
            } else {
                holder.adapter_housing_supermarket_add_housing_price.setVisibility(View.VISIBLE);
                holder.adapter_housing_supermarket_add_housing_unit.setVisibility(View.VISIBLE);
            }
        } else if (list.get(position).getProjectType().equals("3")) {
            holder.adapter_housing_supermarket_add_housing_price.setText(list.get(position).getProductUnitPrice());
            holder.adapter_housing_supermarket_add_housing_unit.setText(list.get(position).getMonetaryUnit());
            Log.i("列表", "旅居数据1" + list.get(position).getProductUnitPrice());
            Log.i("列表", "旅居数据2" + list.get(position).getMonetaryUnit());
            if (list.get(position).getProductUnitPrice().equals("") || list.get(position).getProductUnitPrice().equals("0")) {
                holder.adapter_housing_supermarket_add_housing_price.setVisibility(View.GONE);
                holder.adapter_housing_supermarket_add_housing_unit.setVisibility(View.GONE);
            } else {
                holder.adapter_housing_supermarket_add_housing_price.setVisibility(View.VISIBLE);
                holder.adapter_housing_supermarket_add_housing_unit.setVisibility(View.VISIBLE);
            }
        } else if (list.get(position).getProjectType().equals("1")) {
            holder.adapter_housing_supermarket_add_housing_price.setText(list.get(position).getProductUnitPrice());
            holder.adapter_housing_supermarket_add_housing_unit.setText(list.get(position).getMonetaryUnit());
            Log.i("列表", "城市数据1" + list.get(position).getProductUnitPrice());
            Log.i("列表", "城市数据2" + list.get(position).getMonetaryUnit());
            if (list.get(position).getProductUnitPrice().equals("") || list.get(position).getProductUnitPrice().equals("0")) {
                holder.adapter_housing_supermarket_add_housing_price.setVisibility(View.GONE);
                holder.adapter_housing_supermarket_add_housing_unit.setVisibility(View.GONE);
            } else {
                holder.adapter_housing_supermarket_add_housing_price.setVisibility(View.VISIBLE);
                holder.adapter_housing_supermarket_add_housing_unit.setVisibility(View.VISIBLE);
            }
        }

        holder.adapter_housing_supermarket_add_housing_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.adapter_housing_supermarket_add_housing_select.isChecked()) {
                    for (int i = 0;i < arrayList.size(); ++i){
                        if (i == position) {
                            arrayList.set(position,list.get(position).getProjectId());
                        }
                    }
                }else {
                    for (int i = 0;i < arrayList.size(); ++i){
                        if (i == position) {
                            arrayList.set(position,"");
                        }
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HousingSupermarketAddHousingViewHolder extends RecyclerView.ViewHolder{
        CheckBox adapter_housing_supermarket_add_housing_select;
        ImageView adapter_housing_supermarket_add_housing_image;
        TextView adapter_housing_supermarket_add_housing_title;
        TagContainerLayout adapter_housing_supermarket_add_housing_tagView;
        TextView adapter_housing_supermarket_add_housing_area;
        TextView adapter_housing_supermarket_add_housing_price;
        TextView adapter_housing_supermarket_add_housing_unit;


        public HousingSupermarketAddHousingViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_housing_supermarket_add_housing_select = itemView.findViewById(R.id.adapter_housing_supermarket_add_housing_select);
            adapter_housing_supermarket_add_housing_image = itemView.findViewById(R.id.adapter_housing_supermarket_add_housing_image);
            adapter_housing_supermarket_add_housing_title = itemView.findViewById(R.id.adapter_housing_supermarket_add_housing_title);
            adapter_housing_supermarket_add_housing_tagView = itemView.findViewById(R.id.adapter_housing_supermarket_add_housing_tagView);
            adapter_housing_supermarket_add_housing_area = itemView.findViewById(R.id.adapter_housing_supermarket_add_housing_area);
            adapter_housing_supermarket_add_housing_price = itemView.findViewById(R.id.adapter_housing_supermarket_add_housing_price);
            adapter_housing_supermarket_add_housing_unit = itemView.findViewById(R.id.adapter_housing_supermarket_add_housing_unit);
        }
    }
}
