package com.xcy.fzb.shopping_guide.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.CustomerListBean;
import com.xcy.fzb.shopping_guide.view.ReviewTheSuccessActivity;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder>{
    List<CustomerListBean.DataBean.RowsBean> list;
    private Context context;

    public ClientAdapter(List<CustomerListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client_adapter, null);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_client_name.setText(list.get(position).getCustomerName());
        Glide.with(context).load("http://39.98.173.250:8080" + list.get(position).getCustomerImg()).into(holder.item_client_icon);
        holder.item_client_status.setText(list.get(position).getStatusStr());

        Log.i("yyy","用户ID："+FinalContents.getUserID());
        FinalContents.setCustomerID(list.get(position).getCustomerId());
        FinalContents.setLandingId(list.get(position).getLandingId());
        FinalContents.setPreparationId(list.get(position).getPreparationId());

        if (list.get(position).getNoTrade().equals("")) {
            if (list.get(position).getStatus().equals("50")) {
                holder.item_client_status.setTextColor(R.color.textcolor2);
            } else if (list.get(position).getStatus().equals("60")) {
                holder.item_client_status.setTextColor(R.color.textcolor3);
            }
        }else {
            holder.item_client_status.setTextColor(R.color.textcolor1);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReviewTheSuccessActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView item_client_icon;
        private TextView item_client_name;
        private TextView item_client_status;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_client_icon = itemView.findViewById(R.id.item_client_icon);
            item_client_name = itemView.findViewById(R.id.item_client_name);
            item_client_status = itemView.findViewById(R.id.item_client_status);
        }
    }
}
