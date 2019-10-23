package com.xcy.fzb.captain_team.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.database.MyClientFragmentBean;

import java.util.List;

public class MakeABargainAdapter extends RecyclerView.Adapter<MakeABargainAdapter.FailureViewHolder> {

    private List<MyClientFragmentBean.DataBean.ProcessDataBean> listData;

    public void setListData(List<MyClientFragmentBean.DataBean.ProcessDataBean> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public FailureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_makeabargain, parent, false);

        return new FailureViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FailureViewHolder holder, int position) {


        int size = listData.get(position).getJsonDatas().size();
        Log.i("MyCL", "长度：" + size);
        if (listData.get(position).getJsonDatas().equals("")) {

        } else {
            if(position == 0){

            }else {
                holder.item_makeabargain_lls.setVisibility(View.VISIBLE);
                holder.textView2.setVisibility(View.VISIBLE);
                holder.textView2.setText(listData.get(position).getRecordName());
                for (int i = 0; i < size; ++i) {
                    if (i == size) {
                        break;
                    } else if (i == 0) {

                        holder.textView1.setText(listData.get(position).getJsonDatas().get(1).getValue());
                    } else if (i == 1) {
                        holder.textView3.setVisibility(View.VISIBLE);
                        holder.textView3.setText(listData.get(position).getJsonDatas().get(0).getKey() + ":" + listData.get(position).getJsonDatas().get(0).getValue());
                    } else if (i == 2) {
                        holder.textView4.setVisibility(View.VISIBLE);
                        holder.textView4.setText(listData.get(position).getJsonDatas().get(2).getKey() + ":" + listData.get(position).getJsonDatas().get(2).getValue());
                    } else if (i == 3) {
                        holder.textView5.setVisibility(View.VISIBLE);
                        holder.textView5.setText(listData.get(position).getJsonDatas().get(3).getKey() + ":" + listData.get(position).getJsonDatas().get(3).getValue());
                    } else if (i == 4) {
                        holder.textView6.setVisibility(View.VISIBLE);
                        holder.textView6.setText(listData.get(position).getJsonDatas().get(4).getKey() + ":" + listData.get(position).getJsonDatas().get(4).getValue());
                    } else if (i == 5) {
                        holder.textView7.setVisibility(View.VISIBLE);
                        holder.textView7.setText(listData.get(position).getJsonDatas().get(5).getKey() + ":" + listData.get(position).getJsonDatas().get(5).getValue());
                    } else if (i == 6) {
                        holder.textView7.setVisibility(View.VISIBLE);
                        holder.textView7.setText(listData.get(position).getJsonDatas().get(6).getKey() + ":" + listData.get(position).getJsonDatas().get(6).getValue());
                    } else if (i == 7) {
                        holder.textView8.setVisibility(View.VISIBLE);
                        holder.textView8.setText(listData.get(position).getJsonDatas().get(7).getKey() + ":" + listData.get(position).getJsonDatas().get(7).getValue());
                    } else if (i == 8) {
                        holder.textView9.setVisibility(View.VISIBLE);
                        holder.textView9.setText(listData.get(position).getJsonDatas().get(8).getKey() + ":" + listData.get(position).getJsonDatas().get(8).getValue());
                    } else if (i == 9) {
                        holder.textView10.setVisibility(View.VISIBLE);
                        holder.textView10.setText(listData.get(position).getJsonDatas().get(9).getKey() + ":" + listData.get(position).getJsonDatas().get(9).getValue());
                    } else if (i == 10) {
                        holder.textView10.setVisibility(View.VISIBLE);
                        holder.textView11.setText(listData.get(position).getJsonDatas().get(10).getKey() + ":" + listData.get(position).getJsonDatas().get(10).getValue());
                    } else if (i == 11) {
                        holder.textView12.setVisibility(View.VISIBLE);
                        holder.textView12.setText(listData.get(position).getJsonDatas().get(11).getKey() + ":" + listData.get(position).getJsonDatas().get(11).getValue());
                    } else if (i == 12) {
                        holder.textView13.setVisibility(View.VISIBLE);
                        holder.textView13.setText(listData.get(position).getJsonDatas().get(12).getKey() + ":" + listData.get(position).getJsonDatas().get(12).getValue());
                    } else if (i == 13) {
                        holder.textView14.setVisibility(View.VISIBLE);
                        holder.textView14.setText(listData.get(position).getJsonDatas().get(13).getKey() + ":" + listData.get(position).getJsonDatas().get(13).getValue());
                    } else if (i == 14) {
                        holder.textView16.setVisibility(View.VISIBLE);
                        holder.textView15.setText(listData.get(position).getJsonDatas().get(14).getKey() + ":" + listData.get(position).getJsonDatas().get(14).getValue());
                    } else if (i == 15) {
                        holder.textView16.setVisibility(View.VISIBLE);
                        holder.textView16.setText(listData.get(position).getJsonDatas().get(15).getKey() + ":" + listData.get(position).getJsonDatas().get(15).getValue());
                    } else if (i == 16) {
                        holder.textView17.setVisibility(View.VISIBLE);
                        holder.textView17.setText(listData.get(position).getJsonDatas().get(16).getKey() + ":" + listData.get(position).getJsonDatas().get(16).getValue());
                    } else if (i == 17) {
                        holder.textView18.setVisibility(View.VISIBLE);
                        holder.textView18.setText(listData.get(position).getJsonDatas().get(17).getKey() + ":" + listData.get(position).getJsonDatas().get(17).getValue());
                    } else if (i == 18) {
                        holder.textView19.setVisibility(View.VISIBLE);
                        holder.textView19.setText(listData.get(position).getJsonDatas().get(18).getKey() + ":" + listData.get(position).getJsonDatas().get(18).getValue());
                    } else if (i == 19) {
                        holder.textView20.setVisibility(View.VISIBLE);
                        holder.textView20.setText(listData.get(position).getJsonDatas().get(19).getKey() + ":" + listData.get(position).getJsonDatas().get(19).getValue());
                    }

                }
            }

        }


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class FailureViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        TextView textView10;

        TextView textView11;
        TextView textView12;
        TextView textView13;
        TextView textView14;
        TextView textView15;
        TextView textView16;
        TextView textView17;
        TextView textView18;
        TextView textView19;
        TextView textView20;

        LinearLayout item_makeabargain_lls;

        public FailureViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.item_makeabargain_tv1);
            textView2 = itemView.findViewById(R.id.item_makeabargain_tv2);
            textView3 = itemView.findViewById(R.id.item_makeabargain_tv3);
            textView4 = itemView.findViewById(R.id.item_makeabargain_tv4);
            textView5 = itemView.findViewById(R.id.item_makeabargain_tv5);
            textView6 = itemView.findViewById(R.id.item_makeabargain_tv6);
            textView7 = itemView.findViewById(R.id.item_makeabargain_tv7);
            textView8 = itemView.findViewById(R.id.item_makeabargain_tv8);
            textView9 = itemView.findViewById(R.id.item_makeabargain_tv9);
            textView10 = itemView.findViewById(R.id.item_makeabargain_tv10);
            textView11 = itemView.findViewById(R.id.item_makeabargain_tv11);
            textView12 = itemView.findViewById(R.id.item_makeabargain_tv12);
            textView13 = itemView.findViewById(R.id.item_makeabargain_tv13);
            textView14 = itemView.findViewById(R.id.item_makeabargain_tv14);
            textView15 = itemView.findViewById(R.id.item_makeabargain_tv15);
            textView16 = itemView.findViewById(R.id.item_makeabargain_tv16);
            textView17 = itemView.findViewById(R.id.item_makeabargain_tv17);
            textView18 = itemView.findViewById(R.id.item_makeabargain_tv18);
            textView19 = itemView.findViewById(R.id.item_makeabargain_tv19);
            textView20 = itemView.findViewById(R.id.item_makeabargain_tv20);

            item_makeabargain_lls = itemView.findViewById(R.id.item_makeabargain_lls);

        }
    }

}
