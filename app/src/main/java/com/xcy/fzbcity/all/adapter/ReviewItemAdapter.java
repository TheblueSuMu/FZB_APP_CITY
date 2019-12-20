package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ReportProcessDetailsBean;

import java.util.ArrayList;
import java.util.List;

public class ReviewItemAdapter extends RecyclerView.Adapter<ReviewItemAdapter.ViewHolder> {
    private List<ReportProcessDetailsBean.DataBean.ProcessDataBean.JsonDatasBean> list;
    private Context context;
    private String substring;
    private List<String> arraylist = new ArrayList<>();

    public ReviewItemAdapter(List<ReportProcessDetailsBean.DataBean.ProcessDataBean.JsonDatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_guide_item_review_adapter_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_reivew_adapter_item_key.setText(list.get(position).getKey());
        if (list.get(position).getValue() != null) {
            if (list.get(position).getValue().equals("")) {
            } else {
                substring = list.get(position).getValue().substring(0, 1);
                if (substring.equals("")) {
                } else {
                    if (substring.equals("/")) {
                        arraylist = new ArrayList<>();
                        holder.item_reivew_adapter_item.setVisibility(View.VISIBLE);
                        holder.item_reivew_adapter_item_value.setVisibility(View.GONE);
                        String[] a = list.get(position).getValue().split("[|]");
                        for (int i = 0; i < a.length; i++) {
                            arraylist.add(a[i]);
                        }
                        Log.i("走呀", "数组的长度：" + a.length);
                        Log.i("走呀", "1：" + arraylist.size());
                        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
                        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        holder.item_reivew_adapter_item.setLayoutManager(layoutManager);
                        ReviewAdapterItemAdapter reviewAdapterItemAdapter = new ReviewAdapterItemAdapter(arraylist);
                        reviewAdapterItemAdapter.setImgUrl(list.get(position).getValue());
                        Log.i("走呀", "图片：" + list.get(position).getValue());
                        holder.item_reivew_adapter_item.setAdapter(reviewAdapterItemAdapter);
                        reviewAdapterItemAdapter.notifyDataSetChanged();
                        Log.i("走呀", "2：" + a.length + "晚上：" + a[0]);
                    } else {
                        if (list.get(position).getValue().contains("|")) {
                            //包含
                            String[] split = list.get(position).getValue().split("[|]");
                            holder.item_reivew_adapter_item_value2.setVisibility(View.VISIBLE);
                            holder.item_reivew_adapter_item_value2.setText(split[0]);
                            holder.item_reivew_adapter_item_value2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                            holder.item_reivew_adapter_item_value.setText(split[1]);
                        } else {
                            //不包含
                            holder.item_reivew_adapter_item_value2.setVisibility(View.GONE);
                            holder.item_reivew_adapter_item.setVisibility(View.GONE);
                            holder.item_reivew_adapter_item_value.setVisibility(View.VISIBLE);
                            holder.item_reivew_adapter_item_value.setText(list.get(position).getValue());
                        }
                    }
                }
            }
        }

        Log.i("成交的数据", "数据：" + substring);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_reivew_adapter_item_key;
        TextView item_reivew_adapter_item_value;
        TextView item_reivew_adapter_item_value2;
        RecyclerView item_reivew_adapter_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_reivew_adapter_item_key = itemView.findViewById(R.id.item_reivew_adapter_item_key);
            item_reivew_adapter_item_value = itemView.findViewById(R.id.item_reivew_adapter_item_value);
            item_reivew_adapter_item_value2 = itemView.findViewById(R.id.item_reivew_adapter_item_value2);
            item_reivew_adapter_item = itemView.findViewById(R.id.item_reivew_adapter_item);
        }
    }
}
