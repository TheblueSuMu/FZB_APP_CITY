package com.xcy.fzbcity.all.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ReportProcessDetailsBean;

import java.util.List;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.ViewHolder>{

    private List<ReportProcessDetailsBean.DataBean.ProcessDataBean.JsonDatasBean> listData;
    private Context context;

    public JourneyAdapter(List<ReportProcessDetailsBean.DataBean.ProcessDataBean.JsonDatasBean> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_guide_item_journey_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.item_journey_tv3.setVisibility(View.GONE);
        holder.item_journey_tv4.setVisibility(View.GONE);
        holder.item_journey_fulfill.setVisibility(View.GONE);
        String value = listData.get(position).getValue();
        int length = value.length();
        if (length == 19){
            holder.item_journey_tv1.setText(value);
        }else if (length > 19){
            String time = value.substring(0, 18);
            String name = value.substring(20);
            holder.item_journey_tv3.setText(name);
            holder.item_journey_tv1.setText(time);
            holder.item_journey_tv3.setVisibility(View.VISIBLE);
        }
        holder.item_journey_tv2.setText(listData.get(position).getKey());
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_journey_tv1;
        TextView item_journey_fulfill;
        TextView item_journey_tv2;
        TextView item_journey_tv3;
        TextView item_journey_tv4;

        LinearLayout item_journey_lls;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_journey_tv1 = itemView.findViewById(R.id.item_journey_tv1);
            item_journey_fulfill = itemView.findViewById(R.id.item_journey_fulfill);
            item_journey_tv2 = itemView.findViewById(R.id.item_journey_tv2);
            item_journey_tv3 = itemView.findViewById(R.id.item_journey_tv3);
            item_journey_tv4 = itemView.findViewById(R.id.item_journey_tv4);
            item_journey_lls = itemView.findViewById(R.id.item_journey_lls);
        }
    }

}