package com.xcy.fzbcity.project_side.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.JourneyAdapter;
import com.xcy.fzbcity.all.adapter.ReviewItemAdapter;
import com.xcy.fzbcity.all.modle.ReportProcessDetailsBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;

import java.util.List;

public class Project_Side_MakeABargainAdapter extends RecyclerView.Adapter<Project_Side_MakeABargainAdapter.FailureViewHolder> {

    private List<ReportProcessDetailsBean.DataBean.ProcessDataBean> list;
    private Context context;

    public void setListData(List<ReportProcessDetailsBean.DataBean.ProcessDataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FailureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_side_item_makeabargain, parent, false);
        context = parent.getContext();
        return new FailureViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FailureViewHolder holder, int position) {
        holder.item_project_side_makeabargain_title.setText(list.get(position).getRecordName());

        if (list.get(position).getRecordName().equals("登岛")) {
            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            holder.item_project_side_makeabargain_rv.setLayoutManager(layoutManager);
            JourneyAdapter recyclerAdapter = new JourneyAdapter(list.get(position).getJsonDatas());
            holder.item_project_side_makeabargain_rv.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();
        }else {
            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            layoutManager.setScrollEnabled(false);
            holder.item_project_side_makeabargain_rv.setLayoutManager(layoutManager);
            ReviewItemAdapter adapter = new ReviewItemAdapter(list.get(position).getJsonDatas());
            holder.item_project_side_makeabargain_rv.setNestedScrollingEnabled(false);
            holder.item_project_side_makeabargain_rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FailureViewHolder extends RecyclerView.ViewHolder {
        RecyclerView item_project_side_makeabargain_rv;
        TextView item_project_side_makeabargain_title;

        public FailureViewHolder(@NonNull View itemView) {
            super(itemView);
            item_project_side_makeabargain_title = itemView.findViewById(R.id.item_project_side_makeabargain_title);
            item_project_side_makeabargain_rv = itemView.findViewById(R.id.item_project_side_makeabargain_rv);

        }
    }

}