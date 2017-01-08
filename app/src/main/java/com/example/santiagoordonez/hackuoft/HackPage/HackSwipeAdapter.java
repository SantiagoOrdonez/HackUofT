package com.example.santiagoordonez.hackuoft.HackPage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

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

/**
 * Created by Ose on 1/7/2017.
 */

public class HackSwipeAdapter extends FragmentPagerAdapter {
    //TODO: This constructor will have to accept an array of HackathonEvent classes that it will iterate through as swipe objects.
    public HackSwipeAdapter(FragmentManager fm /*, Array<HackathonEvent>*/) {
        super(fm);
    }

    @Override
    //TODO: This method will give us the current HackathonEvent object using a position as it's index in the list.
    public Fragment getItem(int position) {
        HackPageFragment fragment = new HackPageFragment();

        //This will allow us to pass variables from the HackathonEvent Objects to the fragments
        Bundle bundle = new Bundle();

        //Right now we are passing a variable called "position" that gives the position of an object
        bundle.putInt("position", position);
        
        //add bundle as an argument
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    //TODO: This method will get the total number of elements in the HackathonEvent List.
    public int getCount() {

        // we will leave this as static for now
        return 5;
    }

    public class ParseHackathons extends AsyncTask<String,String,String> {


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
                String finalJSON = buffer.toString();
//                String months[] = {"January","February","March","April","May","June","July","August",
//                        "September","October","November","December"};
//                Date today = Calendar.getInstance().getTime();
//                for(int i = 0; i < months.length;i++){
//                    if (months[i].startsWith(today.toString().substring(4,6))){
//                        String currentMonth = months[i];
//                        int index = i;
//                        break;
//                    }
//                }
                JSONObject parentObject = new JSONObject(finalJSON);
                JSONArray parentArray = parentObject.getJSONArray("January");
                ArrayList<HackathonDTO> hackathons = new  ArrayList<HackathonDTO>();
                for (int i = 0; i < parentArray.length();i++){
                    HackathonDTO hackathonDTO = new HackathonDTO();
                    JSONObject jsonObject =  parentArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    hackathonDTO.setTitle(title);
                    String urlobj = jsonObject.getString("url");
                    hackathonDTO.setUrl(urlobj);
                    String startDate = jsonObject.getString("startDate");
                    hackathonDTO.setStartDate(startDate);
                    String endDate = jsonObject.getString("endDate");
                    hackathonDTO.setEndDate(endDate);
                    String year = jsonObject.getString("year");
                    hackathonDTO.setYear(year);
                    String city = jsonObject.getString("city");
                    hackathonDTO.setCity(city);
                    String host = jsonObject.getString("host");
                    hackathonDTO.setHost(host);
                    String length = jsonObject.getString("length");
                    hackathonDTO.setLength(length);
                    String size = jsonObject.getString("size");
                    hackathonDTO.setSize(size);
                    String travel = jsonObject.getString("travel");
                    hackathonDTO.setTravel(travel);
                    String prize = jsonObject.getString("prize");
                    hackathonDTO.setPrize(prize);
                    String highSchoolers = jsonObject.getString("highSchoolers");
                    hackathonDTO.setHighSchoolers(highSchoolers);
                    String cost = jsonObject.getString("cost");
                    hackathonDTO.setCost(cost);
                    String facebookURL = jsonObject.getString("facebookURL");
                    hackathonDTO.setFacebookURL(facebookURL);
                    String twitterURL = jsonObject.getString("twitterURL");
                    hackathonDTO.setTwitterURL(twitterURL);
                    String notes = jsonObject.getString("notes");
                    hackathonDTO.setNotes(notes);
                    hackathons.add(hackathonDTO);
                }
                return buffer.toString();
            }catch(MalformedURLException e){
                e.printStackTrace();

            }catch(IOException e){
                e.printStackTrace();
            }catch(JSONException e){
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
            //start json parsi
        }
    }

}
