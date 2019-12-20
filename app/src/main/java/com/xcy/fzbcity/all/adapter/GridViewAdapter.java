package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    public static final String POSITION = "POSITION";
    public static final int DELETE = 1;

    private Context context;
    private List<Object> list;
    LayoutInflater layoutInflater;
    private ImageView mImageView;
    private ImageView item_S;

    public GridViewAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return list.size() + 1;//注意此处
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.grid_item, parent, false);
        mImageView = (ImageView) convertView.findViewById(R.id.item);
        item_S = (ImageView) convertView.findViewById(R.id.item_S);
        if (position < list.size()) {
            item_S.setVisibility(View.GONE);
            Glide.with(context).load(FinalContents.getImageUrl() + list.get(position)).into(mImageView);
//            mImageView.setBackgroundResource(list.get(position));
        } else {
            item_S.setVisibility(View.GONE);
            mImageView.setBackgroundResource(R.mipmap.jiatu);//最后一个显示加号图片
        }

        return convertView;
    }


}
