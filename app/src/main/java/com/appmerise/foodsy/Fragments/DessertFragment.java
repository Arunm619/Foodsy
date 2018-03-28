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


public class DessertFragment extends Fragment {

    ListView lv_desserts;

    public DessertFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dessert, container, false);

        lv_desserts = v.findViewById(R.id.lv_dessert);



        return v;
    }


}
