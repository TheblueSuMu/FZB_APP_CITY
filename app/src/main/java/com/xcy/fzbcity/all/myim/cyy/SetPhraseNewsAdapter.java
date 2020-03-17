package com.xcy.fzbcity.all.myim.cyy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.SetPhraseBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class SetPhraseNewsAdapter extends RecyclerView.Adapter<SetPhraseNewsAdapter.SetPhraseViewHolder> {

    List<SetPhraseBean.DataBean.CommonLanguageListBean> commonLanguageList;

    onMyItemClick onMyItemClick;


    public void setOnMyItemClick(SetPhraseNewsAdapter.onMyItemClick onMyItemClick) {
        this.onMyItemClick = onMyItemClick;
    }

    public void setCommonLanguageList(List<SetPhraseBean.DataBean.CommonLanguageListBean> commonLanguageList) {
        this.commonLanguageList = commonLanguageList;
    }

    @NonNull
    @Override
    public SetPhraseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_phrase_news_item, parent, false);
        return new SetPhraseViewHolder(inflate);
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SetPhraseViewHolder holder, int position) {

        holder.set_phrase_item_text.setText(commonLanguageList.get(position).getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyItemClick.itemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return commonLanguageList.size();
//        return 0;
    }

    class SetPhraseViewHolder extends RecyclerView.ViewHolder{

        TextView set_phrase_item_text;

        public SetPhraseViewHolder(@NonNull View itemView) {
            super(itemView);

            set_phrase_item_text = itemView.findViewById(R.id.set_phrase_item_text);

        }
    }

    //TODO 接口
    public interface onMyItemClick {
        void itemClick(int position);
    }

}
