package com.xcy.fzbcity.project_side.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ReceivableBean;

import java.util.List;

//专案端我的佣金适配器
public class TheProjectEndCommissionAdapter extends RecyclerView.Adapter<TheProjectEndCommissionAdapter.TheProjectEndCommissionViewHolder> {

    private List<ReceivableBean.DataBean.RowsBean> rowsBeanList;
    private Context context;

    public TheProjectEndCommissionAdapter(List<ReceivableBean.DataBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
    }

    @NonNull
    @Override
    public TheProjectEndCommissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_side_item_commission_the_project_end, parent, false);
        context = parent.getContext();
        return new TheProjectEndCommissionViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TheProjectEndCommissionViewHolder holder, final int position) {
        //      TODO    左侧
        {
            holder.the_project_end_title.setText(rowsBeanList.get(position).getCustomerName() + "  (" + rowsBeanList.get(position).getCustomerPhone() + ")");
            holder.the_project_end_name.setText(rowsBeanList.get(position).getProjectName());

            if (rowsBeanList.get(position).getRoomNumber().equals("")) {
                holder.the_project_end_time.setVisibility(View.GONE);
            } else {
                holder.the_project_end_time.setVisibility(View.VISIBLE);
                holder.the_project_end_time.setText(rowsBeanList.get(position).getRoomNumber());
            }

            if (rowsBeanList.get(position).getTradeDate().equals("")) {
                holder.the_project_end_bargain_time.setVisibility(View.GONE);
            } else {
                holder.the_project_end_time.setVisibility(View.VISIBLE);
                holder.the_project_end_bargain_time.setText("成交时间：" + rowsBeanList.get(position).getTradeDate());
            }

            if (rowsBeanList.get(position).getCompanyName().equals("") && rowsBeanList.get(position).getStoreName().equals("")) {
                holder.the_project_end_company.setText(rowsBeanList.get(position).getAgentName());
            } else {
                holder.the_project_end_time.setVisibility(View.VISIBLE);
                holder.the_project_end_company.setText(rowsBeanList.get(position).getCompanyName() + "-" + rowsBeanList.get(position).getStoreName() + "-" + rowsBeanList.get(position).getAgentName());
            }
        }

        if (rowsBeanList.get(position).getSecondsAmount().equals("") || rowsBeanList.get(position).getSecondsAmount().equals("0") || rowsBeanList.get(position).getSecondsAmount().equals("0.00")) {
            holder.the_project_end_tv5.setVisibility(View.GONE);
        } else {
            holder.the_project_end_tv5.setVisibility(View.VISIBLE);
            holder.the_project_end_tv5.setText("秒结：￥" + rowsBeanList.get(position).getSecondsAmount() + "");
        }

        if (rowsBeanList.get(position).getTotalAmount().equals("") || rowsBeanList.get(position).getTotalAmount().equals("0") || rowsBeanList.get(position).getTotalAmount().equals("0.00")) {
            holder.the_project_end_tv1.setVisibility(View.GONE);
        } else {
            holder.the_project_end_tv1.setVisibility(View.VISIBLE);
            holder.the_project_end_tv1.setText("总佣金：¥" + rowsBeanList.get(position).getTotalAmount());
        }

        //  TODO    右侧
        {
            if (rowsBeanList.get(position).getStatus().equals("0")) {       //      TODO    未结清
                holder.the_project_end_tv6.setVisibility(View.GONE);
                holder.the_project_end_tv7.setVisibility(View.GONE);
                if (rowsBeanList.get(position).getMoneyStatus() == 0) {      //      TODO    正常单
                    holder.the_project_end_img.setVisibility(View.GONE);
                    if (rowsBeanList.get(position).getAlreadyAmount().equals("") || rowsBeanList.get(position).getAlreadyAmount().equals("0") || rowsBeanList.get(position).getAlreadyAmount().equals("0.00")) {
                        holder.the_project_end_tv2.setVisibility(View.GONE);
                    } else {
                        holder.the_project_end_tv2.setVisibility(View.VISIBLE);
                        holder.the_project_end_tv2.setText("已结：¥" + rowsBeanList.get(position).getAlreadyAmount());
                    }
                    if (rowsBeanList.get(position).getNotAmount().equals("") || rowsBeanList.get(position).getNotAmount().equals("0") || rowsBeanList.get(position).getNotAmount().equals("0.00")) {
                        holder.the_project_end_tv4.setVisibility(View.GONE);
                    } else {
                        holder.the_project_end_tv4.setVisibility(View.VISIBLE);
                        holder.the_project_end_tv4.setText("未结：¥" + rowsBeanList.get(position).getNotAmount());
                    }
                    if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0") || rowsBeanList.get(position).getReturnedMoney().equals("0.00")) {
                        holder.the_project_end_tv8.setVisibility(View.GONE);     //      TODO    无需退还
                    } else {
                        holder.the_project_end_tv8.setVisibility(View.VISIBLE);     //      TODO    需退还
                        holder.the_project_end_tv8.setText("需退还：" + rowsBeanList.get(position).getReturnedMoney());
                    }
                } else if (rowsBeanList.get(position).getMoneyStatus() == 1) {      //      TODO    调单
                    holder.the_project_end_img.setVisibility(View.VISIBLE);
                    holder.the_project_end_img.setBackgroundResource(R.mipmap.tdg);
                    if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0") || rowsBeanList.get(position).getReturnedMoney().equals("0.00")) {
                        holder.the_project_end_tv8.setVisibility(View.GONE);     //      TODO    无需退还
                        if (rowsBeanList.get(position).getAlreadyAmount().equals("") || rowsBeanList.get(position).getAlreadyAmount().equals("0") || rowsBeanList.get(position).getAlreadyAmount().equals("0.00")) {
                            holder.the_project_end_tv2.setVisibility(View.GONE);
                        } else {
                            holder.the_project_end_tv2.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv2.setText("已结：¥" + rowsBeanList.get(position).getAlreadyAmount());
                        }
                        if (rowsBeanList.get(position).getInvoiceMoney().equals("") || rowsBeanList.get(position).getInvoiceMoney().equals("0") || rowsBeanList.get(position).getInvoiceMoney().equals("0.00")) {
                            holder.the_project_end_tv3.setVisibility(View.GONE);
                        } else {
                            holder.the_project_end_tv3.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv3.setText("待结：¥" + rowsBeanList.get(position).getInvoiceMoney());
                        }
                        if (rowsBeanList.get(position).getNotAmount().equals("") || rowsBeanList.get(position).getNotAmount().equals("0") || rowsBeanList.get(position).getNotAmount().equals("0.00")) {
                            holder.the_project_end_tv4.setVisibility(View.GONE);
                        } else {
                            holder.the_project_end_tv4.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv4.setText("未结：¥" + rowsBeanList.get(position).getNotAmount());
                        }
                    } else {
                        if (rowsBeanList.get(position).getAlreadyAmount().equals("") || rowsBeanList.get(position).getAlreadyAmount().equals("0") || rowsBeanList.get(position).getAlreadyAmount().equals("0.00")) {
                            holder.the_project_end_tv2.setVisibility(View.GONE);
                        } else {
                            holder.the_project_end_tv2.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv2.setText("已结：¥" + rowsBeanList.get(position).getAlreadyAmount());
                        }
                        if (rowsBeanList.get(position).getInvoiceMoney().equals("") || rowsBeanList.get(position).getInvoiceMoney().equals("0") || rowsBeanList.get(position).getInvoiceMoney().equals("0.00")) {
                            holder.the_project_end_tv3.setVisibility(View.GONE);
                        } else {
                            holder.the_project_end_tv3.setVisibility(View.VISIBLE);
                            holder.the_project_end_tv3.setText("待结：¥" + rowsBeanList.get(position).getInvoiceMoney());
                        }
                        holder.the_project_end_tv4.setVisibility(View.GONE);
                        holder.the_project_end_tv8.setVisibility(View.VISIBLE);     //      TODO    需退还
                        holder.the_project_end_tv8.setText("需退还：" + rowsBeanList.get(position).getReturnedMoney());
                    }
                } else if (rowsBeanList.get(position).getMoneyStatus() == 2) {      //      TODO    退单
                    holder.the_project_end_img.setVisibility(View.VISIBLE);
                    holder.the_project_end_img.setBackgroundResource(R.mipmap.tdr);
                    if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0") || rowsBeanList.get(position).getReturnedMoney().equals("0.00")) {
                        holder.the_project_end_tv8.setVisibility(View.GONE);     //      TODO    无需退还
                    } else {
                        holder.the_project_end_tv8.setVisibility(View.VISIBLE);     //      TODO    需退还
                        holder.the_project_end_tv8.setText("需退还：" + rowsBeanList.get(position).getReturnedMoney());
                    }
                    holder.the_project_end_tv2.setVisibility(View.GONE);
                    holder.the_project_end_tv3.setVisibility(View.GONE);
                    holder.the_project_end_tv4.setVisibility(View.GONE);

                }
            } else if (rowsBeanList.get(position).getStatus().equals("1")) {       //      TODO    已结清
                holder.the_project_end_tv2.setVisibility(View.GONE);
                holder.the_project_end_tv3.setVisibility(View.GONE);
                holder.the_project_end_tv4.setVisibility(View.GONE);
                holder.the_project_end_tv6.setVisibility(View.VISIBLE);
                holder.the_project_end_tv7.setVisibility(View.VISIBLE);
                holder.the_project_end_tv7.setText(rowsBeanList.get(position).getClosingTime());
                if (rowsBeanList.get(position).getMoneyStatus() == 0) {      //      TODO    正常单
                    holder.the_project_end_img.setVisibility(View.GONE);
                    holder.the_project_end_tv4.setVisibility(View.GONE);
                    if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0") || rowsBeanList.get(position).getReturnedMoney().equals("0.00")) {
                        holder.the_project_end_tv8.setVisibility(View.GONE);     //      TODO    无需退还
                    } else {
                        holder.the_project_end_tv6.setVisibility(View.GONE);
                        holder.the_project_end_tv7.setVisibility(View.GONE);
                        holder.the_project_end_tv8.setVisibility(View.VISIBLE);     //      TODO    需退还
                        holder.the_project_end_tv8.setText("需退还：" + rowsBeanList.get(position).getReturnedMoney());
                    }
                } else if (rowsBeanList.get(position).getMoneyStatus() == 1) {      //      TODO    调单
                    holder.the_project_end_img.setVisibility(View.VISIBLE);
                    holder.the_project_end_img.setBackgroundResource(R.mipmap.tdg);
                    if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0") || rowsBeanList.get(position).getReturnedMoney().equals("0.00")) {
                        holder.the_project_end_tv8.setVisibility(View.GONE);     //      TODO    无需退还
                    } else {
                        holder.the_project_end_tv6.setVisibility(View.GONE);
                        holder.the_project_end_tv7.setVisibility(View.GONE);
                        holder.the_project_end_tv8.setVisibility(View.VISIBLE);     //      TODO    需退还
                        holder.the_project_end_tv8.setText("需退还：" + rowsBeanList.get(position).getReturnedMoney());
                    }
                } else if (rowsBeanList.get(position).getMoneyStatus() == 2) {      //      TODO    退单
                    holder.the_project_end_img.setVisibility(View.VISIBLE);
                    holder.the_project_end_img.setBackgroundResource(R.mipmap.tdr);
                    if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0") || rowsBeanList.get(position).getReturnedMoney().equals("0.00")) {
                        holder.the_project_end_tv8.setVisibility(View.GONE);     //      TODO    无需退还
                    } else {
                        holder.the_project_end_tv6.setVisibility(View.GONE);
                        holder.the_project_end_tv7.setVisibility(View.GONE);
                        holder.the_project_end_tv8.setVisibility(View.VISIBLE);     //      TODO    需退还
                        holder.the_project_end_tv8.setText("需退还：" + rowsBeanList.get(position).getReturnedMoney());
                    }
                    holder.the_project_end_tv2.setVisibility(View.GONE);
                    holder.the_project_end_tv3.setVisibility(View.GONE);
                    holder.the_project_end_tv4.setVisibility(View.GONE);

                }
            }
        }


        //历史数据填充数据
        if (rowsBeanList.get(position).getHistoryData().equals("1")) {//有数据

        } else {//无数据

        }

