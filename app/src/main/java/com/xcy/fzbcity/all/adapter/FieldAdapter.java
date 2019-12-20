package com.xcy.fzbcity.all.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.ProjectProgressApi;
import com.xcy.fzbcity.all.modle.LandBean;
import com.xcy.fzbcity.all.view.FieldActivity;

import java.util.List;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.FieldViewHolder>{

    private List<LandBean.DataBean.ColleaguesBean> list;

    public FieldAdapter(List<LandBean.DataBean.ColleaguesBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_field, parent, false);

        return new FieldViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final FieldViewHolder holder, final int position) {
        holder.item_field_title.setText(list.get(position).getFullName()+"  "+list.get(position).getGender()+"  "+list.get(position).getRelation());
        holder.item_field_amend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectProgressApi.setFieldID(list.get(position).getId());
                ProjectProgressApi.setLandingId(list.get(position).getLandingId());
                Intent intent = new Intent(holder.itemView.getContext(), FieldActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FieldViewHolder extends RecyclerView.ViewHolder{
        TextView item_field_title;
        ImageView item_field_amend;

        public FieldViewHolder(@NonNull View itemView) {
            super(itemView);
            item_field_title = itemView.findViewById(R.id.item_field_title);
            item_field_amend = itemView.findViewById(R.id.item_field_amend);
        }

    }
}