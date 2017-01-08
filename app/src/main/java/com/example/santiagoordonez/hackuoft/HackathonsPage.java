package com.example.santiagoordonez.hackuoft;

import android.os.Bundle;
import android.widget.ImageView;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by jayrebgz on 2017-01-07.
 */

public class HackathonsPage extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.hackathons_page_layout, container, false);
    }


}
