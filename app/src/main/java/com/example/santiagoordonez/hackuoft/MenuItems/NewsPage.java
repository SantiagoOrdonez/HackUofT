package com.example.santiagoordonez.hackuoft.MenuItems;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.santiagoordonez.hackuoft.R;

import com.example.santiagoordonez.hackuoft.R;

/**
 * Created by jayrebgz on 2017-01-07.
 */

public class NewsPage extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.news_page_layout, container, false);
    }
}
