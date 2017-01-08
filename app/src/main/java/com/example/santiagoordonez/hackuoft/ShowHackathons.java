package com.example.santiagoordonez.hackuoft;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowHackathons extends FragmentActivity {

    // initialize a new view pager object
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hackathons);

        //get the view pager object from the view
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //add swipe adapter
        HackSwipeAdapter hackSwipeAdapter = new HackSwipeAdapter(getSupportFragmentManager());

        //set adapter for viewPager
        viewPager.setAdapter(hackSwipeAdapter);
    }
}
