package com.xcy.fzbcity.all.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.database.BorkerageDownBean;

import java.util.List;

//经纪人端我的佣金适配器
public class BrokerageAdapter extends RecyclerView.Adapter<BrokerageAdapter.BrokerageViewHolder> {

    List<BorkerageDownBean.DataBean.RowsBean> rows;

    public void setRows(List<BorkerageDownBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public BrokerageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i("MyCL", "测试1:");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_brokerage_item1, parent, false);

        return new BrokerageViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final BrokerageViewHolder holder, final int position) {

        Log.i("MyCL", "测试:");
        //  TODO    左侧
        holder.brokerage_item_name.setText(rows.get(position).getCustomerName() + "(" + rows.get(position).getCustomerPhone() + ")");
        holder.brokerage_item_roomNumber.setText(rows.get(position).getRoomNumber());
        holder.brokerage_item_projectName.setText(rows.get(position).getProjectName());
        holder.brokerage_item_ziji.setText("自己");
        holder.brokerage_item_tradeDat.setText("成交时间：" + rows.get(position).getTradeDate());
        holder.brokerage_item_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rows.get(position).getCustomerPhone().equals("")) {

                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + rows.get(position).getCustomerPhone()));//跳转到拨号界面，同时传递电话号码
                    holder.itemView.getContext().startActivity(dialIntent);
                }
            }
        });

        if (rows.get(position).getTotalAmount().equals("") || rows.get(position).getTotalAmount().equals("0") || rows.get(position).getTotalAmount().equals("0.00")) {
            holder.brokerage_item_totalAmount.setVisibility(View.GONE);
        } else {
            holder.brokerage_item_totalAmount.setVisibility(View.VISIBLE);
            holder.brokerage_item_totalAmount.setText("总佣金：￥" + rows.get(position).getTotalAmount());
        }
        //      TODO    右侧
        if (rows.get(position).getStatus().equals("0")) {       //      TODO    未结清
            holder.the_project_end_tv6.setVisibility(View.GONE);
            holder.the_project_end_tv7.setVisibility(View.GONE);
            {
                if (rows.get(position).getMoneyStatus() == 0) {      //      TODO    正常单
                    holder.brokerage_item_img.setVisibility(View.GONE);
                    {
                        if (rows.get(position).getSecondsAmount().equals("") || rows.get(position).getSecondsAmount().equals("0") || rows.get(position).getSecondsAmount().equals("0.00")) {
                            holder.brokerage_item_secondsAmount.setVisibility(View.GONE);
                        } else {
                            holder.brokerage_item_secondsAmount.setVisibility(View.VISIBLE);
                            holder.brokerage_item_secondsAmount.setText("秒结：￥" + rows.get(position).getSecondsAmount() + "");
                        }
                        if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
                            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                        } else {
                            holder.brokerage_item_alreadyAmount.setVisibility(View.VISIBLE);
                            holder.brokerage_item_alreadyAmount.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
                        }
                        if (rows.get(position).getNotAmount().equals("") || rows.get(position).getNotAmount().equals("0") || rows.get(position).getNotAmount().equals("0.00")) {
                            holder.brokerage_item_notAmount.setVisibility(View.GONE);
                        } else {
                            holder.brokerage_item_notAmount.setVisibility(View.VISIBLE);
                            holder.brokerage_item_notAmount.setText("未结：￥" + rows.get(position).getNotAmount() + "");
                        }
                        if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                            holder.the_project_end_tv8.setVisibility(View.GONE);
                        } else {
                            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                            holder.brokerage_item_notAmount.setVisibility(View.GONE);
                            holder.the_project_end_tv8.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv8.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                        }
                    }
                } else if (rows.get(position).getMoneyStatus() == 1) {      //      TODO    调单
                    holder.brokerage_item_img.setVisibility(View.VISIBLE);
                    holder.brokerage_item_img.setBackgroundResource(R.mipmap.tdg);
                    {
                        if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                            holder.the_project_end_tv8.setVisibility(View.GONE);
                            {
                                if (rows.get(position).getSecondsAmount().equals("") || rows.get(position).getSecondsAmount().equals("0") || rows.get(position).getSecondsAmount().equals("0.00")) {
                                    holder.brokerage_item_secondsAmount.setVisibility(View.GONE);
                                } else {
                                    holder.brokerage_item_secondsAmount.setVisibility(View.VISIBLE);
                                    holder.brokerage_item_secondsAmount.setText("秒结：￥" + rows.get(position).getSecondsAmount());
                                }
                                if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
                                    holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                                } else {
                                    holder.brokerage_item_alreadyAmount.setVisibility(View.VISIBLE);
                                    holder.brokerage_item_alreadyAmount.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
                                }
                                if (rows.get(position).getNotAmount().equals("") || rows.get(position).getNotAmount().equals("0") || rows.get(position).getNotAmount().equals("0.00")) {
                                    holder.brokerage_item_notAmount.setVisibility(View.GONE);
                                } else {
                                    holder.brokerage_item_notAmount.setVisibility(View.VISIBLE);
                                    holder.brokerage_item_notAmount.setText("未结：￥" + rows.get(position).getNotAmount() + "");
                                }
                            }
                        } else {
                            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                            holder.brokerage_item_notAmount.setVisibility(View.GONE);
                            holder.the_project_end_tv8.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv8.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                        }
                    }
                } else if (rows.get(position).getMoneyStatus() == 2) {      //      TODO    退单
                    holder.brokerage_item_img.setVisibility(View.VISIBLE);
                    holder.brokerage_item_img.setBackgroundResource(R.mipmap.tdr);
                    {
                        if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                            holder.the_project_end_tv8.setVisibility(View.GONE);
                            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                            holder.brokerage_item_notAmount.setVisibility(View.GONE);
                        } else {
                            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                            holder.brokerage_item_notAmount.setVisibility(View.GONE);
                            holder.the_project_end_tv8.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv8.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                        }
                    }
                }
            }
        } else if (rows.get(position).getStatus().equals("1")) {       //      TODO    已结清
            holder.the_project_end_tv6.setVisibility(View.VISIBLE);
            holder.the_project_end_tv7.setVisibility(View.VISIBLE);
            holder.the_project_end_tv7.setText(rows.get(position).getClosingTime());
            {
                if (rows.get(position).getMoneyStatus() == 0) {      //      TODO    正常单
                    holder.brokerage_item_img.setVisibility(View.GONE);
                    {
                        if (rows.get(position).getSecondsAmount().equals("") || rows.get(position).getSecondsAmount().equals("0") || rows.get(position).getSecondsAmount().equals("0.00")) {
                            holder.brokerage_item_secondsAmount.setVisibility(View.GONE);
                        } else {
                            holder.brokerage_item_secondsAmount.setVisibility(View.VISIBLE);
                            holder.brokerage_item_secondsAmount.setText("秒结：￥" + rows.get(position).getSecondsAmount());
                        }
                        if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
                            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                        } else {
                            holder.brokerage_item_alreadyAmount.setVisibility(View.VISIBLE);
                            holder.brokerage_item_alreadyAmount.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
                        }
                        holder.brokerage_item_notAmount.setVisibility(View.GONE);
                    }
                } else if (rows.get(position).getMoneyStatus() == 1) {      //      TODO    调单
                    holder.brokerage_item_img.setVisibility(View.VISIBLE);
                    holder.brokerage_item_img.setBackgroundResource(R.mipmap.tdg);
                    {
                        if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                            holder.the_project_end_tv8.setVisibility(View.GONE);
                        } else {
                            holder.the_project_end_tv6.setVisibility(View.GONE);
                            holder.the_project_end_tv7.setVisibility(View.GONE);
                            if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
                                holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                            } else {
                                holder.brokerage_item_alreadyAmount.setVisibility(View.VISIBLE);
                                holder.brokerage_item_alreadyAmount.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
                            }
                            holder.brokerage_item_notAmount.setVisibility(View.GONE);
                            holder.the_project_end_tv8.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv8.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                        }
                    }
                } else if (rows.get(position).getMoneyStatus() == 2) {      //      TODO    退单
                    holder.brokerage_item_img.setVisibility(View.VISIBLE);
                    holder.brokerage_item_img.setBackgroundResource(R.mipmap.tdr);
                    {
                        if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                            holder.the_project_end_tv8.setVisibility(View.GONE);
                            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                            holder.brokerage_item_notAmount.setVisibility(View.GONE);
                            holder.brokerage_item_totalAmount.setVisibility(View.GONE);
                            holder.brokerage_item_secondsAmount.setVisibility(View.GONE);
                        } else {
                            holder.the_project_end_tv6.setVisibility(View.GONE);
                            holder.the_project_end_tv7.setVisibility(View.GONE);
                            holder.brokerage_item_alreadyAmount.setVisibility(View.GONE);
                            holder.brokerage_item_notAmount.setVisibility(View.GONE);
                            holder.the_project_end_tv8.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv8.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                        }
                    }

                }
            }
        }


        //历史数据填充数据
        if (rows.get(position).getHistoryData().equals("1")) {//有数据
            holder.my_brokerage_item_rl_1.setVisibility(View.VISIBLE);
            holder.my_brokerage_item_ll_1.setVisibility(View.VISIBLE);
            if (rows.get(position).getCompanyName().equals("")) {
                if (rows.get(position).getStoreName().equals("")) {
                    holder.my_brokerage_item_tv_1.setText("历史数据");
                } else {
                    holder.my_brokerage_item_tv_1.setText(rows.get(position).getStoreName() + "历史数据");
                }
            } else {
                if (rows.get(position).getStoreName().equals("")) {
                    holder.my_brokerage_item_tv_1.setText(rows.get(position).getCompanyName() + "历史数据");
                } else {
                    holder.my_brokerage_item_tv_1.setText(rows.get(position).getCompanyName() + "/" + rows.get(position).getStoreName() + "历史数据");
                }
            }

            holder.brokerage_item_name.setTextColor(Color.parseColor("#999999"));
            holder.brokerage_item_roomNumber.setTextColor(Color.parseColor("#999999"));
            holder.brokerage_item_projectName.setTextColor(Color.parseColor("#999999"));
            holder.brokerage_item_ziji.setTextColor(Color.parseColor("#999999"));
            holder.brokerage_item_tradeDat.setTextColor(Color.parseColor("#999999"));
            holder.brokerage_item_totalAmount.setTextColor(Color.parseColor("#999999"));
            holder.brokerage_item_secondsAmount.setTextColor(Color.parseColor("#999999"));
            holder.brokerage_item_alreadyAmount.setTextColor(Color.parseColor("#999999"));
            holder.brokerage_item_notAmount.setTextColor(Color.parseColor("#999999"));
            holder.the_project_end_tv6.setTextColor(Color.parseColor("#999999"));
            holder.the_project_end_tv7.setTextColor(Color.parseColor("#999999"));
            holder.the_project_end_tv8.setTextColor(Color.parseColor("#999999"));
            holder.my_brokerage_item_tv_1.setTextColor(Color.parseColor("#999999"));
        } else {//无数据
            holder.my_brokerage_item_rl_1.setVisibility(View.GONE);
            holder.my_brokerage_item_ll_1.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class BrokerageViewHolder extends RecyclerView.ViewHolder {

        TextView brokerage_item_name;
        TextView brokerage_item_roomNumber;
        TextView brokerage_item_projectName;
        TextView brokerage_item_ziji;
        TextView brokerage_item_tradeDat;
        TextView brokerage_item_totalAmount;
        TextView brokerage_item_secondsAmount;
        TextView brokerage_item_alreadyAmount;
        TextView brokerage_item_notAmount;
        ImageView brokerage_item_img;
        TextView the_project_end_tv6;
        TextView the_project_end_tv7;
        TextView the_project_end_tv8;

        RelativeLayout my_brokerage_item_rl_1;
        LinearLayout my_brokerage_item_ll_1;
        TextView my_brokerage_item_tv_1;

        public BrokerageViewHolder(@NonNull View itemView) {
            super(itemView);
            brokerage_item_img = itemView.findViewById(R.id.brokerage_item_img);
            brokerage_item_name = itemView.findViewById(R.id.brokerage_item_name);
            brokerage_item_roomNumber = itemView.findViewById(R.id.brokerage_item_roomNumber);
            brokerage_item_projectName = itemView.findViewById(R.id.brokerage_item_projectName);
            brokerage_item_ziji = itemView.findViewById(R.id.brokerage_item_ziji);
            brokerage_item_tradeDat = itemView.findViewById(R.id.brokerage_item_tradeDat);
            brokerage_item_totalAmount = itemView.findViewById(R.id.brokerage_item_totalAmount);
            brokerage_item_secondsAmount = itemView.findViewById(R.id.brokerage_item_secondsAmount);
            brokerage_item_alreadyAmount = itemView.findViewById(R.id.brokerage_item_alreadyAmount);
            brokerage_item_notAmount = itemView.findViewById(R.id.brokerage_item_notAmount);
            the_project_end_tv6 = itemView.findViewById(R.id.the_project_end_tv6_S);
            the_project_end_tv7 = itemView.findViewById(R.id.the_project_end_tv7_S);
            the_project_end_tv8 = itemView.findViewById(R.id.the_project_end_tv8_S);

            my_brokerage_item_rl_1 = itemView.findViewById(R.id.my_brokerage_item_rl_1);
            my_brokerage_item_ll_1 = itemView.findViewById(R.id.my_brokerage_item_ll_1);
            my_brokerage_item_tv_1 = itemView.findViewById(R.id.my_brokerage_item_tv_1);

        }


    }

}
