package com.xcy.fzbcity.project_attache.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.BrokersListData;
import com.xcy.fzbcity.all.persente.ContactModel;
import com.xcy.fzbcity.project_attache.view.BrokerActivity;

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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    List<ContactModel> contacts = new ArrayList<>();
    private ContactModel contact;
    ItemOnClick itemOnClick;
    List<BrokersListData> listData;

    public void setListData(List<BrokersListData> listData) {
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
        View view = inflater.inflate(R.layout.project_attache_layaout_item_contacts, null);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactsViewHolder holder, final int position) {

//        Log.i("MyCL", "集合长度：" + contacts.size());
        contact = contacts.get(position);
//        Log.e("MyCL", "onBindViewHolder: index:" + contact.getIndex());
        if (position == 0 || !contacts.get(position - 1).getIndex().equals(contact.getIndex())) {
            holder.tvIndex.setVisibility(View.VISIBLE);
            holder.tvIndex.setText(contact.getIndex());
        } else {
            holder.tvIndex.setVisibility(View.GONE);
        }

        for (int i = 0; i < listData.size(); ++i) {
            if (contact.getName().equals((listData.get(i).getAgentName() + "@" + listData.get(i).getAgentId()))) {
                StringBuffer stringBuffer = new StringBuffer();
                StringBuffer append = stringBuffer.append(contact.getName());
                for (int j = 0; j < append.length(); ++j) {
                    if (stringBuffer.substring(j, j + 1).equals("@")) {
                        holder.tvName.setText(append.substring(0, j));
                        holder.contacts_phone.setText(listData.get(i).getAgentPhone());
                        if (listData.get(i).getStoreName().equals("")) {
                            holder.contacts_name.setText(listData.get(i).getCompanyName());
                        } else {
                            holder.contacts_name.setText(listData.get(i).getCompanyName() + "-" + listData.get(i).getStoreName());
                        }
                        if (listData.get(i).getStatus().equals("1") || listData.get(i).getStatus().equals("0")) {
                            holder.contacts_img.setVisibility(View.GONE);
                        } else {
                            holder.contacts_img.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
        }
        holder.contacts_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < listData.size(); ++i) {
                    if ((listData.get(i).getAgentName() + "@" + listData.get(i).getAgentId()).equals(contacts.get(position).getName())) {
                        if (listData.get(position).getAgentPhone().equals("")) {
                            Toast.makeText(holder.itemView.getContext(), "暂无电话信息，无法拨打", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + listData.get(i).getAgentPhone()));//跳转到拨号界面，同时传递电话号码
                            holder.itemView.getContext().startActivity(dialIntent);
                        }
                    }
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < listData.size(); ++i) {
                    if ((listData.get(i).getAgentName() + "@" + listData.get(i).getAgentId()).equals(contacts.get(position).getName())) {
                        Intent intent = new Intent(holder.itemView.getContext(), BrokerActivity.class);
                        FinalContents.setAgentId(listData.get(i).getAgentId());
                        holder.itemView.getContext().startActivity(intent);
                    }
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

        TextView contacts_phone;
        TextView contacts_name;

        ImageView contacts_call;
        ImageView contacts_img;

        ContactsViewHolder(View itemView) {
            super(itemView);
            tvIndex = itemView.findViewById(R.id.tv_index_s);
            tvName = itemView.findViewById(R.id.tv_name_s);

            contacts_phone = itemView.findViewById(R.id.contacts_phone);
            contacts_name = itemView.findViewById(R.id.contacts_name);
            contacts_call = itemView.findViewById(R.id.contacts_call);
            contacts_img = itemView.findViewById(R.id.contacts_img);

        }
    }

    public interface ItemOnClick {
        void itemClick(String itemName);
    }

}
