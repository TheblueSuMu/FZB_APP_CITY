package com.xcy.fzbcity.all.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.SignInCompanyBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SignInCompanyAdapter extends RecyclerView.Adapter<SignInCompanyAdapter.SignInCompanyViewHolder> {

    List<SignInCompanyBean.DataBean.RowsBean> rows;
    OnCompanyClick onCompanyClick;
    String companyName;

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setOnCompanyClick(OnCompanyClick onCompanyClick) {
        this.onCompanyClick = onCompanyClick;
    }

    public void setRows(List<SignInCompanyBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public SignInCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_in_item, parent, false);
        return new SignInCompanyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SignInCompanyViewHolder holder, int position) {

        if(rows.get(position).getName().equals(companyName)){
            holder.sign_in_img.setVisibility(View.VISIBLE);
            holder.sign_in_tv.setTextColor(Color.parseColor("#334485"));
        }else {
            holder.sign_in_img.setVisibility(View.GONE);
            holder.sign_in_tv.setTextColor(Color.parseColor("#111111"));
        }
        holder.sign_in_tv.setText(rows.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCompanyClick.CompanyClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class SignInCompanyViewHolder extends RecyclerView.ViewHolder {

        ImageView sign_in_img;
        TextView sign_in_tv;

        public SignInCompanyViewHolder(@NonNull View itemView) {
            super(itemView);

            sign_in_img = itemView.findViewById(R.id.sign_in_img);
            sign_in_tv = itemView.findViewById(R.id.sign_in_tv);

        }
    }

    public interface OnCompanyClick{
        void CompanyClick(int CompanyPosition);
    }

}
