package com.example.santiagoordonez.hackuoft;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.santiagoordonez.hackuoft.MenuItems.PagerAdapter;
import com.example.santiagoordonez.hackuoft.dto.HackathonDTO;

import java.util.ArrayList;

public class MenuActivity extends FragmentActivity {
    PagerAdapter pAdapter;
    ViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        viewPager = (ViewPager) findViewById(R.id.pager);

        pAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pAdapter);

    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }



}
