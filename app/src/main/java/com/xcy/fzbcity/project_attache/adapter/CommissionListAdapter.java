package com.xcy.fzbcity.project_attache.adapter;

import android.graphics.Color;
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
import com.xcy.fzbcity.all.database.CommissionListBean;

import java.util.List;

//专员端我的佣金适配器
public class CommissionListAdapter extends RecyclerView.Adapter<CommissionListAdapter.CommissionListViewHolder> {
    private List<CommissionListBean.DataBean.RowsBean> rows;

    public void setRows(List<CommissionListBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public CommissionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_attache_item_commission_list, parent, false);
        return new CommissionListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CommissionListViewHolder holder, final int position) {
        //      TODO    左侧
        {
            holder.item_commission_list_tv1.setText(rows.get(position).getCustomerName());
            holder.item_commission_list_tv2.setText(rows.get(position).getProjectName());
            holder.item_commission_list_tv3.setText(rows.get(position).getCompanyName());
            holder.item_commission_list_tv4.setText(rows.get(position).getStoreName());
            holder.item_commission_list_tv5.setText(rows.get(position).getAgentName());

            if (rows.get(position).getRoomNumber().equals("")) {
                holder.item_commission_list_tv0.setVisibility(View.GONE);
            } else {
                holder.item_commission_list_tv0.setText(rows.get(position).getRoomNumber());
            }
        }


        if (rows.get(position).getTotalAmount().equals("") || rows.get(position).getTotalAmount().equals("0") || rows.get(position).getTotalAmount().equals("0.00")) {
            holder.item_commission_list_tv6.setVisibility(View.GONE);
        } else {
            holder.item_commission_list_tv6.setVisibility(View.VISIBLE);
            holder.item_commission_list_tv6.setText("总佣金：￥" + rows.get(position).getTotalAmount() + "");
        }

        if (rows.get(position).getSecondsAmount().equals("") || rows.get(position).getSecondsAmount().equals("0") || rows.get(position).getSecondsAmount().equals("0.00")) {
            holder.item_commission_list_tv7.setVisibility(View.GONE);
        } else {
            holder.item_commission_list_tv7.setVisibility(View.VISIBLE);
            holder.item_commission_list_tv7.setText("秒结：￥" + rows.get(position).getSecondsAmount() + "");
        }

        //      TODO    右侧
        if (rows.get(position).getStatus().equals("0")) {      //      TODO    未结清
            if (rows.get(position).getMoneyStatus() == 0) {     //      TODO    正常单
                holder.item_commission_list_img.setVisibility(View.GONE);
                if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
                    holder.item_commission_list_tv8.setVisibility(View.GONE);
                } else {
                    holder.item_commission_list_tv8.setVisibility(View.VISIBLE);
                    holder.item_commission_list_tv8.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
                }

                if (rows.get(position).getNotAmount().equals("") || rows.get(position).getNotAmount().equals("0") || rows.get(position).getNotAmount().equals("0.00")) {
                    holder.item_commission_list_tv9.setVisibility(View.GONE);
                } else {
                    holder.item_commission_list_tv9.setVisibility(View.VISIBLE);
                    holder.item_commission_list_tv9.setText("未结：￥" + rows.get(position).getNotAmount() + "");
                }
                if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                    holder.item_commission_list_tv12.setVisibility(View.GONE);
                } else {
                    holder.item_commission_list_tv12.setVisibility(View.VISIBLE);
                    holder.item_commission_list_tv12.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                }
            } else if (rows.get(position).getMoneyStatus() == 1) {     //      TODO    调单
                holder.item_commission_list_img.setVisibility(View.VISIBLE);
                holder.item_commission_list_img.setBackgroundResource(R.mipmap.tdg);
                if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                    holder.item_commission_list_tv12.setVisibility(View.GONE);
                    if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
                        holder.item_commission_list_tv8.setVisibility(View.GONE);
                    } else {
                        holder.item_commission_list_tv8.setVisibility(View.VISIBLE);
                        holder.item_commission_list_tv8.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
                    }
                    if (rows.get(position).getNotAmount().equals("") || rows.get(position).getNotAmount().equals("0") || rows.get(position).getNotAmount().equals("0.00")) {
                        holder.item_commission_list_tv9.setVisibility(View.GONE);
                    } else {
                        holder.item_commission_list_tv9.setVisibility(View.VISIBLE);
                        holder.item_commission_list_tv9.setText("未结：￥" + rows.get(position).getNotAmount() + "");
                    }
                } else {
                    if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
                        holder.item_commission_list_tv8.setVisibility(View.GONE);
                    } else {
                        holder.item_commission_list_tv8.setVisibility(View.VISIBLE);
                        holder.item_commission_list_tv8.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
                    }
                    holder.item_commission_list_tv12.setVisibility(View.VISIBLE);
                    holder.item_commission_list_tv12.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                }
            } else if (rows.get(position).getMoneyStatus() == 2) {     //      TODO    退单
                if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                    holder.item_commission_list_tv12.setVisibility(View.GONE);
                    {
                        if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
                            holder.item_commission_list_tv8.setVisibility(View.GONE);
                        } else {
                            holder.item_commission_list_tv8.setVisibility(View.VISIBLE);
                            holder.item_commission_list_tv8.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
                        }
                    }
                } else {
                    holder.item_commission_list_tv9.setVisibility(View.GONE);
                    holder.item_commission_list_tv12.setVisibility(View.VISIBLE);
                    holder.item_commission_list_tv12.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                }
                holder.item_commission_list_img.setVisibility(View.VISIBLE);
                holder.item_commission_list_img.setBackgroundResource(R.mipmap.tdr);
            }
            holder.item_commission_list_tv10.setVisibility(View.GONE);
            holder.item_commission_list_tv11.setVisibility(View.GONE);
        } else if (rows.get(position).getStatus().equals("1")) {      //      TODO    已结清
            holder.item_commission_list_tv8.setVisibility(View.GONE);
            holder.item_commission_list_tv9.setVisibility(View.GONE);
            holder.item_commission_list_tv10.setVisibility(View.VISIBLE);
            holder.item_commission_list_tv11.setVisibility(View.VISIBLE);
            holder.item_commission_list_tv11.setText(rows.get(position).getClosingTime());
            if (rows.get(position).getMoneyStatus() == 0) {     //      TODO    正常单
                holder.item_commission_list_img.setVisibility(View.GONE);
            } else if (rows.get(position).getMoneyStatus() == 1) {     //      TODO    调单
                holder.item_commission_list_img.setVisibility(View.VISIBLE);
                holder.item_commission_list_img.setBackgroundResource(R.mipmap.tdg);
                if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                    holder.item_commission_list_tv12.setVisibility(View.GONE);
                } else {
                    holder.item_commission_list_tv10.setVisibility(View.GONE);
                    holder.item_commission_list_tv11.setVisibility(View.GONE);
                    holder.item_commission_list_tv12.setVisibility(View.VISIBLE);
                    holder.item_commission_list_tv12.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                }
            } else if (rows.get(position).getMoneyStatus() == 2) {     //      TODO    退单
                if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00")) {
                    holder.item_commission_list_tv12.setVisibility(View.GONE);
                } else {
                    holder.item_commission_list_tv10.setVisibility(View.GONE);
                    holder.item_commission_list_tv11.setVisibility(View.GONE);
                    holder.item_commission_list_tv12.setVisibility(View.VISIBLE);
                    holder.item_commission_list_tv12.setText("需退还：￥" + rows.get(position).getReturnedMoney());
                }
                holder.item_commission_list_img.setVisibility(View.VISIBLE);
                holder.item_commission_list_img.setBackgroundResource(R.mipmap.tdr);
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

            holder.item_commission_list_tv0.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv1.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv2.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv3.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv4.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv5.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv6.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv7.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv8.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv9.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv10.setTextColor(Color.parseColor("#999999"));
            holder.item_commission_list_tv11.setTextColor(Color.parseColor("#999999"));
            holder.my_brokerage_item_tv_1.setTextColor(Color.parseColor("#999999"));
        } else {//无数据
            holder.my_brokerage_item_rl_1.setVisibility(View.GONE);
            holder.my_brokerage_item_ll_1.setVisibility(View.GONE);
        }

