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
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.PreferentialActListBean;

import java.util.List;

public class SpecialOfferAdapter extends RecyclerView.Adapter<SpecialOfferAdapter.SpecialOfferViewHolder>{
    private View view;
    private Context context;
    private List<PreferentialActListBean.DataBean.RowsBean> list;

    public SpecialOfferAdapter(List<PreferentialActListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    private OnItemClickLisenter onItemClickLisenter;

    public interface OnItemClickLisenter {
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener) {
        this.onItemClickLisenter = onItemClickListener;
    }

    @NonNull
    @Override
    public SpecialOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_special_offer, parent, false);
        context = parent.getContext();
        return new SpecialOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialOfferViewHolder holder, int position) {
        holder.adapter_special_offer_price.setText(list.get(position).getDenomination()+"");
        if (list.get(position).getType().equals("1")) {
            holder.adapter_special_offer_discount_coupon.setText("带看券");
            holder.adapter_special_offer_item.setBackgroundResource(R.mipmap.adapter_special_offer_background_image);
        }else if (list.get(position).getType().equals("2")){
            holder.adapter_special_offer_discount_coupon.setText("成交券");
            holder.adapter_special_offer_item.setBackgroundResource(R.mipmap.adapter_special_offer_background_image_make_a_bargain);
        }

        holder.adapter_special_offer_name.setText(list.get(position).getProjectName());
        holder.adapter_special_offer_time.setText(list.get(position).getStartDate()+"-"+list.get(position).getEndDate()+"有效");
//        holder.adapter_special_offer_button.setText(list.get(position).getLotteryState());
        if (list.get(position).getLotteryState().equals("1")) {
            holder.adapter_special_offer_button.setImageResource(R.mipmap.adapter_special_offer_button_image_not_reach);
        }else if (list.get(position).getLotteryState().equals("2")) {
            holder.adapter_special_offer_button.setImageResource(R.mipmap.adapter_special_offer_button_image);
        }

        holder.adapter_special_offer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).getLotteryState().equals("2")) {
                    if (onItemClickLisenter != null) {
                        onItemClickLisenter.onItemClick(position);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SpecialOfferViewHolder extends RecyclerView.ViewHolder{
        TextView adapter_special_offer_price;
        TextView adapter_special_offer_name;
        TextView adapter_special_offer_time;
        ImageView adapter_special_offer_button;
        TextView adapter_special_offer_discount_coupon;
        LinearLayout adapter_special_offer_item;

        public SpecialOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_special_offer_item = itemView.findViewById(R.id.adapter_special_offer_item);
            adapter_special_offer_price = itemView.findViewById(R.id.adapter_special_offer_price);
            adapter_special_offer_name = itemView.findViewById(R.id.adapter_special_offer_name);
            adapter_special_offer_time = itemView.findViewById(R.id.adapter_special_offer_time);
            adapter_special_offer_button = itemView.findViewById(R.id.adapter_special_offer_button);
            adapter_special_offer_discount_coupon = itemView.findViewById(R.id.adapter_special_offer_discount_coupon);

        }
    }
}
