package com.xcy.fzbcity.all.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.persente.Fragnemt_SS;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment4 extends Fragment {

    TextView textView;
    private String name;

    public MyFragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_fragment4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        EventBus.getDefault().register(this);

        textView = getActivity().findViewById(R.id.fragment4_tv_name);
        name = NewlyIncreased.getLeaderNum();
        textView.setText(name);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(Fragnemt_SS fragnemt_ss) {
        name = fragnemt_ss.getName3();
        Log.i("MyCL", "廣播");

        textView.setText(name);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }

}
