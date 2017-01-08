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

public class HackathonsPage extends FragmentActivity {

    // initialize a new view pager object
    ViewPager viewPager;
    HackathonDTO hackathonDTO;
    ArrayList<HackathonDTO> allHackathons;
    int counter = 0;
    int counter2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hackathons);
        new ParseHackathons().execute("http://www.hackalist.org/api/1.0/2017/01.json");
    }

    public class ParseHackathons extends AsyncTask<String,String,ArrayList<HackathonDTO>>{
        @Override
        protected ArrayList<HackathonDTO> doInBackground(String... params) {
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
                allHackathons = new  ArrayList<HackathonDTO>();
                for (int i = 0; i < parentArray.length();i++){
                    hackathonDTO = new HackathonDTO();
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
                    allHackathons.add(hackathonDTO);
                }
                return allHackathons;
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
        protected void onPostExecute(ArrayList<HackathonDTO> hacks) {
            super.onPostExecute(hacks);
            for(HackathonDTO hack: hacks){
                ParsePicture parsePicture = new ParsePicture(hack);
                if(!hack.getFacebookURL().equals("")){
                    counter2++;
                    parsePicture.execute("https://graph.facebook.com/"+ hack.getFacebookURL() +"" +
                            "?fields=picture.width(640)&access_token=1278772608884108%7CNyvDdjr45c-jtXgQyRG0rkiTq2s");
                }


            }
        }
    }

    public class ParsePicture extends AsyncTask<String,String,String>{
        HackathonDTO hackathon;
        public ParsePicture(HackathonDTO hack){
            hackathon = hack;
        }

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
                JSONObject parentObject = new JSONObject(finalJSON);
                JSONObject pictureObject;
                if (parentObject.has("picture")){
                    pictureObject = parentObject.getJSONObject("picture");

                }else{
                    return "";
                }
                JSONObject dataObject = pictureObject.getJSONObject("data");
                if(dataObject.has("url")){
                    return dataObject.getString("url");
                }else{
                    return "";
                }
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
        protected void onPostExecute(String url) {
            super.onPostExecute(url);
            if (!url.equals("")) {
                hackathon.setPictureURL(url);
                System.out.print(url);
            }
            counter++;
            allDone();

        }

        protected void allDone(){
            if (counter >= counter2){
                //get the view pager object from the view
                System.out.println("hellodjasdsad");
                viewPager = (ViewPager) findViewById(R.id.viewPager);

                //add swipe adapter
                HackSwipeAdapter hackSwipeAdapter = new HackSwipeAdapter(getSupportFragmentManager(),allHackathons);

                //set adapter for viewPager
                viewPager.setAdapter(hackSwipeAdapter);
                //start json parsin.
            }
        }
    }







}
