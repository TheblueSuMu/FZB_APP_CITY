package com.xcy.fzbcity.all.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.xcy.fzbcity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSojournFragment extends Fragment {


    public SearchSojournFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_sojourn, container, false);
    }

}
