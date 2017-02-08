package com.example.santiagoordonez.hackuoft.HackPage;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

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
import java.util.Calendar;
import java.util.Date;

public class HackPage extends FragmentActivity {

    // initialize a new view pager object
    ViewPager viewPager;
    HackathonDTO hackathonDTO;
    ArrayList<HackathonDTO> allHackathons;
    int counter = 0;
    int counter2 = 0;
    private MyDate date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hackathons);
        date = new MyDate();
        int counter = 0;
        int month = date.getMonth();



        //Makes the 3 api calls to get the next 3 months of hackathons information.
        while (counter < 3){
            if (month < 10){
                new ParseHackathons().execute("http://www.hackalist.org/api/1.0/"+date.getYear()+"/0"+ month+".json",  date.getMonthStr(month));
                Log.v("DEBUG",""+month);
            }else if (month >= 10 && month <= 12){
                new ParseHackathons().execute("http://www.hackalist.org/api/1.0/"+date.getYear()+"/"+month+".json", date.getMonthStr(month) );
            }else if (month >12){
                new ParseHackathons().execute("http://www.hackalist.org/api/1.0/"+date.getYear()+1+"/0"+month%12+".json", date.getMonthStr(month));
            }
            month++;
            counter++;
        }
    }

    public class ParseHackathons extends AsyncTask<String,String,ArrayList<HackathonDTO>>{

        @Override
        protected ArrayList<HackathonDTO> doInBackground(String... params) {
            HttpURLConnection HackathonsAPIConnection = null;
            BufferedReader reader = null;
            try{
                System.out.println(params[0]);
                HackathonsAPIConnection = getConnection(params[0]);
                reader = getBufferedReader(HackathonsAPIConnection);
                String finalJSON = getJSONString(reader);
                JSONObject parentObject = new JSONObject(finalJSON);
                JSONArray parentArray = parentObject.getJSONArray(params[1]);
                return getHackathons(parentArray);
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }catch(JSONException e){
                e.printStackTrace();
            } finally {
                if (HackathonsAPIConnection != null){
                    HackathonsAPIConnection.disconnect();
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


        /**
         *  Returns a string in JSON format with data from hackathons.
         * @param reader BufferedReader
         * @return String
         * @throws IOException
         */
        String getHackAThonsStringData(BufferedReader reader) throws IOException{
            StringBuilder buffer = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            return buffer.toString();
        }

        ArrayList<HackathonDTO> getHackathons(JSONArray parentArray)throws  JSONException{
            allHackathons = new ArrayList<>();
            for (int i = 0; i < parentArray.length();i++){
                hackathonDTO = new HackathonDTO();
                JSONObject jsonObject =  parentArray.getJSONObject(i);
                String startDate = jsonObject.getString("startDate");
                if (hackathonHasNotPassed(startDate)){
                    hackathonDTO.setStartDate(startDate);
                    String endDate = jsonObject.getString("endDate");
                    hackathonDTO.setEndDate(endDate);
                    String title = jsonObject.getString("title");
                    hackathonDTO.setTitle(title);
                    String urlobj = jsonObject.getString("url");
                    hackathonDTO.setUrl(urlobj);
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
            }
            return allHackathons;
        }

        boolean hackathonHasNotPassed(String startDate){
            int hackathonDay =  Integer.parseInt((startDate.substring(startDate.length() - 2)).trim());
            return date.getDay() < hackathonDay;
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
            HttpURLConnection facebookConnection = null;
            BufferedReader reader = null;
            try{
                facebookConnection = getConnection(params[0]);
                reader = getBufferedReader(facebookConnection);
                String finalJSON = getJSONString(reader);
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
                return "";//poor error handling
            }catch(JSONException e){
                e.printStackTrace();
                return "";//poor error handling
            } finally {
                if (facebookConnection != null){
                    facebookConnection.disconnect();
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
                viewPager = (ViewPager) findViewById(R.id.viewPager);
                //add swipe adapter
                HackSwipeAdapter hackSwipeAdapter = new HackSwipeAdapter(getSupportFragmentManager(),allHackathons);
                //set adapter for viewPager
                viewPager.setAdapter(hackSwipeAdapter);
                //start json parsin.
            }
        }
    }

    /**
     * Gets the inputStream from the HackAList API and returns a new BufferedReader.
     * @param connection
     * @return
     * @throws IOException
     */
    BufferedReader getBufferedReader(HttpURLConnection connection) throws  IOException{
        InputStream stream = connection.getInputStream();
        return new BufferedReader(new InputStreamReader(stream));
    }

    HttpURLConnection getConnection(String link) throws IOException{
        HttpURLConnection connection = null;
        URL url = new URL(link);
        connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        return connection;
    }

    String getJSONString(BufferedReader reader) throws IOException{
        StringBuilder buffer = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null){
            buffer.append(line);
        }
        return buffer.toString();
    }
}