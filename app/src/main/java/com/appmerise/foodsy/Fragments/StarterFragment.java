package com.appmerise.foodsy.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appmerise.foodsy.R;


public class StarterFragment extends Fragment {

    ListView lv_starter;

    public StarterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_starter2, container, false);
        lv_starter = v.findViewById(R.id.lv_starter);

        // Inflate the layout for this fragment
        return v;
    }


}
