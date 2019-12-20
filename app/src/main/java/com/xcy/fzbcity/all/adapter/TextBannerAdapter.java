package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stx.xmarqueeview.XMarqueeView;
import com.stx.xmarqueeview.XMarqueeViewAdapter;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.Bean;

import java.util.List;

public class TextBannerAdapter extends XMarqueeViewAdapter<Bean> {
    private Context mContext;
    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    public TextBannerAdapter(List<Bean> datas, Context context) {
        super(datas);
        mContext = context;
    }

    @Override
    public View onCreateView(XMarqueeView parent) {
        //跑马灯单个显示条目布局，自定义
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_marqueeview, null);
    }

    @Override
    public void onBindView(View parent, View view, final int position) {
        //布局内容填充
        TextView text = (TextView) view.findViewById(R.id.textbanner);
        ImageView image =  view.findViewById(R.id.imagebanner);
        text.setText(mDatas.get(position).getName());
        image.setImageResource(mDatas.get(position).getPic());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLisenter != null){
                    onItemClickLisenter.onItemClick(position);
                }
            }
        });
    }
}