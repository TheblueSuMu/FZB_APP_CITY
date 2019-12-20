package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ProjectDetailsBean;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder>{
    private ProjectDetailsBean.DataBean dataBean;

    public ReportAdapter(ProjectDetailsBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_report_rv, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.item_key.setText(dataBean.getGuideRules().get(i).getKey());
        viewHolder.item_value.setText(dataBean.getGuideRules().get(i).getValue());
    }

    @Override
    public int getItemCount() {
        return dataBean.getGuideRules().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_key;
        TextView item_value;


        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            item_key =(TextView) itemView.findViewById(R.id.project_details_report_key);
            item_value =(TextView) itemView.findViewById(R.id.project_details_report_value);

        }
    }
}