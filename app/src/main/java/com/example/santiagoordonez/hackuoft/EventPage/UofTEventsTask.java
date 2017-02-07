package com.example.santiagoordonez.hackuoft.EventPage;

import android.os.AsyncTask;

import com.example.santiagoordonez.hackuoft.dto.EventDTO;

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

/**
 * Created by avidave on 2017-01-16.
 */

public class UofTEventsTask extends AsyncTask<String, String, ArrayList<EventDTO>> {
    Calendar cl;
    @Override

    protected ArrayList<EventDTO> doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            System.out.println("Hello!");
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            System.out.println("connect?");
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String finalJson = buffer.toString();

            JSONObject parentObject = new JSONObject(finalJson);
            JSONArray parentArray = parentObject.getJSONArray("data");
            cl = Calendar.getInstance();
            int year = cl.get(Calendar.YEAR); // get the current year
            int month = cl.get(Calendar.MONTH); // month...
            int day = cl.get(Calendar.DAY_OF_MONTH); // current day in the month

            ArrayList<EventDTO> EventDTOList = new ArrayList<EventDTO>();

            for (int i = 0; i < parentArray.length(); i++) {
                EventDTO currentEventDTO = new EventDTO();

                JSONObject finalObject = parentArray.getJSONObject(i);
                String startTime = "";
                if(finalObject.has("start_time")){
                    startTime = (finalObject.getString("start_time").substring(0,10));
                }
                String endTime = "";
                if(finalObject.has("endTime")){
                    endTime = (finalObject.getString("end_time").substring(0,10));
                }
                if (Integer.parseInt(startTime.substring(0,4))<=year && (Integer.parseInt(startTime.substring(5,7)) <=month)&&
                        (Integer.parseInt(startTime.substring(0,4)) <=day)){
                    System.out.println("dead");
                    break;
                }
                String description = finalObject.getString("description");
                String name = finalObject.getString("name");
                String eventID = finalObject.getString("id");
                currentEventDTO.setEventId(eventID);

                if(finalObject.has("place")){
                    JSONObject placeObj = finalObject.getJSONObject("place");
                    if (placeObj.has("location")){
                        JSONObject locationObj = placeObj.getJSONObject("location");
                        String street = locationObj.getString("street");
                        String city = locationObj.getString("city");
                        String address = (street + city + ", " + "ON, "+ "Canada");
                        currentEventDTO.setLocation(address);
                        currentEventDTO.setDesription(description);
                        currentEventDTO.setName(name);
                        currentEventDTO.setStartTime(startTime);
                        EventDTOList.add(currentEventDTO);
                    }
                    else{
                        currentEventDTO.setDesription(description);
                        currentEventDTO.setName(name);
                        //Gotta fix t, we cannot be this broad
                        currentEventDTO.setLocation("University of Toronto, Toronto, ON,Canada");
                        currentEventDTO.setStartTime(startTime);
                        currentEventDTO.setEndTime(endTime);
                        EventDTOList.add(currentEventDTO);
                    }
                }else{
                    currentEventDTO.setDesription(description);
                    currentEventDTO.setName(name);
                    //Some how gotta figure out the locations cause people are not going
                    //to know how to find their location given that UofT is huge
                    currentEventDTO.setLocation("University of Toronto, Toronto, ON,Canada");
                    currentEventDTO.setStartTime(startTime);
                    EventDTOList.add(currentEventDTO);
                }
            }
            return EventDTOList;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        return null;
    }




}
