package com.example.santiagoordonez.hackuoft.EventPage;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.santiagoordonez.hackuoft.HackPage.HackSwipeAdapter;
import com.example.santiagoordonez.hackuoft.R;

public class EventsPage extends FragmentActivity {

    // initialize a new view pager object
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_page);

        //get the view pager object from the view
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //add swipe adapter
        EventSwipeAdapter eventSwipeAdapter = new EventSwipeAdapter (getSupportFragmentManager());

        //set adapter for viewPager
        viewPager.setAdapter(eventSwipeAdapter);
    }
}
