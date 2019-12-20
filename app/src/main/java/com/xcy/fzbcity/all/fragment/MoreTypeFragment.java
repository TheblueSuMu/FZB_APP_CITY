package com.xcy.fzbcity.all.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.MoreTypeAdapter;
import com.xcy.fzbcity.all.modle.MoreBean;
import com.xcy.fzbcity.all.persente.StatusBar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreTypeFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<MoreBean.DataBean> list;
    private List<MoreBean.DataBean.ValueBeanX> array;
    private View view;
    private ImageView all_no_information;

    public MoreTypeFragment(List<MoreBean.DataBean> list) {
        this.list = list;
    }

    public MoreTypeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        view  = inflater.inflate(R.layout.fragment_more_type, container, false);
        recyclerView = view.findViewById(R.id.more_type_rv);
        all_no_information = view.findViewById(R.id.all_no_information);
        init();
        return view;
    }

    private void init(){
        for (int i = 0;i < list.size();i++){
            if (list.get(i).getKey().equals("产品类型")) {
                array = list.get(i).getValue();
                all_no_information.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                MoreTypeAdapter recyclerAdapter = new MoreTypeAdapter(array);
                recyclerView.setAdapter(recyclerAdapter);
                return ;
            }else {
                all_no_information.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    }


}
