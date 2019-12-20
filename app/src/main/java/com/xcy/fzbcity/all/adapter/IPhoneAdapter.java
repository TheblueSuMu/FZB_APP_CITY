package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.Dynamic2Bean;

import java.util.List;

public class IPhoneAdapter extends RecyclerView.Adapter<IPhoneAdapter.IPhoneViewHolder>{
    private List<Dynamic2Bean.DataBean.RowsBean.AttachesBean> list;
    private Context context;

    public IPhoneAdapter(List<Dynamic2Bean.DataBean.RowsBean.AttachesBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public IPhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone_name, parent, false);
        IPhoneViewHolder holder = new IPhoneViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IPhoneViewHolder holder, int position) {
        holder.item_phone_name_name.setText(list.get(position).getName());
        holder.item_phone_name_phone.setText(list.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class IPhoneViewHolder extends RecyclerView.ViewHolder {
        TextView item_phone_name_name;
        TextView item_phone_name_phone;

        public IPhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            item_phone_name_name = itemView.findViewById(R.id.item_phone_name_name);
            item_phone_name_phone = itemView.findViewById(R.id.item_phone_name_phone);
        }
    }
}
