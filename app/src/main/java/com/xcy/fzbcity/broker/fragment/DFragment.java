package com.xcy.fzbcity.broker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.AllFragment;
import com.xcy.fzbcity.all.fragment.DynamicFragment;
import com.xcy.fzbcity.all.fragment.GoodNewsFragment;
import com.xcy.fzbcity.all.fragment.GuestRoomFragment;
import com.xcy.fzbcity.all.fragment.NoticeFragment;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.view.ReleaseActivity;


public class DFragment extends AllFragment implements View.OnClickListener {


    private ImageView d_fb;
    //    private LinearLayout d_all;
//    private LinearLayout d_all_l;
//    private LinearLayout d_my;
//    private LinearLayout d_my_l;
    private LinearLayout new_ll_1;
    private LinearLayout new_ll_2;
    private LinearLayout new_ll_3;
    private LinearLayout new_ll_4;
    private LinearLayout new_ll_5;
    private LinearLayout new_ll_6;
    private LinearLayout new_ll_7;
    private LinearLayout new_ll_8;
    private LinearLayout new_ll_9;
    private LinearLayout new_ll_10;
    MineFragment mineFragment = new MineFragment();

    TotalFragment totalFragment = new TotalFragment();
    DynamicFragment dynamicFragment = new DynamicFragment();
    NoticeFragment noticeFragment = new NoticeFragment();
    GuestRoomFragment guestRoomFragment = new GuestRoomFragment();
    GoodNewsFragment goodNewsFragment = new GoodNewsFragment();
    private FrameLayout d_fl;

    FragmentManager manager;
    FragmentTransaction transaction;

    private String type = "1";

    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.broker_modulebroker_fragment_economics, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        d_fb = getActivity().findViewById(R.id.d_fb);
//        d_all = getActivity().findViewById(R.id.d_all);
//        d_all_l = getActivity().findViewById(R.id.d_all_l);
//        d_my = getActivity().findViewById(R.id.d_my);
//        d_my_l = getActivity().findViewById(R.id.d_my_l);
        new_ll_1 = getActivity().findViewById(R.id.new_ll_1);
        new_ll_2 = getActivity().findViewById(R.id.new_ll_2);
        new_ll_3 = getActivity().findViewById(R.id.new_ll_3);
        new_ll_4 = getActivity().findViewById(R.id.new_ll_4);
        new_ll_5 = getActivity().findViewById(R.id.new_ll_5);
        new_ll_6 = getActivity().findViewById(R.id.new_ll_6);
        new_ll_7 = getActivity().findViewById(R.id.new_ll_7);
        new_ll_8 = getActivity().findViewById(R.id.new_ll_8);
        new_ll_9 = getActivity().findViewById(R.id.new_ll_9);
        new_ll_10 = getActivity().findViewById(R.id.new_ll_10);
        d_fl = getActivity().findViewById(R.id.d_fl);

        new_ll_1.setOnClickListener(this);
        new_ll_3.setOnClickListener(this);
        new_ll_5.setOnClickListener(this);
        new_ll_7.setOnClickListener(this);
        new_ll_9.setOnClickListener(this);
        d_fb.setOnClickListener(this);
//        d_all.setOnClickListener(this);
//        d_my.setOnClickListener(this);

//        totalFragment = new TotalFragment();
//        manager = getFragmentManager();
//        transaction = manager.beginTransaction();
//        FinalContents.setIsMyTotal("1");
//        transaction.replace(R.id.d_fl, dynamicFragment);
//        transaction.commit();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.d_fb:
                Intent intent = new Intent(getContext(), ReleaseActivity.class);
                startActivity(intent);
                break;
            case R.id.new_ll_1:
                new_ll_2.setVisibility(View.VISIBLE);
                new_ll_4.setVisibility(View.GONE);
                new_ll_6.setVisibility(View.GONE);
                new_ll_8.setVisibility(View.GONE);
                new_ll_10.setVisibility(View.GONE);

                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.d_fl, dynamicFragment);
                transaction.commit();
                break;
            case R.id.new_ll_3:
                new_ll_2.setVisibility(View.GONE);
                new_ll_4.setVisibility(View.VISIBLE);
                new_ll_6.setVisibility(View.GONE);
                new_ll_8.setVisibility(View.GONE);
                new_ll_10.setVisibility(View.GONE);

                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.d_fl, totalFragment);
                transaction.commit();
                break;
            case R.id.new_ll_5:
                new_ll_2.setVisibility(View.GONE);
                new_ll_4.setVisibility(View.GONE);
                new_ll_6.setVisibility(View.VISIBLE);
                new_ll_8.setVisibility(View.GONE);
                new_ll_10.setVisibility(View.GONE);

                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.d_fl, noticeFragment);
                transaction.commit();
                break;
            case R.id.new_ll_7:
                new_ll_2.setVisibility(View.GONE);
                new_ll_4.setVisibility(View.GONE);
                new_ll_6.setVisibility(View.GONE);
                new_ll_8.setVisibility(View.VISIBLE);
                new_ll_10.setVisibility(View.GONE);

                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.d_fl, guestRoomFragment);
                transaction.commit();
                break;
            case R.id.new_ll_9:
                new_ll_2.setVisibility(View.GONE);
                new_ll_4.setVisibility(View.GONE);
                new_ll_6.setVisibility(View.GONE);
                new_ll_8.setVisibility(View.GONE);
                new_ll_10.setVisibility(View.VISIBLE);

                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.d_fl, goodNewsFragment);
                transaction.commit();
                break;
