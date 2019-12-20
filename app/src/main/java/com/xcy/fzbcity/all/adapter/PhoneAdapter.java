package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.database.LinkmanBean;
import com.xcy.fzbcity.all.modle.PhoneDto;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder>{
    protected Context context;
    protected List<LinkmanBean> list;
    private List<PhoneDto> datas;
    private View view;
    private String all = "";

    public PhoneAdapter(Context context, List<LinkmanBean> list) {
        this.context = context;
        this.list = list;
    }

    public List<PhoneDto> getList() {
        return datas;
    }

    public void setAll(String all) {
        this.all = all;
        notifyDataSetChanged();
    }

    public void setList(List<PhoneDto> datas) {
        this.datas = datas;
    }

    ItemOnClick itemOnClick;

    public interface ItemOnClick {
        void itemClick(int position);
    }

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.phone_CheckBox.setText(list.get(position).getCity());
        holder.phone_number.setText(list.get(position).getClientPhone());

        holder.phone_CheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.phone_CheckBox.isChecked()) {
                    for (int i = 0;i < datas.size(); ++i){
                        if((list.get(position).getClientPhone()).equals(datas.get(i).getTelPhone())){
                            Log.i("MyCL","contacts.get(position).getName():" + datas.get(i).getName());
                            datas.get(i).setStatus(true);
                        }
                    }
                }else {
                    for (int i = 0;i < datas.size(); ++i){
                        if((list.get(position).getClientPhone()).equals(datas.get(i).getTelPhone())){
                            Log.i("MyCL","contacts.get(position).getName():" + datas.get(i).getName());
                            datas.get(i).setStatus(false);
                        }
                    }
                }
                itemOnClick.itemClick(position);
            }
        });


        if (all.equals("1")) {
            holder.phone_CheckBox.setChecked(true);
            datas.get(position).setStatus(true);
        } else if (all.equals("2")) {
            holder.phone_CheckBox.setChecked(false);
            datas.get(position).setStatus(false);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox phone_CheckBox;
        TextView phone_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phone_CheckBox = itemView.findViewById(R.id.phone_CheckBox);
            phone_number = itemView.findViewById(R.id.phone_number);
        }

    }
}
