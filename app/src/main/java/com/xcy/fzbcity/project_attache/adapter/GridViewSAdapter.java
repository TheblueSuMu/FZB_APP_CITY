package com.xcy.fzbcity.project_attache.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;

import java.util.List;

public class GridViewSAdapter extends BaseAdapter {

    public static final String POSITION = "POSITION";
    public static final int DELETE = 1;

    private Context context;
    private List<Object> list;
    LayoutInflater layoutInflater;
    private ImageView mImageView;
    private ImageView item_S;

    private ItemClieck itemClieck;

    private Handler handler;

    public GridViewSAdapter(Context context, List<Object> list, Handler handler) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        this.handler = handler;
    }

    public interface ItemClieck {
        public void ItemS(int position);
    }

    public void setItemClieck(ItemClieck itemClieck) {
        this.itemClieck = itemClieck;
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
            item_S.setVisibility(View.VISIBLE);
            Glide.with(context).load(FinalContents.getImageUrl() + list.get(position)).into(mImageView);
//            mImageView.setBackgroundResource(list.get(position));
        } else {
            item_S.setVisibility(View.GONE);
            mImageView.setBackgroundResource(R.mipmap.jiatu);//最后一个显示加号图片
        }

//        item_S.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("MyCL", "position：" + position);
//                itemClieck.ItemS(position);
//            }
//        });

        item_S.setOnClickListener(new OnItemChildClickListener(DELETE, position));

        return convertView;
    }

    // handler的点击事件
    private class OnItemChildClickListener implements View.OnClickListener {
        // 点击类型索引，对应前面的CLICK_INDEX
        private int clickIndex;
        // 点击列表位置
        private int position;

        public OnItemChildClickListener(int clickIndex, int position) {
            this.clickIndex = clickIndex;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // 创建Message并填充数据，通过handle联系Activity接收处理
            Message msg = new Message();
            msg.what = clickIndex;
            msg.arg1 = position;
            Bundle b = new Bundle();
            b.putInt(POSITION, position);
            msg.setData(b);
            handler.sendMessage(msg);
        }

    }
}
