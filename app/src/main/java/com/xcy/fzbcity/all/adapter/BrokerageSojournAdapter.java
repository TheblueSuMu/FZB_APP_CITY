package com.xcy.fzbcity.all.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.all.database.BorkerageDownBean;

import java.util.List;

public class BrokerageSojournAdapter extends RecyclerView.Adapter<BrokerageSojournAdapter.BrokerageSojournViewHolder> {

    List<BorkerageDownBean.DataBean.RowsBean> rows;

    public void setRows(List<BorkerageDownBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }


    @NonNull
    @Override
    public BrokerageSojournViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BrokerageSojournViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class BrokerageSojournViewHolder extends RecyclerView.ViewHolder {

        public BrokerageSojournViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
