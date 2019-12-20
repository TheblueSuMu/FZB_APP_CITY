package com.xcy.fzbcity.all.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.all.database.BorkerageDownBean;

import java.util.List;

public class BrokerageOverseasAdapter extends RecyclerView.Adapter<BrokerageOverseasAdapter.BrokerageOverseasViewHolder> {

    List<BorkerageDownBean.DataBean.RowsBean> rows;

    public void setRows(List<BorkerageDownBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public BrokerageOverseasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BrokerageOverseasViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class BrokerageOverseasViewHolder extends RecyclerView.ViewHolder{

        public BrokerageOverseasViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
