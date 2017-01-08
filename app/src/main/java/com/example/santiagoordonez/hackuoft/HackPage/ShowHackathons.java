package com.example.santiagoordonez.hackuoft.HackPage;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.example.santiagoordonez.hackuoft.HackPage.HackSwipeAdapter;
import com.example.santiagoordonez.hackuoft.R;
import com.example.santiagoordonez.hackuoft.R;
import com.example.santiagoordonez.hackuoft.dto.HackathonDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
