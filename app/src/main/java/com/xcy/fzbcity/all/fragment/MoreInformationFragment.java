package com.xcy.fzbcity.all.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.More_Information_Fragment1_Adapter;
import com.xcy.fzbcity.all.modle.MoreBean;
import com.xcy.fzbcity.all.persente.StatusBar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreInformationFragment extends AllFragment {
    private View view;
    private List<MoreBean.DataBean> list;
    private List<MoreBean.DataBean.ValueBeanX> array;

    private Context context;
    private ImageView all_no_information;
    private RecyclerView more_information_rv;

    public MoreInformationFragment(List<MoreBean.DataBean> list) {
        this.list = list;
    }

    public MoreInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());

        view = inflater.inflate(R.layout.fragment_more_information, container, false);
        context = container.getContext();
        initfvb();
        return view;
    }

    private void initfvb(){
        all_no_information = view.findViewById(R.id.all_no_information);
        more_information_rv = view.findViewById(R.id.more_information_Rv);

        for (int i = 0;i < list.size();i++){
            if (list.get(i).getKey().equals("基本信息")) {
                array = list.get(i).getValue();
                initView();
                all_no_information.setVisibility(View.GONE);
                more_information_rv.setVisibility(View.VISIBLE);
                Log.i("没有","有数据");
                return;
            }else {
                Log.i("没有","没有数据");
                all_no_information.setVisibility(View.VISIBLE);
                more_information_rv.setVisibility(View.GONE);
            }
        }

    }

    private void initView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        more_information_rv.setLayoutManager(linearLayoutManager);
        More_Information_Fragment1_Adapter more_information_fragment1_adapter = new More_Information_Fragment1_Adapter(array.get(0).getValue());
        more_information_rv.setAdapter(more_information_fragment1_adapter);
        more_information_fragment1_adapter.notifyDataSetChanged();
    }

}
