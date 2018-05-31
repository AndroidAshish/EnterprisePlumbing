package com.example.smart.enterpriseplumbing.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.smart.enterpriseplumbing.R;

/**
 * Created by Smart on 3/19/2018.
 */

public class SportsFragment  extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.under_devleopment, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //  adapter = new BrandNewAdapter(getActivity(),data);
        //  recyclerView.setAdapter(adapter);


        return v;
    }

}
