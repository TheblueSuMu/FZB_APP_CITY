package com.xcy.fzbcity.shopping_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.shopping_guide.MyClientFragmentBean;

import java.util.List;

public class ReviewTheSuccessAdapter extends RecyclerView.Adapter<ReviewTheSuccessAdapter.ViewHolder>{
    private List<MyClientFragmentBean.DataBean.ListDataBean> list;
    private Context context;

    public ReviewTheSuccessAdapter(List<MyClientFragmentBean.DataBean.ListDataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_guide_item_review_the_success, parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_review_the_success_title.setText(list.get(position).getRecordName());

        if (list.get(position).getRecordName().equals("已登岛")) {
//            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(context);
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            holder.item_review_the_success_rv.setLayoutManager(layoutManager);
//            JourneyAdapter recyclerAdapter = new JourneyAdapter(list.get(position).getJsonDatas());
//            holder.item_review_the_success_rv.setAdapter(recyclerAdapter);
//            recyclerAdapter.notifyDataSetChanged();
        }else {
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            holder.item_review_the_success_rv.setLayoutManager(manager);
            ReviewItemAdapter adapter = new ReviewItemAdapter(list.get(position).getJsonDatas());
            holder.item_review_the_success_rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerView item_review_the_success_rv;
        TextView item_review_the_success_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_review_the_success_title = itemView.findViewById(R.id.item_review_the_success_title);
            item_review_the_success_rv = itemView.findViewById(R.id.item_review_the_success_rv);
        }
    }
}