package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        holder.adapter_special_offer_history_price.setText(list.get(position).getDenomination()+"");
        if (list.get(position).getType().equals("1")) {
            holder.adapter_special_offer_history_discount_coupon.setText("带看券");
            holder.adapter_special_offer_history_item.setBackgroundResource(R.mipmap.adapter_special_offer_background_image);
        }else if (list.get(position).getType().equals("2")){
            holder.adapter_special_offer_history_discount_coupon.setText("成交券");
            holder.adapter_special_offer_history_item.setBackgroundResource(R.mipmap.adapter_special_offer_background_image_make_a_bargain);
        }

        holder.adapter_special_offer_history_name.setText(list.get(position).getProjectName());
        holder.adapter_special_offer_history_time.setText(list.get(position).getStartDate()+"-"+list.get(position).getEndDate()+"有效");
//        holder.adapter_special_offer_history_button.setText(list.get(position).getLotteryState());
        if (list.get(position).getLotteryState().equals("3")) {
            holder.adapter_special_offer_history_button.setImageResource(R.mipmap.adapter_special_offer_history_has_change);
        }else if (list.get(position).getLotteryState().equals("4")) {
            holder.adapter_special_offer_history_button.setImageResource(R.mipmap.adapter_special_offer_history_lose_efficacy);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SpecialOfferHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView adapter_special_offer_history_price;
        TextView adapter_special_offer_history_discount_coupon;
        TextView adapter_special_offer_history_name;
        TextView adapter_special_offer_history_time;
        ImageView adapter_special_offer_history_button;
        LinearLayout adapter_special_offer_history_item;

        public SpecialOfferHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_special_offer_history_item = itemView.findViewById(R.id.adapter_special_offer_history_item);
            adapter_special_offer_history_price = itemView.findViewById(R.id.adapter_special_offer_history_price);
            adapter_special_offer_history_discount_coupon = itemView.findViewById(R.id.adapter_special_offer_history_discount_coupon);
            adapter_special_offer_history_name = itemView.findViewById(R.id.adapter_special_offer_history_name);
            adapter_special_offer_history_time = itemView.findViewById(R.id.adapter_special_offer_history_time);
            adapter_special_offer_history_button = itemView.findViewById(R.id.adapter_special_offer_history_button);

        }

    }
}
