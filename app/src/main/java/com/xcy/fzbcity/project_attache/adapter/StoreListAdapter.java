package com.xcy.fzbcity.project_attache.adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.StoreListData;
import com.xcy.fzbcity.all.persente.ContactModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact联系人适配器
 *
 * @author nanchen
 * @fileName WaveSideBarView
 * @packageName com.nanchen.wavesidebarview
 * @date 2016/12/27  15:33
 * @github https://github.com/nanchen2251
 */

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ContactsViewHolder> {

    List<ContactModel> contacts = new ArrayList<>();
    private ContactModel contact;
    ItemOnClick itemOnClick;
    List<StoreListData> listData;

    public void setListData(List<StoreListData> listData) {
        this.listData = listData;
    }

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public void setContacts(List<ContactModel> contacts) {
        this.contacts = contacts;
    }


    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.project_attache_layaout_item_store, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactsViewHolder holder, final int position) {


        contact = contacts.get(position);
        if (position == 0 || !contacts.get(position - 1).getIndex().equals(contact.getIndex())) {
            holder.tvIndex.setVisibility(View.VISIBLE);
            holder.tvIndex.setText(contact.getIndex());
        } else {
            holder.tvIndex.setVisibility(View.GONE);
        }

        for (int i = 0; i < listData.size(); ++i) {

            if ((listData.get(i).getCompanyName() + listData.get(i).getStoreName()).equals(contact.getName())) {

                final int FinID = i;

                if (FinalContents.getStoreList().equals("1")) {
                    holder.tvName.setText(listData.get(i).getCompanyName());
                    holder.store_tv.setText("-" + listData.get(i).getStoreName());
                    holder.store_id.setText(listData.get(i).getStoreIdCode());
                    if(listData.get(i).getAgentNum().equals("")){
                        holder.store_num.setText("经纪人：0");
                    }else {
                        holder.store_num.setText("经纪人：" + listData.get(i).getAgentNum());
                    }

                    if (listData.get(i).getShopownerName().equals("")) {
                        holder.contacts_name.setVisibility(View.GONE);
                        holder.contacts_phone.setVisibility(View.GONE);
                    } else {
                        holder.contacts_name.setVisibility(View.VISIBLE);
                        holder.contacts_phone.setVisibility(View.VISIBLE);
                        holder.contacts_name.setText("店长：" + listData.get(i).getShopownerName());
                        holder.contacts_phone.setText(listData.get(i).getShopownerPhone());
                    }

                    if (listData.get(i).getIsMy().equals("1")) {
                        holder.contacts_name_s.setVisibility(View.GONE);
                        holder.contacts_phone_s.setVisibility(View.GONE);
//                        holder.contacts_name_s.setText("自己:" + listData.get(i).getAttacheName());
//                        holder.contacts_phone_s.setText(listData.get(i).getAttachePhone());
                    } else {
                        if (listData.get(i).getAttacheName().equals("")) {
                            holder.contacts_name_s.setVisibility(View.GONE);
                            holder.contacts_phone_s.setVisibility(View.GONE);
                        } else {
                            holder.contacts_name_s.setVisibility(View.VISIBLE);
                            holder.contacts_phone_s.setVisibility(View.VISIBLE);
                            if (listData.get(i).getAttacheIdentity().equals("5")) {
                                holder.contacts_name_s.setText("负责专员:" + listData.get(i).getAttacheName());
                                holder.contacts_phone_s.setText(listData.get(i).getAttachePhone());
                            } else if (listData.get(i).getAttacheIdentity().equals("8")) {
                                holder.contacts_name_s.setText("负责经理:" + listData.get(i).getAttacheName());
                                holder.contacts_phone_s.setText(listData.get(i).getAttachePhone());
                            } else if (listData.get(i).getAttacheIdentity().equals("9")) {
                                holder.contacts_name_s.setText("负责总监:" + listData.get(i).getAttacheName());
                                holder.contacts_phone_s.setText(listData.get(i).getAttachePhone());
                            }
//                            if (FinalContents.getIdentity().equals("5")) {
//                                if(listData.get(i).getAttacheIdentity().equals("8")){
//                                    holder.contacts_name_s.setText("负责专员:" + listData.get(i).getAttacheName());
////                                    holder.contacts_phone_s.setText(listData.get(i).getAttachePhone());
//                                }else if(listData.get(i).getAttacheIdentity().equals("9")){
//                                    holder.contacts_name_s.setText("负责专员:" + listData.get(i).getAttacheName());
//                                    holder.contacts_phone_s.setText(listData.get(i).getAttachePhone());
//                                }
//                            } else if (FinalContents.getIdentity().equals("8")) {
//                                if(listData.get(i).getAttacheIdentity().equals("5")){
//
//                                }else if(listData.get(i).getAttacheIdentity().equals("8")){
//
//                                }else if(listData.get(i).getAttacheIdentity().equals("9")){
//
//                                }
//                                holder.contacts_name_s.setVisibility(View.VISIBLE);
//                                holder.contacts_phone_s.setVisibility(View.VISIBLE);
//                                holder.contacts_name_s.setText("负责专员:" + listData.get(i).getAttacheName());
//                                holder.contacts_phone_s.setText(listData.get(i).getAttachePhone());
//                            } else if (FinalContents.getIdentity().equals("9")) {
//                                holder.contacts_name_s.setVisibility(View.VISIBLE);
//                                holder.contacts_phone_s.setVisibility(View.VISIBLE);
//                                holder.contacts_name_s.setText("负责经理:" + listData.get(i).getAttacheName());
//                                holder.contacts_phone_s.setText(listData.get(i).getAttachePhone());
//                            }
                        }
                    }
                    holder.contacts_phone_s.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listData.get(FinID).getShopownerPhone().equals("")) {
                                ToastUtil.showToast(holder.itemView.getContext(), "暂无电话信息，无法拨打");
                            } else {
                                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + listData.get(FinID).getAttachePhone()));//跳转到拨号界面，同时传递电话号码
                                holder.itemView.getContext().startActivity(dialIntent);

                                FinalContents.setMyAddType("门店");

                            }
                        }
                    });
                    holder.contacts_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listData.get(FinID).getShopownerPhone().equals("")) {
                                ToastUtil.showToast(holder.itemView.getContext(), "暂无电话信息，无法拨打");
                            } else {
                                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + listData.get(FinID).getShopownerPhone()));//跳转到拨号界面，同时传递电话号码
                                holder.itemView.getContext().startActivity(dialIntent);

                                FinalContents.setMyAddType("门店");

                            }
                        }
                    });

                } else if (FinalContents.getStoreList().equals("2")) {
                    holder.tvName.setText(listData.get(i).getCompanyName());
                    holder.store_tv.setText("");
                    holder.store_id.setText("公司地址：" + listData.get(i).getCompanyAddress());
                    if(listData.get(i).getStoreNum().equals("")){
                        holder.store_num.setText("门店：0");
                    }else {
                        holder.store_num.setText("门店：" + listData.get(i).getStoreNum());
                    }


                    if (listData.get(i).getShopownerName().equals("")) {
                        holder.contacts_name.setVisibility(View.GONE);
                        holder.contacts_phone.setVisibility(View.GONE);
                        holder.item_stores_ll1.setVisibility(View.GONE);
                    } else {
                        holder.contacts_name.setVisibility(View.VISIBLE);
                        holder.contacts_phone.setVisibility(View.VISIBLE);
                        holder.item_stores_ll1.setVisibility(View.VISIBLE);
                        holder.contacts_name.setText("公司负责人：" + listData.get(i).getShopownerName());
                        holder.contacts_phone.setText(listData.get(i).getShopownerPhone());
                    }
//                    holder.contacts_name.setText("公司负责人：" + listData.get(i).getShopownerName() + " " + listData.get(i).getShopownerPhone());

                    holder.item_stores_ll2.setVisibility(View.GONE);
                    holder.contacts_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listData.get(FinID).getShopownerPhone().equals("")) {
                                ToastUtil.showToast(holder.itemView.getContext(), "暂无电话信息，无法拨打");
                            } else {
                                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + listData.get(FinID).getShopownerPhone()));//跳转到拨号界面，同时传递电话号码
                                holder.itemView.getContext().startActivity(dialIntent);

                                FinalContents.setMyAddType("公司");

                            }
                        }
                    });

                }
                holder.contacts_img.setVisibility(View.GONE);
                if (listData.get(i).getStatus().equals("0")) {
                    holder.contacts_img.setVisibility(View.VISIBLE);
                    Glide.with(holder.itemView.getContext()).load(R.mipmap.wwc).into(holder.contacts_img);
                } else if (listData.get(i).getStatus().equals("2")) {
                    holder.contacts_img.setVisibility(View.VISIBLE);
                    Glide.with(holder.itemView.getContext()).load(R.mipmap.wwc2).into(holder.contacts_img);
                } else if (listData.get(i).getStatus().equals("3")) {
                    holder.contacts_img.setVisibility(View.VISIBLE);
                    Glide.with(holder.itemView.getContext()).load(R.mipmap.wwc3).into(holder.contacts_img);
                } else if (listData.get(i).getStatus().equals("1")) {
                    if (FinalContents.getStoreList().equals("1")) {
                        if (listData.get(i).getState().equals("1")) {//签约
                            holder.contacts_img.setVisibility(View.VISIBLE);
                            Glide.with(holder.itemView.getContext()).load(R.mipmap.qianyue).into(holder.contacts_img);
                        } else if (listData.get(i).getState().equals("2")) {//装机
                            holder.contacts_img.setVisibility(View.VISIBLE);
                            Glide.with(holder.itemView.getContext()).load(R.mipmap.zhuangji).into(holder.contacts_img);
                        } else if (listData.get(i).getState().equals("3")) {//培训
                            holder.contacts_img.setVisibility(View.VISIBLE);
                            Glide.with(holder.itemView.getContext()).load(R.mipmap.peixun).into(holder.contacts_img);
                        } else if (listData.get(i).getState().equals("4")) {//维护
                            holder.contacts_img.setVisibility(View.VISIBLE);
                            Glide.with(holder.itemView.getContext()).load(R.mipmap.weihu).into(holder.contacts_img);
                        } else {
                            holder.contacts_img.setVisibility(View.GONE);
                        }
                    } else if (FinalContents.getStoreList().equals("2")) {
                        holder.contacts_img.setVisibility(View.GONE);
                    }
                }
            }

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FinalContents.getStoreList().equals("1")) {
                    Log.i("添加经纪人", "getStoreId()：" + listData.get(position).getStoreId());
                    itemOnClick.itemClick(listData.get(position).getStoreId() + "");
                } else if (FinalContents.getStoreList().equals("2")) {
                    Log.i("添加经纪人", "getCompanyId()：" + listData.get(position).getCompanyId());
                    itemOnClick.itemClick(listData.get(position).getCompanyId() + "");
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndex;
        TextView tvName;

        TextView store_tv;
        TextView contacts_name;
        TextView contacts_name_s;
        TextView contacts_phone;
        TextView contacts_phone_s;
        TextView store_id;
        TextView store_num;

        //        ImageView contacts_call;
        ImageView contacts_img;

        LinearLayout item_stores_ll1;
        LinearLayout item_stores_ll2;

        ContactsViewHolder(View itemView) {
            super(itemView);
            tvIndex = itemView.findViewById(R.id.tv_index_s);
            tvName = itemView.findViewById(R.id.tv_name_s);

            store_tv = itemView.findViewById(R.id.store_tv);
            store_id = itemView.findViewById(R.id.store_id);
            contacts_name_s = itemView.findViewById(R.id.store_name_s);
            contacts_name = itemView.findViewById(R.id.store_name);
            contacts_phone_s = itemView.findViewById(R.id.store_phone_s);
            contacts_phone = itemView.findViewById(R.id.store_phone);
            store_num = itemView.findViewById(R.id.store_num);

//            contacts_call = itemView.findViewById(R.id.store_call);
            contacts_img = itemView.findViewById(R.id.store_img);
            item_stores_ll1 = itemView.findViewById(R.id.item_stores_ll1);
            item_stores_ll2 = itemView.findViewById(R.id.item_stores_ll2);

        }
    }

    public interface ItemOnClick {
        void itemClick(String itemName);
    }

}