//        if (rowsBeanList.get(position).getStatus().equals("0")) {
//            holder.the_project_end_tv6.setVisibility(View.GONE);
//            holder.the_project_end_tv7.setVisibility(View.GONE);
//        } else if (rowsBeanList.get(position).getStatus().equals("1")) {
//            holder.the_project_end_tv2.setVisibility(View.GONE);
//            holder.the_project_end_tv3.setVisibility(View.GONE);
//            holder.the_project_end_tv4.setVisibility(View.GONE);
//            holder.the_project_end_tv5.setVisibility(View.GONE);
//            holder.the_project_end_tv6.setVisibility(View.VISIBLE);
//            holder.the_project_end_tv7.setVisibility(View.VISIBLE);
//            holder.the_project_end_tv7.setText(rowsBeanList.get(position).getClosingTime());
//        }
//        if (rowsBeanList.get(position).getReturnedMoney().equals("") || rowsBeanList.get(position).getReturnedMoney().equals("0") || rowsBeanList.get(position).getReturnedMoney().equals("0.00") ) {
//            holder.the_project_end_tv8.setVisibility(View.GONE);
//        }else {
//            holder.the_project_end_tv8.setVisibility(View.VISIBLE);
//            holder.the_project_end_tv8.setText("需退还："+rowsBeanList.get(position).getReturnedMoney());
//        }
//
//        if (rowsBeanList.get(position).getTotalAmount().equals("") || rowsBeanList.get(position).getTotalAmount().equals("0") || rowsBeanList.get(position).getTotalAmount().equals("0.00")) {
//            holder.the_project_end_tv1.setVisibility(View.GONE);
//        }else {
//            holder.the_project_end_tv1.setVisibility(View.VISIBLE);
//            holder.the_project_end_tv1.setText("总佣金：¥" + rowsBeanList.get(position).getTotalAmount());
//        }
//
//        if (rowsBeanList.get(position).getMoneyStatus() == 0) {
//            holder.the_project_end_img.setVisibility(View.GONE);
//        } else if (rowsBeanList.get(position).getMoneyStatus() == 1) {
//            holder.the_project_end_img.setVisibility(View.VISIBLE);
//            holder.the_project_end_img.setBackgroundResource(R.mipmap.tdg);
//        } else if (rowsBeanList.get(position).getMoneyStatus() == 2) {
//            holder.the_project_end_img.setVisibility(View.VISIBLE);
//            holder.the_project_end_img.setBackgroundResource(R.mipmap.tdr);
//            holder.the_project_end_tv2.setVisibility(View.GONE);
//            holder.the_project_end_tv3.setVisibility(View.GONE);
//            holder.the_project_end_tv4.setVisibility(View.GONE);
//
//        }
    }

    @Override
    public int getItemCount() {
        return rowsBeanList.size();
    }

    class TheProjectEndCommissionViewHolder extends RecyclerView.ViewHolder {

        ImageView the_project_end_img;

        TextView the_project_end_title;
        TextView the_project_end_name;
        TextView the_project_end_time;
        TextView the_project_end_bargain_time;
        TextView the_project_end_company;
        TextView the_project_end_tv0;
        TextView the_project_end_tv1;
        TextView the_project_end_tv2;
        TextView the_project_end_tv3;
        TextView the_project_end_tv4;
        TextView the_project_end_tv5;
        TextView the_project_end_tv6;
        TextView the_project_end_tv7;
        TextView the_project_end_tv8;

        public TheProjectEndCommissionViewHolder(@NonNull View itemView) {
            super(itemView);

            the_project_end_img = itemView.findViewById(R.id.the_project_end_img);
            the_project_end_title = itemView.findViewById(R.id.the_project_end_title);
            the_project_end_name = itemView.findViewById(R.id.the_project_end_name);
            the_project_end_time = itemView.findViewById(R.id.the_project_end_time);
            the_project_end_bargain_time = itemView.findViewById(R.id.the_project_end_bargain_time);
            the_project_end_company = itemView.findViewById(R.id.the_project_end_company);
            the_project_end_tv0 = itemView.findViewById(R.id.the_project_end_tv0);
            the_project_end_tv1 = itemView.findViewById(R.id.the_project_end_tv1);
            the_project_end_tv2 = itemView.findViewById(R.id.the_project_end_tv2);
            the_project_end_tv3 = itemView.findViewById(R.id.the_project_end_tv3);
            the_project_end_tv4 = itemView.findViewById(R.id.the_project_end_tv4);
            the_project_end_tv5 = itemView.findViewById(R.id.the_project_end_tv5);
            the_project_end_tv6 = itemView.findViewById(R.id.the_project_end_tv6);
            the_project_end_tv7 = itemView.findViewById(R.id.the_project_end_tv7);
            the_project_end_tv8 = itemView.findViewById(R.id.the_project_end_tv8);

        }
    }

}
