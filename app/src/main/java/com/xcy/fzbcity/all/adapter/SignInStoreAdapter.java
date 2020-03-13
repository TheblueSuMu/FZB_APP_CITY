package com.xcy.fzbcity.all.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.SignInStoreBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SignInStoreAdapter extends RecyclerView.Adapter<SignInStoreAdapter.SignInStoreViewHolder> {
    private List<SignInStoreBean.DataBean.RowsBean> rows1;
    String storeName;

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    OnStoreClick onStoreClick;

    public void setOnStoreClick(OnStoreClick onStoreClick) {
        this.onStoreClick = onStoreClick;
    }

    public void setRows1(List<SignInStoreBean.DataBean.RowsBean> rows1) {
        this.rows1 = rows1;
    }

    @NonNull
    @Override
    public SignInStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_in_item, parent, false);
        return new SignInStoreViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SignInStoreViewHolder holder, int position) {

        if(rows1.get(position).getName().equals(storeName)){
            holder.sign_in_img.setVisibility(View.VISIBLE);
            holder.sign_in_tv.setTextColor(Color.parseColor("#334485"));
        }else {
            holder.sign_in_img.setVisibility(View.GONE);
            holder.sign_in_tv.setTextColor(Color.parseColor("#111111"));
        }
        holder.sign_in_tv.setText(rows1.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStoreClick.StoreClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rows1.size();
    }

    class SignInStoreViewHolder extends RecyclerView.ViewHolder {

        ImageView sign_in_img;
        TextView sign_in_tv;

        public SignInStoreViewHolder(@NonNull View itemView) {
            super(itemView);

            sign_in_img = itemView.findViewById(R.id.sign_in_img);
            sign_in_tv = itemView.findViewById(R.id.sign_in_tv);

        }
    }

    public interface OnStoreClick{
        void StoreClick(int StorePosition);
    }

}
