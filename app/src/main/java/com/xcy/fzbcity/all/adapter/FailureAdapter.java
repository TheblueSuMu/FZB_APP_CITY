package com.xcy.fzbcity.all.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.FailureBean;

import java.util.List;

public class FailureAdapter extends RecyclerView.Adapter<FailureAdapter.FailureViewHolder> {

    private List<FailureBean.DataBean.ListDataBean> listData;
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setListData(List<FailureBean.DataBean.ListDataBean> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public FailureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_failure, parent, false);

        return new FailureViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FailureViewHolder holder, int position) {


        int size = listData.get(position).getListMap().size();
        Log.i("MyCL", "长度：" + size);

        for (int i = 0; i < size; ++i) {
            if (i == size) {
                break;
            } else if (i == 0) {
                holder.textView1.setVisibility(View.VISIBLE);
                holder.textView2.setVisibility(View.GONE);
                holder.textView3.setVisibility(View.GONE);
                holder.textView4.setVisibility(View.GONE);
                holder.textView5.setVisibility(View.GONE);
                holder.textView6.setVisibility(View.GONE);
                holder.textView1.setText(listData.get(position).getStatusName());
            } else if (i == 1) {
                holder.textView2.setVisibility(View.VISIBLE);
                holder.textView3.setVisibility(View.GONE);
                holder.textView4.setVisibility(View.GONE);
                holder.textView5.setVisibility(View.GONE);
                holder.textView6.setVisibility(View.GONE);
                holder.textView2.setText(listData.get(position).getListMap().get(1).getKey() + ":" + listData.get(position).getListMap().get(1).getValue());
            }else if (i == 3) {
                holder.textView3.setVisibility(View.VISIBLE);
                holder.textView4.setVisibility(View.GONE);
                holder.textView5.setVisibility(View.GONE);
                holder.textView6.setVisibility(View.GONE);
                holder.textView3.setText(listData.get(position).getListMap().get(2).getKey() + ":" + listData.get(position).getListMap().get(2).getValue());
            }else if (i == 4) {
                holder.textView4.setVisibility(View.VISIBLE);
                holder.textView5.setVisibility(View.GONE);
                holder.textView6.setVisibility(View.GONE);
                holder.textView3.setText(listData.get(position).getListMap().get(2).getKey() + ":" + listData.get(position).getListMap().get(2).getValue());
            }else if (i == 5) {
                holder.textView5.setVisibility(View.VISIBLE);
                holder.textView6.setVisibility(View.GONE);
                holder.textView3.setText(listData.get(position).getListMap().get(2).getKey() + ":" + listData.get(position).getListMap().get(2).getValue());
            }else if (i == 6) {
                holder.textView6.setVisibility(View.VISIBLE);
                holder.textView3.setText(listData.get(position).getListMap().get(2).getKey() + ":" + listData.get(position).getListMap().get(2).getValue());
            }

        }
        holder.textView6.setText(listData.get(position).getListMap().get(0).getValue());


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class FailureViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;

        public FailureViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.item_failure_tv1);
            textView2 = itemView.findViewById(R.id.item_failure_tv2);
            textView3 = itemView.findViewById(R.id.item_failure_tv3);
            textView4 = itemView.findViewById(R.id.item_failure_tv4);
            textView5 = itemView.findViewById(R.id.item_failure_tv5);
            textView6 = itemView.findViewById(R.id.item_failure_tv6);

        }
    }

}
