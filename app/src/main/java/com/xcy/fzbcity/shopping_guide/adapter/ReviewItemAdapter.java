package com.xcy.fzbcity.shopping_guide.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.ReviewAdapterItemAdapter;
import com.xcy.fzbcity.shopping_guide.MyClientFragmentBean;

import java.util.ArrayList;
import java.util.List;

public class ReviewItemAdapter extends RecyclerView.Adapter<ReviewItemAdapter.ViewHolder>{
    private List<MyClientFragmentBean.DataBean.ListDataBean.JsonDatasBean> list;
    private Context context;
    private String substring;

    public ReviewItemAdapter(List<MyClientFragmentBean.DataBean.ListDataBean.JsonDatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_guide_item_review_adapter_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_reivew_adapter_item_key.setText(list.get(position).getKey());
        if (list.get(position).getValue().equals("")) {

        }else {
            substring = list.get(position).getValue().substring(0, 1);
            if (substring.equals("")) {
            }else {
                if (substring.equals("/")) {
                    holder.item_reivew_adapter_item.setVisibility(View.VISIBLE);
                    holder.item_reivew_adapter_item_value.setVisibility(View.GONE);
                    String a []  = list.get(position).getValue().split("[|]");
                    List<String> arraylist = new ArrayList<>();
                    for (int i = 0;i < a.length;i++){
                        arraylist.add(a[i]);
                    }
                    GridLayoutManager layoutManager = new GridLayoutManager(context,3);
                    layoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    holder.item_reivew_adapter_item.setLayoutManager(layoutManager);
                    ReviewAdapterItemAdapter recyclerAdapter = new ReviewAdapterItemAdapter(arraylist);
                    recyclerAdapter.setImgUrl(list.get(position).getValue());
                    holder.item_reivew_adapter_item.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                }else {
                    if(list.get(position).getValue().contains("|")){
                        //包含
                        String[] split = list.get(position).getValue().split("[|]");
                        holder.item_reivew_adapter_item_value2.setVisibility(View.VISIBLE);
                        holder.item_reivew_adapter_item_value2.setText(split[0]);
                        holder.item_reivew_adapter_item_value2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG );
                        holder.item_reivew_adapter_item_value.setText(split[1]);
                    }else{
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_reivew_adapter_item_key;
        TextView item_reivew_adapter_item_value;
        RecyclerView item_reivew_adapter_item;
        TextView item_reivew_adapter_item_value2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_reivew_adapter_item_key = itemView.findViewById(R.id.item_reivew_adapter_item_key);
            item_reivew_adapter_item_value = itemView.findViewById(R.id.item_reivew_adapter_item_value);
            item_reivew_adapter_item_value2 = itemView.findViewById(R.id.item_reivew_adapter_item_value2);
            item_reivew_adapter_item = itemView.findViewById(R.id.item_reivew_adapter_item);
        }
    }
}