//            case R.id.d_all:
//
//                d_all_l.setVisibility(View.VISIBLE);
//                d_my_l.setVisibility(View.INVISIBLE);
//                FinalContents.setIsMyTotal("1");
//                manager = getFragmentManager();
//                transaction = manager.beginTransaction();
//                transaction.replace(R.id.d_fl, totalFragment);
//                transaction.commit();
//                break;
//            case R.id.d_my:
//                d_all_l.setVisibility(View.INVISIBLE);
//                d_my_l.setVisibility(View.VISIBLE);
//                FinalContents.setIsMyTotal("2");
//                manager = getFragmentManager();
//                transaction = manager.beginTransaction();
//                transaction.replace(R.id.d_fl, mineFragment);
//                transaction.commit();
//                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (FinalContents.isHidden()) {             //TODO 非消息内部
            if(hidden){
                type = "1";
                //TODO now visible to user 不显示fragment
            } else {
                onResume();
                //TODO now invisible to user 显示fragment
            }
        }else {                                     //TODO  消息内部
            if(hidden){
                //TODO now visible to user 不显示fragment
            } else {
                onResume();
                //TODO now invisible to user 显示fragment
            }
        }
        if (hidden) {
            if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                d_fb.setVisibility(View.VISIBLE);
            } else {
                d_fb.setVisibility(View.GONE);
            }
        } else {
            if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                d_fb.setVisibility(View.VISIBLE);
            } else {
                d_fb.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("文字轮播点击", "type：" + type);
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        if (FinalContents.isLuo()) {
            FinalContents.setLuo(false);
        }
        if (type.equals("1")) {
            new_ll_2.setVisibility(View.VISIBLE);
            new_ll_4.setVisibility(View.GONE);
            new_ll_6.setVisibility(View.GONE);
            new_ll_8.setVisibility(View.GONE);
            new_ll_10.setVisibility(View.GONE);

            manager = getFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.d_fl, dynamicFragment);
            transaction.commit();
        } else if (type.equals("2")) {
            new_ll_2.setVisibility(View.GONE);
            new_ll_4.setVisibility(View.GONE);
            new_ll_6.setVisibility(View.VISIBLE);
            new_ll_8.setVisibility(View.GONE);
            new_ll_10.setVisibility(View.GONE);

            manager = getFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.d_fl, noticeFragment);
            transaction.commit();
        } else if (type.equals("3")) {
            new_ll_2.setVisibility(View.GONE);
            new_ll_4.setVisibility(View.GONE);
            new_ll_6.setVisibility(View.GONE);
            new_ll_8.setVisibility(View.VISIBLE);
            new_ll_10.setVisibility(View.GONE);

            manager = getFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.d_fl, guestRoomFragment);
            transaction.commit();
        } else if (type.equals("4")) {
            new_ll_2.setVisibility(View.GONE);
            new_ll_4.setVisibility(View.GONE);
            new_ll_6.setVisibility(View.GONE);
            new_ll_8.setVisibility(View.GONE);
            new_ll_10.setVisibility(View.VISIBLE);

            manager = getFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.d_fl, goodNewsFragment);
            transaction.commit();
        }
    }

}
