package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.PreferentialActListBean;

import java.util.List;

public class SpecialOfferHistoryAdapter extends RecyclerView.Adapter<SpecialOfferHistoryAdapter.SpecialOfferHistoryViewHolder>{
    private List<PreferentialActListBean.DataBean.RowsBean> list;
    private View view;
    private Context context;

    public SpecialOfferHistoryAdapter(List<PreferentialActListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SpecialOfferHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_special_offer_history, parent, false);
        context = parent.getContext();
        return new SpecialOfferHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialOfferHistoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SpecialOfferHistoryViewHolder extends RecyclerView.ViewHolder{

        public SpecialOfferHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
