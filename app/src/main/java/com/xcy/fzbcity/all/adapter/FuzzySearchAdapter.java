package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tuacy.fuzzysearchlibrary.FuzzySearchBaseAdapter;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ItemEntity;

import java.util.List;

public class FuzzySearchAdapter extends FuzzySearchBaseAdapter<ItemEntity, FuzzySearchAdapter.ItemHolder> {

    public FuzzySearchAdapter(List<ItemEntity> dataList) {
        super(null, dataList);
    }

    ItemOnClick itemOnClick;

    public interface ItemOnClick {
        void itemClick(int position,String phone);
    }

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FuzzySearchAdapter.ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {
        for (int i = 0; i < mDataList.get(position).getValue().length(); ++i) {
            if (mDataList.get(position).getValue().substring(i, i + 1).equals("@")) {
                holder.text_item_name.setText(mDataList.get(position).getValue().substring(0, i));
                holder.text_item_phone.setText(mDataList.get(position).getValue().substring(i+1));
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClick.itemClick(position,holder.text_item_phone.getText().toString());
            }
        });
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        TextView text_item_name;
        TextView text_item_phone;

        ItemHolder(View itemView) {
            super(itemView);
            text_item_name = itemView.findViewById(R.id.text_item_name);
            text_item_phone = itemView.findViewById(R.id.text_item_phone);
        }
    }

}