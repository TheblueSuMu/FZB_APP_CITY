package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.RedbagReceiveRecordBean;

import java.util.List;

public class GetTheRecordParticularsAdapter extends RecyclerView.Adapter<GetTheRecordParticularsAdapter.GetTheRecordParticularsViewHolder>{
    private List<RedbagReceiveRecordBean.DataBean.RowsBean.AssistListBean> list;
    private View view;
    private Context context;

    public GetTheRecordParticularsAdapter(List<RedbagReceiveRecordBean.DataBean.RowsBean.AssistListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GetTheRecordParticularsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_get_the_record_particulars, parent, false);
        context = parent.getContext();
        return new GetTheRecordParticularsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetTheRecordParticularsViewHolder holder, int position) {
        holder.adapter_get_the_record_particulars_list_time.setText(list.get(position).getCreateDate());
        holder.adapter_get_the_record_particulars_list_name.setText(list.get(position).getAssistName());
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getCustomerImg()).into(holder.adapter_get_the_record_particulars_list_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GetTheRecordParticularsViewHolder extends RecyclerView.ViewHolder{
        ImageView adapter_get_the_record_particulars_list_image;
        TextView adapter_get_the_record_particulars_list_name;
        TextView adapter_get_the_record_particulars_list_time;

        public GetTheRecordParticularsViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_get_the_record_particulars_list_image = itemView.findViewById(R.id.adapter_get_the_record_particulars_list_image);
            adapter_get_the_record_particulars_list_name = itemView.findViewById(R.id.adapter_get_the_record_particulars_list_name);
            adapter_get_the_record_particulars_list_time = itemView.findViewById(R.id.adapter_get_the_record_particulars_list_time);

        }
    }
}
