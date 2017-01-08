package com.example.santiagoordonez.hackuoft.HackPage;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.example.santiagoordonez.hackuoft.HackPage.HackSwipeAdapter;
import com.example.santiagoordonez.hackuoft.R;
import com.example.santiagoordonez.hackuoft.R;
import com.example.santiagoordonez.hackuoft.dto.HackathonDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    public class ParseHackathons extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try{
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null){
                    buffer.append(line);

                }
                return buffer.toString();

            }catch(MalformedURLException e){
                e.printStackTrace();

            }catch(IOException e){
                e.printStackTrace();
            } finally {
                if (connection != null){
                    connection.disconnect();
                }
                try {
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //start json parsin.
        }
    }
}
