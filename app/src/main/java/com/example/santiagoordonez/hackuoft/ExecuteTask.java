package com.example.santiagoordonez.hackuoft;

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
import java.util.List;

/**
 * Created by avidave on 2017-01-07.
 */

public class ExecuteTask extends AsyncTask<String, String, List<EventDTO>> {

    @Override
    protected List<EventDTO> doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
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

            List<EventDTO> EventDTOList = new ArrayList<>();

            for (int i = 0; i < parentArray.length(); i++) {
                EventDTO currentEventDTO = new EventDTO();


                JSONObject finalObject = parentArray.getJSONObject(i);
                //Checks for the date if it is in the future or the day of
                String startTime = (finalObject.getString("start_time").substring(0,9) + " " +
                        finalObject.getString("start_time").substring(11,15));
                String endTime = (finalObject.getString("end_time").substring(0,9) + " " +
                        finalObject.getString("end_time").substring(11,15));

                if (Integer.parseInt(startTime.substring(0,3))<=2015){
                    return EventDTOList;
                }
                String description = finalObject.getString("description");
                String name = finalObject.getString("name");
                JSONObject placeObj = finalObject.getJSONObject("place");
                if (placeObj.getBoolean("location")){
                    JSONObject locationObj = placeObj.getJSONObject("location");
                    String street = locationObj.getString("street");
                    String address = (street + " " + "ON,"+ "Canada");
                    currentEventDTO.setLocation(address);
                    currentEventDTO.setDesription(description);
                    currentEventDTO.setName(name);
                    currentEventDTO.setStartTime(startTime);
                    currentEventDTO.setEndTime(endTime);
                    EventDTOList.add(currentEventDTO);
                }
                else{
                    currentEventDTO.setDesription(description);
                    currentEventDTO.setName(name);
                    currentEventDTO.setLocation("3359 Mississauga Rd, ON,Canada");
                    currentEventDTO.setStartTime(startTime);
                    currentEventDTO.setEndTime(endTime);
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
