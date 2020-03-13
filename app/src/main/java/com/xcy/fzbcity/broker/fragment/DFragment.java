package com.xcy.fzbcity.broker.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.view.ReleaseActivity;


public class DFragment extends AllFragment implements View.OnClickListener {


    private ImageView d_fb;
    private LinearLayout d_all;
    private LinearLayout d_all_l;
    private LinearLayout d_my;
    private LinearLayout d_my_l;
    TotalFragment totalFragment = new TotalFragment();
    MineFragment mineFragment = new MineFragment();
    private FrameLayout d_fl;

    FragmentManager manager;
    FragmentTransaction transaction;

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
        d_all = getActivity().findViewById(R.id.d_all);
        d_all_l = getActivity().findViewById(R.id.d_all_l);
        d_my = getActivity().findViewById(R.id.d_my);
        d_my_l = getActivity().findViewById(R.id.d_my_l);

        d_fl = getActivity().findViewById(R.id.d_fl);

        d_fb.setOnClickListener(this);
        d_all.setOnClickListener(this);
        d_my.setOnClickListener(this);

        totalFragment = new TotalFragment();
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        FinalContents.setIsMyTotal("1");
        transaction.replace(R.id.d_fl, totalFragment);
        transaction.commit();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.d_fb:
                Intent intent = new Intent(getContext(), ReleaseActivity.class);
                startActivity(intent);
                break;
            case R.id.d_all:

                d_all_l.setVisibility(View.VISIBLE);
                d_my_l.setVisibility(View.INVISIBLE);
                FinalContents.setIsMyTotal("1");
                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.d_fl, totalFragment);
                transaction.commit();
                break;
            case R.id.d_my:
                d_all_l.setVisibility(View.INVISIBLE);
                d_my_l.setVisibility(View.VISIBLE);
                FinalContents.setIsMyTotal("2");
                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.d_fl, mineFragment);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                d_fb.setVisibility(View.VISIBLE);
            }else {
                d_fb.setVisibility(View.GONE);
            }
        }else {
            if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                d_fb.setVisibility(View.VISIBLE);
            }else {
                d_fb.setVisibility(View.GONE);
            }
        }
    }
}