//        {
//            if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00") ) {
//                holder.item_commission_list_tv12.setVisibility(View.GONE);
//            }else {
//                holder.item_commission_list_tv12.setVisibility(View.VISIBLE);
//                holder.item_commission_list_tv12.setText("需退还："+rows.get(position).getReturnedMoney());
//            }
//
//
//            if (rows.get(position).getStatus().equals("0")) {
//                holder.item_commission_list_tv10.setVisibility(View.GONE);
//                holder.item_commission_list_tv11.setVisibility(View.GONE);
//                if (rows.get(position).getMoneyStatus() == 0) {
//                    if (rows.get(position).getTotalAmount().equals("") || rows.get(position).getTotalAmount().equals("0") || rows.get(position).getTotalAmount().equals("0.00")) {
//                        holder.item_commission_list_tv6.setVisibility(View.GONE);
//                    }else {
//                        holder.item_commission_list_tv6.setVisibility(View.VISIBLE);
//                        holder.item_commission_list_tv6.setText("总佣金：￥" + rows.get(position).getTotalAmount() + "");
//                    }
//
//                    if (rows.get(position).getSecondsAmount().equals("") || rows.get(position).getSecondsAmount().equals("0") || rows.get(position).getSecondsAmount().equals("0.00")) {
//                        holder.item_commission_list_tv7.setText("无秒结");
//                    }else {
//                        holder.item_commission_list_tv7.setText("秒结：￥" + rows.get(position).getSecondsAmount() + "");
//                    }
//
//                    if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
//                        holder.item_commission_list_tv8.setVisibility(View.GONE);
//                    }else {
//                        holder.item_commission_list_tv8.setVisibility(View.VISIBLE);
//                        holder.item_commission_list_tv8.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
//                    }
//
//                    if (rows.get(position).getNotAmount().equals("") || rows.get(position).getNotAmount().equals("0") || rows.get(position).getNotAmount().equals("0.00")) {
//                        holder.item_commission_list_tv9.setVisibility(View.GONE);
//                    }else {
//                        holder.item_commission_list_tv9.setVisibility(View.VISIBLE);
//                        holder.item_commission_list_tv9.setText("未结：￥" + rows.get(position).getNotAmount() + "");
//                    }
//                } else if (rows.get(position).getMoneyStatus() == 1) {
//
//                    if (rows.get(position).getTotalAmount().equals("") || rows.get(position).getTotalAmount().equals("0") || rows.get(position).getTotalAmount().equals("0.00")) {
//                        holder.item_commission_list_tv6.setVisibility(View.GONE);
//                    }else {
//                        holder.item_commission_list_tv6.setVisibility(View.VISIBLE);
//                        holder.item_commission_list_tv6.setText("总佣金：￥" + rows.get(position).getTotalAmount() + "");
//                    }
//
//                    if (rows.get(position).getSecondsAmount().equals("")  || rows.get(position).getSecondsAmount().equals("0") || rows.get(position).getSecondsAmount().equals("0.00")) {
//                        holder.item_commission_list_tv7.setText("无秒结");
//                    }else {
//                        holder.item_commission_list_tv7.setText("秒结：￥" + rows.get(position).getSecondsAmount() + "");
//                    }
//
//                    if (rows.get(position).getAlreadyAmount().equals("") || rows.get(position).getAlreadyAmount().equals("0") || rows.get(position).getAlreadyAmount().equals("0.00")) {
//                        holder.item_commission_list_tv8.setVisibility(View.GONE);
//                    }else {
//                        holder.item_commission_list_tv8.setVisibility(View.VISIBLE);
//                        holder.item_commission_list_tv8.setText("已结：￥" + rows.get(position).getAlreadyAmount() + "");
//                    }
//
//                    if (rows.get(position).getNotAmount().equals("") || rows.get(position).getNotAmount().equals("0") || rows.get(position).getNotAmount().equals("0.00")) {
//                        holder.item_commission_list_tv9.setVisibility(View.GONE);
//                    }else {
//                        holder.item_commission_list_tv9.setVisibility(View.VISIBLE);
//                        holder.item_commission_list_tv9.setText("未结：￥" + rows.get(position).getNotAmount() + "");
//                    }
//                    holder.item_commission_list_img.setImageResource(R.mipmap.tdg);
//                    if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00") ) {
//                        holder.item_commission_list_tv12.setVisibility(View.GONE);
//                    }else {
//                        holder.item_commission_list_tv12.setVisibility(View.VISIBLE);
//                        holder.item_commission_list_tv12.setText("需退还："+rows.get(position).getReturnedMoney());
//                    }
//
//                } else if (rows.get(position).getMoneyStatus() == 2) {
//
//                    holder.item_commission_list_tv6.setVisibility(View.GONE);
//
//                    holder.item_commission_list_img.setImageResource(R.mipmap.tdr);
//                    if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00") ) {
//                        holder.item_commission_list_tv12.setVisibility(View.GONE);
//                    }else {
//                        holder.item_commission_list_tv12.setVisibility(View.VISIBLE);
//                        holder.item_commission_list_tv12.setText("需退还："+rows.get(position).getReturnedMoney());
//                    }
//                    holder.item_commission_list_tv6.setText("总佣金：￥" + rows.get(position).getTotalAmount() + "");
//                }
//            }
//            else {
//
//                holder.item_commission_list_tv6.setVisibility(View.VISIBLE);
//                holder.item_commission_list_tv7.setVisibility(View.VISIBLE);
//                holder.item_commission_list_tv11.setVisibility(View.VISIBLE);
//
//                if (rows.get(position).getTotalAmount().equals("") || rows.get(position).getTotalAmount().equals("0") || rows.get(position).getTotalAmount().equals("0.00")) {
//                    holder.item_commission_list_tv6.setVisibility(View.GONE);
//                }else {
//                    holder.item_commission_list_tv6.setVisibility(View.VISIBLE);
//                    holder.item_commission_list_tv6.setText("总佣金：￥" + rows.get(position).getTotalAmount() + "");
//                }
//
//                if (rows.get(position).getSecondsAmount().equals("")  || rows.get(position).getSecondsAmount().equals("0") || rows.get(position).getSecondsAmount().equals("0.00")) {
//                    holder.item_commission_list_tv7.setVisibility(View.GONE);
//                    holder.item_commission_list_tv7.setText("无秒结");
//                }else {
//                    holder.item_commission_list_tv7.setVisibility(View.VISIBLE);
//                    holder.item_commission_list_tv7.setText("秒结：￥" + rows.get(position).getSecondsAmount() + "");
//                }
//
//                holder.item_commission_list_tv8.setVisibility(View.GONE);
//
//
//                if (rows.get(position).getNotAmount().equals("") || rows.get(position).getNotAmount().equals("0") || rows.get(position).getNotAmount().equals("0.00")) {
//                    holder.item_commission_list_tv9.setVisibility(View.GONE);
//                }else {
//                    holder.item_commission_list_tv9.setVisibility(View.VISIBLE);
//                    holder.item_commission_list_tv9.setText("未结：￥" + rows.get(position).getNotAmount() + "");
//                }
//                if (rows.get(position).getReturnedMoney().equals("") || rows.get(position).getReturnedMoney().equals("0") || rows.get(position).getReturnedMoney().equals("0.00") ) {
//                    holder.item_commission_list_tv12.setVisibility(View.GONE);
//                }else {
//                    holder.item_commission_list_tv12.setVisibility(View.VISIBLE);
//                    holder.item_commission_list_tv12.setText("需退还："+rows.get(position).getReturnedMoney());
//                }
//
//                holder.item_commission_list_tv6.setText("总佣金：￥" + rows.get(position).getTotalAmount() + "");
//                holder.item_commission_list_tv10.setVisibility(View.VISIBLE);
//                holder.item_commission_list_tv11.setText(rows.get(position).getClosingTime() + "");
//
//            }
//
//            if (rows.get(position).getMoneyStatus() == 0) {
//                holder.item_commission_list_img.setVisibility(View.GONE);
//            } else if (rows.get(position).getMoneyStatus() == 1) {
//                holder.item_commission_list_img.setVisibility(View.VISIBLE);
//                holder.item_commission_list_img.setBackgroundResource(R.mipmap.tdg);
//            } else if (rows.get(position).getMoneyStatus() == 2) {
//                holder.item_commission_list_img.setVisibility(View.VISIBLE);
//                holder.item_commission_list_img.setBackgroundResource(R.mipmap.tdr);
//
//            }
//        }

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class CommissionListViewHolder extends RecyclerView.ViewHolder {
        TextView item_commission_list_tv0;
        TextView item_commission_list_tv1;
        TextView item_commission_list_tv2;
        TextView item_commission_list_tv3;
        TextView item_commission_list_tv4;
        TextView item_commission_list_tv5;
        TextView item_commission_list_tv6;
        TextView item_commission_list_tv7;
        TextView item_commission_list_tv8;
        TextView item_commission_list_tv9;
        TextView item_commission_list_tv10;
        TextView item_commission_list_tv11;
        TextView item_commission_list_tv12;
        ImageView item_commission_list_img;

        RelativeLayout my_brokerage_item_rl_1;
        LinearLayout my_brokerage_item_ll_1;
        TextView my_brokerage_item_tv_1;

        public CommissionListViewHolder(@NonNull View itemView) {
            super(itemView);
            item_commission_list_tv0 = itemView.findViewById(R.id.item_commission_list_tv0);
            item_commission_list_tv1 = itemView.findViewById(R.id.item_commission_list_tv1);
            item_commission_list_tv2 = itemView.findViewById(R.id.item_commission_list_tv2);
            item_commission_list_tv3 = itemView.findViewById(R.id.item_commission_list_tv3);
            item_commission_list_tv4 = itemView.findViewById(R.id.item_commission_list_tv4);
            item_commission_list_tv5 = itemView.findViewById(R.id.item_commission_list_tv5);
            item_commission_list_tv6 = itemView.findViewById(R.id.item_commission_list_tv6);
            item_commission_list_tv7 = itemView.findViewById(R.id.item_commission_list_tv7);
            item_commission_list_tv8 = itemView.findViewById(R.id.item_commission_list_tv8);
            item_commission_list_tv9 = itemView.findViewById(R.id.item_commission_list_tv9);
            item_commission_list_tv10 = itemView.findViewById(R.id.item_commission_list_tv10);
            item_commission_list_tv11 = itemView.findViewById(R.id.item_commission_list_tv11);
            item_commission_list_tv12 = itemView.findViewById(R.id.item_commission_list_tv12);

            item_commission_list_img = itemView.findViewById(R.id.item_commission_list_img);

            my_brokerage_item_rl_1 = itemView.findViewById(R.id.project_brokerage_item_rl_1);
            my_brokerage_item_ll_1 = itemView.findViewById(R.id.project_brokerage_item_ll_1);
            my_brokerage_item_tv_1 = itemView.findViewById(R.id.project_brokerage_item_tv_1);

        }
    }

}
