package com.xcy.fzbcity.all.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
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
public class MoreProjectFragment extends Fragment {

    private List<MoreBean.DataBean> list;
    private ImageView all_no_information;
    private RecyclerView more_project_fragment_rv;
    private List<MoreBean.DataBean.ValueBeanX> array;


    public MoreProjectFragment(List<MoreBean.DataBean> list) {
        this.list = list;
    }

    public MoreProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_more_project, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        all_no_information = getActivity().findViewById(R.id.all_no_information);
        more_project_fragment_rv = getActivity().findViewById(R.id.more_project_fragment_Rv);

        for (int i = 0;i < list.size();i++){
            if (list.get(i).getKey().equals("项目规划")) {
                array = list.get(i).getValue();
                all_no_information.setVisibility(View.GONE);
                more_project_fragment_rv.setVisibility(View.VISIBLE);
                initView();
            }else {
                all_no_information.setVisibility(View.VISIBLE);
                more_project_fragment_rv.setVisibility(View.GONE);
            }
        }



    }

    private void initView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        more_project_fragment_rv.setLayoutManager(linearLayoutManager);
        More_Information_Fragment1_Adapter more_information_fragment1_adapter = new More_Information_Fragment1_Adapter(array.get(0).getValue());
        more_project_fragment_rv.setAdapter(more_information_fragment1_adapter);
        more_information_fragment1_adapter.notifyDataSetChanged();
    }
}
