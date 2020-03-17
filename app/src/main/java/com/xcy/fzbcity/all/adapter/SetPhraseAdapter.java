package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.SetPhraseBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SetPhraseAdapter extends RecyclerView.Adapter<SetPhraseAdapter.SetPhraseViewHolder> {

    List<SetPhraseBean.DataBean.CommonLanguageListBean> commonLanguageList;

    onBianji onBianji;

    onShanchu onShanchu;

    public void setOnShanchu(SetPhraseAdapter.onShanchu onShanchu) {
        this.onShanchu = onShanchu;
    }

    public void setOnBianji(SetPhraseAdapter.onBianji onBianji) {
        this.onBianji = onBianji;
    }

    public void setCommonLanguageList(List<SetPhraseBean.DataBean.CommonLanguageListBean> commonLanguageList) {
        this.commonLanguageList = commonLanguageList;
    }

    @NonNull
    @Override
    public SetPhraseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_phrase_item, parent, false);
        return new SetPhraseViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SetPhraseViewHolder holder, int position) {

        holder.set_phrase_item_text.setText(commonLanguageList.get(position).getContent());

        if (commonLanguageList.get(position).getState().equals("1")) {
            holder.set_phrase_item_bianji.setVisibility(View.GONE);
            holder.set_phrase_item_shanchu.setVisibility(View.GONE);
        } else if (commonLanguageList.get(position).getState().equals("2")) {
            holder.set_phrase_item_bianji.setVisibility(View.VISIBLE);
            holder.set_phrase_item_shanchu.setVisibility(View.VISIBLE);
        }

        holder.set_phrase_item_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBianji.onBianji(position);
            }
        });
        holder.set_phrase_item_shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShanchu.onShanchu(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return commonLanguageList.size();
    }

    class SetPhraseViewHolder extends RecyclerView.ViewHolder {

        TextView set_phrase_item_text;
        ImageView set_phrase_item_bianji;
        ImageView set_phrase_item_shanchu;

        public SetPhraseViewHolder(@NonNull View itemView) {
            super(itemView);

            set_phrase_item_text = itemView.findViewById(R.id.set_phrase_item_text);
            set_phrase_item_bianji = itemView.findViewById(R.id.set_phrase_item_bianji);
            set_phrase_item_shanchu = itemView.findViewById(R.id.set_phrase_item_shanchu);

        }
    }

    //TODO 接口
    public interface onBianji {
        void onBianji(int position);
    }

    //    //TODO 接口
    public interface onShanchu {
        void onShanchu(int position);
    }
}
