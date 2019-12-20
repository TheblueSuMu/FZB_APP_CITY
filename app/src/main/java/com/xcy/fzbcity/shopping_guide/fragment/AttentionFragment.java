package com.xcy.fzbcity.shopping_guide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.fragment.AllFragment;
import com.xcy.fzbcity.all.modle.TaskDetailsBean;

public class AttentionFragment extends AllFragment {

    private View view;
    private WebView attention_content;
    private TaskDetailsBean taskDetailsBean;

    public AttentionFragment(TaskDetailsBean taskDetailsBean) {
        this.taskDetailsBean = taskDetailsBean;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_guide_fragment_attention, null);
        attention_content = view.findViewById(R.id.attention_content);
        initData();
        return view;
    }

    private void initData(){
        attention_content.loadDataWithBaseURL(null, "<head><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no'> <style>img{max-width: 100%; width:auto; height:auto;}</style></head>"+taskDetailsBean.getData().getRouteTimeInfo().getMatter().getMatterComment(), "text/html", "utf-8", null);
    }

}