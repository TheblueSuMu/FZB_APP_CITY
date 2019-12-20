package com.xcy.fzbcity.captain_team.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    List<ContactModel> contacts = new ArrayList<>();
    private ContactModel contact;
    ItemOnClick itemOnClick;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public void setContacts(List<ContactModel> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }


    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layaout_item_contacts, parent,false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, final int position) {

        Log.i("MyCL", "集合长度：" + contacts.size());
        contact = contacts.get(position);
        Log.e("MyCL", "onBindViewHolder: index:" + contact.getIndex());
        if (position == 0 || !contacts.get(position - 1).getIndex().equals(contact.getIndex())) {
            holder.tvIndex.setVisibility(View.VISIBLE);
            holder.tvIndex.setText(contact.getIndex());
        } else {
            holder.tvIndex.setVisibility(View.GONE);
        }

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append = stringBuffer.append(contact.getName());
        for (int i = 0; i < append.length(); ++i) {
            if (append.substring(i, i + 1).equals("@")) {
                holder.tvName.setText(append.substring(0, i));
//                Log.i("客户","name：" + append.substring(0, i));
//                Log.i("客户","id：" + append.substring(i + 1));
            }
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("团队长", "contacts.get(position).getName()：" + contacts.get(position).getName());
                itemOnClick.itemClick(contacts.get(position).getName());
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

        ContactsViewHolder(View itemView) {
            super(itemView);
            tvIndex = itemView.findViewById(R.id.tv_index_s);
            tvName = itemView.findViewById(R.id.tv_name_s);
        }
    }

    public interface ItemOnClick {
        void itemClick(String itemName);
    }

}
