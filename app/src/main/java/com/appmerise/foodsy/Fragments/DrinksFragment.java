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


public class DrinksFragment extends Fragment {

    ListView lv_drinks;

    public DrinksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_drinks, container, false);
        lv_drinks = v.findViewById(R.id.lvDrinks);

        return v;
    }

}
