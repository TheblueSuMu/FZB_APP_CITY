package com.xcy.fzbcity.all.adapter;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nim.uikit.common.util.C;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.utils.InformeDataBase;
import com.xcy.fzbcity.all.utils.InformeDataNewBase;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InformTheDetailsAdapter extends RecyclerView.Adapter<InformTheDetailsAdapter.InformTheDetailsViewHolder> {

    private List<InformeDataNewBase> InformeDataNewBase;

    public void setInformeDataBases(List<InformeDataNewBase> InformeDataNewBase) {
        this.InformeDataNewBase = InformeDataNewBase;
    }

    OnItemInformClick onItemInformClick;

    public void setOnItemInformClick(OnItemInformClick onItemInformClick) {
        this.onItemInformClick = onItemInformClick;
    }


    @NonNull
    @Override
    public InformTheDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.inform_the_details_item, parent, false);
        return new InformTheDetailsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InformTheDetailsViewHolder holder, int position) {

        holder.inform_the_details_item_ll.setVisibility(View.VISIBLE);
        holder.inform_the_details_item_time.setText(timestamp2Date(InformeDataNewBase.get(position).getDate()));
        holder.inform_the_details_item_title.setText(InformeDataNewBase.get(position).getTitle());
        holder.inform_the_details_item_message.setText(InformeDataNewBase.get(position).getContent());

        if (InformeDataNewBase.get(position).getIsRead().equals("1")) {
            holder.inform_the_details_item_tv.setTextColor(Color.parseColor("#334485"));
        } else {
            holder.inform_the_details_item_tv.setTextColor(Color.parseColor("#B2B2B2"));
        }

        holder.inform_the_details_item_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemInformClick.ItemInformClick(position);
            }
        });
//        else if(informeDataBases.get(position).getType().equals("2")){
//            holder.inform_the_details_item_time.setText(timestamp2Date(informeDataBases.get(position).getDate()));
//            holder.inform_the_details_item_title.setText(timestamp2Date(informeDataBases.get(position).getTitle()));
//            holder.inform_the_details_item_message.setText(timestamp2Date(informeDataBases.get(position).getContent()));
//
//            if(timestamp2Date(informeDataBases.get(position).getIsRead()).equals("1")){
//                holder.inform_the_details_item_tv.setTextColor(Color.parseColor("#334485"));
//            }else {
//                holder.inform_the_details_item_tv.setTextColor(Color.parseColor("#B2B2B2"));
//            }
//        }else if(informeDataBases.get(position).getType().equals("3")){
//            holder.inform_the_details_item_time.setText(timestamp2Date(informeDataBases.get(position).getDate()));
//            holder.inform_the_details_item_title.setText(timestamp2Date(informeDataBases.get(position).getTitle()));
//            holder.inform_the_details_item_message.setText(timestamp2Date(informeDataBases.get(position).getContent()));
//
//            if(timestamp2Date(informeDataBases.get(position).getIsRead()).equals("1")){
//                holder.inform_the_details_item_tv.setTextColor(Color.parseColor("#334485"));
//            }else {
//                holder.inform_the_details_item_tv.setTextColor(Color.parseColor("#B2B2B2"));
//            }
//        }

    }

    public static String timestamp2Date(String str_num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(toLong(str_num)));
        return date;

    }

    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return InformeDataNewBase.size();
    }

    class InformTheDetailsViewHolder extends RecyclerView.ViewHolder {

        TextView inform_the_details_item_time;
        TextView inform_the_details_item_title;
        TextView inform_the_details_item_message;
        RelativeLayout inform_the_details_item_rl;
        TextView inform_the_details_item_tv;
        LinearLayout inform_the_details_item_ll;

        public InformTheDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            inform_the_details_item_time = itemView.findViewById(R.id.inform_the_details_item_time);
            inform_the_details_item_title = itemView.findViewById(R.id.inform_the_details_item_title);
            inform_the_details_item_message = itemView.findViewById(R.id.inform_the_details_item_message);
            inform_the_details_item_rl = itemView.findViewById(R.id.inform_the_details_item_rl);
            inform_the_details_item_tv = itemView.findViewById(R.id.inform_the_details_item_tv);
            inform_the_details_item_ll = itemView.findViewById(R.id.inform_the_details_item_ll);

        }
    }

    public interface OnItemInformClick {
        void ItemInformClick(int position);
    }

}
