package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ClientParticularsBean;
import com.xcy.fzbcity.all.view.ReviewTheSuccessActivity;

import java.util.List;

public class ClientParticularsAdapter extends RecyclerView.Adapter<ClientParticularsAdapter.ClientParticularsViewHolder> {

    List<ClientParticularsBean.DataBean.ListDataBean> listData;
    private Context context;

    public void setListData(List<ClientParticularsBean.DataBean.ListDataBean> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ClientParticularsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client_particulars, parent, false);
        context = parent.getContext();
        return new ClientParticularsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientParticularsViewHolder holder, final int position) {
        int num = 0;
        int a = 0;
        holder.item_client_title.setText(listData.get(position).getProjectName());
        List<ClientParticularsBean.DataBean.ListDataBean.AttacheListBean> attacheList = listData.get(position).getAttacheList();
        for (int i = 0; i < attacheList.size(); ++i) {
            if (num == 0) {
                holder.item_client_name1.setText("[项目负责人]" + attacheList.get(i).getName());
                num++;
            } else if (num == 1) {
                holder.item_client_name2.setText("[项目负责人]" + attacheList.get(i).getName());
                num++;
            } else if (num == 2) {
                holder.item_client_name3.setText("[项目负责人]" + attacheList.get(i).getName());
                num++;
            }
        }

        if (listData.get(position).getStatusName().equals("失效") || a == 1) {
            holder.item_client_tv1.setVisibility(View.VISIBLE);
            holder.item_client_tv1.setText(listData.get(position).getStatusName());
            holder.item_client_tv1.setTextColor(Color.parseColor(listData.get(position).getStatusColor()));
            holder.item_client_tv2.setVisibility(View.GONE);
        } else {
            holder.item_client_tv1.setVisibility(View.VISIBLE);
            holder.item_client_tv1.setText(listData.get(position).getStatusName());
            holder.item_client_tv1.setTextColor(Color.parseColor(listData.get(position).getStatusColor()));
        }

        final List<ClientParticularsBean.DataBean.ListDataBean.ListMapBean> listMap = listData.get(position).getListMap();
        if (listMap.size() != 0) {
            for (int j = 0; j < listMap.size(); ++j) {

                if (listMap.get(j).getKey() != null) {
                    if (listMap.get(j).getKey().indexOf("保护期") == -1) {
                        holder.item_client_tv2.setVisibility(View.GONE);
                    } else {
                        holder.item_client_tv2.setVisibility(View.VISIBLE);
                        holder.item_client_tv2.setText("保护期" + listMap.get(j).getValue());
                        holder.item_client_tv2.setTextColor(Color.parseColor("#C3555B"));
                        return;
                    }
                }
            }
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setPreparationId(listData.get(position).getPreparationId());
                Intent intent = new Intent(context, ReviewTheSuccessActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ClientParticularsViewHolder extends RecyclerView.ViewHolder {

        TextView item_client_title;
        TextView item_client_name1;
        TextView item_client_name2;
        TextView item_client_name3;
        TextView item_client_tv1;
        TextView item_client_tv2;
        LinearLayout item_client_ll;

        public ClientParticularsViewHolder(@NonNull View itemView) {
            super(itemView);
            item_client_ll = itemView.findViewById(R.id.item_client_ll);
            item_client_title = itemView.findViewById(R.id.item_client_title);
            item_client_name1 = itemView.findViewById(R.id.item_client_name1);
            item_client_name2 = itemView.findViewById(R.id.item_client_name2);
            item_client_name3 = itemView.findViewById(R.id.item_client_name3);
            item_client_tv1 = itemView.findViewById(R.id.item_client_tv1);
            item_client_tv2 = itemView.findViewById(R.id.item_client_tv2);

        }
    }


}
