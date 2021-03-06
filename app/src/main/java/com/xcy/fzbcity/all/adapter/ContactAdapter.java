package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ContactBean;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<ContactBean> {

	private SectionIndexer mIndexer;
	private int resource;

	public ContactAdapter(Context context, int resource, List<ContactBean> objects, SectionIndexer mIndexer) {
		super(context, resource, objects);
		this.resource = resource;
		this.mIndexer = mIndexer;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactBean contact = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(resource,
					null);
		}
		TextView tv_index = (TextView) convertView.findViewById(R.id.tv_index);
		int sectionIndex = mIndexer.getSectionForPosition(position);
		int positionIndex = mIndexer.getPositionForSection(sectionIndex);

		if (position == positionIndex) {
			tv_index.setVisibility(View.VISIBLE);
			tv_index.setText(contact.getPhonebookLabel());
		} else {
			tv_index.setVisibility(View.GONE);
		}

		TextView tv_contact_name = (TextView) convertView
				.findViewById(R.id.tv_contact_name);
		tv_contact_name.setText(contact.getName());
		return convertView;
	}

}
