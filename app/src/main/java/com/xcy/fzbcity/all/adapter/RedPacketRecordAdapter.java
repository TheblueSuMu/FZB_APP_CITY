package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.RedbagStatisticsBean;

import java.util.List;

public class RedPacketRecordAdapter extends RecyclerView.Adapter<RedPacketRecordAdapter.RedPacketRecordViewHolder>{
    private List<RedbagStatisticsBean.DataBean.RowsBean> list;
    private View view;

    public RedPacketRecordAdapter(List<RedbagStatisticsBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RedPacketRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.red_packet_record_adapter, parent,false);
        return new RedPacketRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RedPacketRecordViewHolder holder, int position) {
        holder.red_packet_record_adapter_content.setText("已领取"+list.get(position).getConverted()+"/"+list.get(position).getQuantity()+"个,共"+list.get(position).getConvertedAmount()+"/"+list.get(position).getQuantityAmount()+"元");
        holder.red_packet_record_adapter_time.setText("有效期至："+list.get(position).getStartDate()+"-"+list.get(position).getEndDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RedPacketRecordViewHolder extends RecyclerView.ViewHolder{
        TextView red_packet_record_adapter_content;
        TextView red_packet_record_adapter_time;
        CheckBox red_packet_record_adapter_button;

        public RedPacketRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            red_packet_record_adapter_content = itemView.findViewById(R.id.red_packet_record_adapter_content);
            red_packet_record_adapter_time = itemView.findViewById(R.id.red_packet_record_adapter_time);
            red_packet_record_adapter_button = itemView.findViewById(R.id.red_packet_record_adapter_button);
        }
    }
}
