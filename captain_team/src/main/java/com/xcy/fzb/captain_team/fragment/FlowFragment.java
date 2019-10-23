package com.xcy.fzb.captain_team.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.persente.StatusBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlowFragment extends Fragment {


    public FlowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_flow, container, false);
    }
}
