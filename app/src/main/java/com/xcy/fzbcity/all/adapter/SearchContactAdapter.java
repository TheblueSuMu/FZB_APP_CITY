package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ContactBean;

import java.util.List;


public class SearchContactAdapter extends ArrayAdapter<ContactBean> {

	private int resource;

	public SearchContactAdapter(Context context, int resource,
								List<ContactBean> objects) {
		super(context, resource, objects);
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactBean contact = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(resource,
					null);
		}
		TextView tv_contact_name = (TextView) convertView
				.findViewById(R.id.tv_contact_name);
		tv_contact_name.setText(contact.getName());
		return convertView;
	}

}
